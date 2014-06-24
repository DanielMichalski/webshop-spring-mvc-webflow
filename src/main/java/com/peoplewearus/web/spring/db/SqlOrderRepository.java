package com.peoplewearus.web.spring.db;

import com.peoplewearus.web.spring.domain.Order;
import com.peoplewearus.web.spring.domain.Orderline;
import com.peoplewearus.web.spring.services.OrderRepository;
import com.peoplewearus.web.spring.services.ProductRepository;
import com.peoplewearus.web.spring.services.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SqlOrderRepository implements OrderRepository {

    private static final String INSERT_ORDER_STRING = "insert into order_table values (null, ?, Ordered)";
    private static final String INSERT_ORDERLINE_STRING = "insert into orderline_table values (null,?,?,?)";
    private static final String GET_ORDER_BY_EMAIL_AND_STATE_STRING = "SELECT * from order_table WHERE email = ? AND state = ?";
    private static final String GET_ORDERLINES_BY_ORDER_ID = "SELECT * from orderline_table WHERE orderId = ?";
    private static final String UPDATE_ORDER_STATE_STRING = "UPDATE order_table SET state = ? WHERE id = ?";

    private final ConnectionManager connectionManager;

    UserRepository userRepository;
    ProductRepository productRepository;

    public SqlOrderRepository(ConnectionManager connectionManager, UserRepository userRepository, ProductRepository productRepository) {
        this.connectionManager = connectionManager;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    private void addOrder(String email) {


        Connection con = connectionManager.getConnection();

        try {
            con.setAutoCommit(false);

            PreparedStatement insertOrderStatement = con
                    .prepareStatement(INSERT_ORDER_STRING);

            insertOrderStatement.setString(1, email);
            insertOrderStatement.execute();

            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException("Rollback failed!", e1);
            }

            throw new RuntimeException("Could not add order1", e);
        } finally {
            connectionManager.closeResource(con);
        }
    }


    public Order makeOrder(Order order) {
        if (order == null) {
            return null;
        } else {

            Connection con = connectionManager.getConnection();

            addOrder(order.getUser().getEmail());

            try {
                con.setAutoCommit(false);

                PreparedStatement insertOrderlineStatement = con.prepareStatement(INSERT_ORDERLINE_STRING);

                for (Orderline line : order.getProductsOrdered()) {

                    insertOrderlineStatement.setString(1, line.getProduct().getReferenceNumber());
                    insertOrderlineStatement.setInt(2, line.getQuantity());
                    insertOrderlineStatement.setInt(3, getOrderId(order.getUser().getEmail(), "Ordered"));

                    insertOrderlineStatement.addBatch();
                }

                insertOrderlineStatement.executeBatch();


                con.commit();
                return order;

            } catch (SQLException e) {
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    throw new RuntimeException("Rollback failed!", e1);
                }

                throw new RuntimeException("Could not add order2", e);
            } finally {
                connectionManager.closeResource(con);
            }
        }
    }


    public Order getOrder(String email, String state) {

        System.out.println("1 hej jag heter getOrder " + email + state);

        if (email == null || email.trim() == "") {
            System.out.println("hej från getOrder första if");
            return null;
        } else {

            System.out.println("hej från getOrder första else");

            System.out.println("2 hej från else");

            Connection con = connectionManager.getConnection();

            try {
                PreparedStatement getOrderStatement = con
                        .prepareStatement(GET_ORDER_BY_EMAIL_AND_STATE_STRING);
                getOrderStatement.setString(1, email);
                getOrderStatement.setString(2, state);

                ResultSet resultSet = getOrderStatement.executeQuery();

                if (resultSet.next()) {
                    System.out.println("hej från getOrder if(resultset.next)");
                    if (resultSet.getString("order_table.state").equals(state)) {
                        System.out.println("4 hej från if if state: " + resultSet.getString("order_table.state").equals(state));
                        Order order = extractOrder(resultSet);
                        return order;
                    }
                } else {
                    return null;
                }
            } catch (SQLException e) {

                throw new RuntimeException("Could not get order with email:" + email, e);
            } finally {
                connectionManager.closeResource(con);
            }
        }
        return null;
    }

    public int getOrderId(String email, String state) {

        System.out.println("getOrderId INPUT: " + email + state);

        Connection con = connectionManager.getConnection();

        try {
            PreparedStatement getOrderStatement = con
                    .prepareStatement(GET_ORDER_BY_EMAIL_AND_STATE_STRING);
            getOrderStatement.setString(1, email);
            getOrderStatement.setString(2, state);

            ResultSet resultSet = getOrderStatement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getString("order_table.state").equals(state)) {

                    return resultSet.getInt("order_table.id");
                }
            } else {
                return 0;
            }
        } catch (SQLException e) {

            throw new RuntimeException("Could not get order with email:" + email, e);
        } finally {
            connectionManager.closeResource(con);
        }
        return 0;
    }


    // FIXA (GAMMAL)
    public Collection<Order> getAllOrders() {
        List<Order> orders = new ArrayList<Order>();

        Connection con = connectionManager.getConnection();

        try {
            ResultSet rs = con
                    .createStatement()
                    .executeQuery("SELECT * from product_table join orderline_table on product_table.product_id = orderline_table.product_id " +
                            "join order_table on orderline_table.order_id = order_table.order_id join user_table on order_table.user_id = user_table.user_id join address_table on user_table.address_id = address_table.address_id");

            while (rs.next()) {
                orders.add(extractOrder(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all orders", e);
        } finally {
            connectionManager.closeResource(con);
        }

        return orders;
    }


    public List<Orderline> getAllOrderlines(int id) {
        Connection con = connectionManager.getConnection();
        List<Orderline> lines = new ArrayList<Orderline>();

        try {

            PreparedStatement getOrderStatement = con
                    .prepareStatement(GET_ORDERLINES_BY_ORDER_ID);

            getOrderStatement.setInt(1, id);

            ResultSet resultSet = getOrderStatement.executeQuery();
            while (resultSet.next()) {
                Orderline orderline = extractOrderline(resultSet);
                lines.add(orderline);
            }

            return lines;

        } catch (SQLException e) {
            throw new RuntimeException("Could not get all orderlines with orderID: " + id);
        } finally {
            connectionManager.closeResource(con);
        }

    }

    private Orderline extractOrderline(final ResultSet resultSet) throws SQLException {
        System.out.println(resultSet.getString("orderline_table.referenceNumber"));
        return new Orderline(productRepository.getProduct(resultSet.getString("orderline_table.referenceNumber")), resultSet.getInt("orderline_table.amount"));
    }

    public Order updateOrderState(Order order, String state) {
        System.out.println("STATE INNAN UPDATE: " + state);

        if (state.equals("null")) {
            return order;
        } else {

            Connection con = connectionManager.getConnection();

            try {

                PreparedStatement updateOrderStatement = con
                        .prepareStatement(UPDATE_ORDER_STATE_STRING);

                updateOrderStatement.setString(1, state);
                System.out.println("ORDER ID: " + getOrderId(order.getUser().getEmail(), state));
                updateOrderStatement.setInt(2, getOrderId(order.getUser().getEmail(), order.getState()));


                updateOrderStatement.execute();

                System.out.println("STATE EFTER UPDATE: " + getOrder(order.getUser().getEmail(), state).getState());
                return getOrder(order.getUser().getEmail(), state);

            } catch (SQLException e) {
                throw new RuntimeException("Could not update order: " + order);
            } finally {
                connectionManager.closeResource(con);
            }

        }

    }


    private Order extractOrder(final ResultSet resultSet) throws SQLException {


        Order order = new Order();
        order.setProductsOrdered(getAllOrderlines(resultSet.getInt("order_table.id")));
        order.setUser(userRepository.getUser(resultSet.getString("order_table.email")));
        order.setId(resultSet.getInt("order_table.id"));
        order.setState(resultSet.getString("order_table.state"));

        return order;

    }


}
