package com.peoplewearus.web.spring.db;

import com.peoplewearus.web.spring.domain.Product;
import com.peoplewearus.web.spring.services.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SqlProductRepository implements ProductRepository {

    private static final String INSERT_PRODUCT_STRING = "insert into product_table values (null, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_PRODUCT_BY_REFERENCENUMBER_STRING = "SELECT * from product_table where referenceNumber = ?";
    private static final String GET_ALL_PRODUCTS = "SELECT * from product_table WHERE state='In Stock' OR state='Out Of Stock'";
    private static final String UPDATE_PRODUCT_STRING = "UPDATE product_table SET productName=?, price=?, brand=?, description=?, genre=?, state=? WHERE referenceNumber=?";
    private static final String GET_PRODUCTS_BY_CATEGORY_STRING = "SELECT * FROM product_table WHERE genre = ? AND state='In Stock' OR state='Out Of Stock'";

    private final ConnectionManager connectionManager;

    public SqlProductRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Product getProduct(String referenceNumber) {
        if (referenceNumber == null || referenceNumber.trim() == "") {
            return null;
        } else {
            Connection con = connectionManager.getConnection();

            try {

                PreparedStatement getProductByReferenceNumberStatement = con
                        .prepareStatement(GET_PRODUCT_BY_REFERENCENUMBER_STRING);
                getProductByReferenceNumberStatement.setString(1, referenceNumber);

                ResultSet rs = getProductByReferenceNumberStatement.executeQuery();

                if (rs.next()) {
                    return extractProduct(rs);

                } else {
                    return null;
                }

            } catch (SQLException e) {
                throw new RuntimeException("Could not get product with reference number: " + referenceNumber, e);
            } finally {
                connectionManager.closeResource(con);
            }
        }
    }

    public void addProduct(String productName, String referenceNumber, int price, String brand, String description,
                           String genre, String state) {

        Connection con = connectionManager.getConnection();

        try {
            con.setAutoCommit(false);
            PreparedStatement insertProductStatement = con.prepareStatement(INSERT_PRODUCT_STRING);

            insertProductStatement.setString(1, referenceNumber);
            insertProductStatement.setString(2, productName);
            insertProductStatement.setInt(3, price);
            insertProductStatement.setString(4, brand);
            insertProductStatement.setString(5, description);
            insertProductStatement.setString(6, genre);
            insertProductStatement.setString(7, state);
            insertProductStatement.execute();

            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException("Rollback failed!", e1);
            }

            throw new RuntimeException("Could not add product", e);
        } finally {
            connectionManager.closeResource(con);
        }
    }

    public Collection<Product> getAllProducts() {
        List<Product> products = new ArrayList<Product>();

        Connection con = connectionManager.getConnection();

        try {
            PreparedStatement getAllProductsByStatement = con.prepareStatement(GET_ALL_PRODUCTS);

            ResultSet rs = getAllProductsByStatement.executeQuery();

            while (rs.next() && rs.getString("product_table.state").equals("In Stock")) {
                products.add(extractProduct(rs));

            }
            return products;

        } catch (SQLException e) {
            throw new RuntimeException("Could not get all products", e);
        } finally {
            connectionManager.closeResource(con);
        }

    }

    public Product updateProduct(String productName, String referenceNumber, int price, String brand, String description, String genre, String state) {
        if (state.equals("null")) {
            return getProduct(referenceNumber);
        } else {

            Connection con = connectionManager.getConnection();

            try {
                con.setAutoCommit(false);
                PreparedStatement updateProductStatement = con.prepareStatement(UPDATE_PRODUCT_STRING);

                updateProductStatement.setString(1, productName);
                updateProductStatement.setInt(2, price);
                updateProductStatement.setString(3, brand);
                updateProductStatement.setString(4, description);
                updateProductStatement.setString(5, genre);
                updateProductStatement.setString(6, state);
                updateProductStatement.setString(7, referenceNumber);
                updateProductStatement.execute();

                //private static final String UPDATE_PRODUCT_STRING = "UPDATE product_table SET productName=?, price=?, " +
                //		"brand=?, description=?, genre=?, state=? WHERE referenceNumber=?";


                con.commit();

                return getProduct(referenceNumber);
            } catch (SQLException e) {
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    throw new RuntimeException("Rollback failed!", e1);
                }

                throw new RuntimeException("Could not update product", e);
            } finally {
                connectionManager.closeResource(con);
            }
        }
    }

    public boolean checkProduct(String productName, String referenceNumber, int price, String brand) {

        if (productName == null || productName.trim() == "" || referenceNumber == null || referenceNumber.trim() == ""
                || brand == null || brand.trim() == "") {
            return true;
        } else {

            Connection con = connectionManager.getConnection();

            try {

                PreparedStatement getProductByIdStatement = con.prepareStatement(GET_PRODUCT_BY_REFERENCENUMBER_STRING);
                getProductByIdStatement.setString(1, referenceNumber);

                ResultSet rs = getProductByIdStatement.executeQuery();

                return rs.next();

            } catch (SQLException e) {
                throw new RuntimeException("Could not get product with id:" + productName, e);
            } finally {
                connectionManager.closeResource(con);
            }
        }
    }


    @Override
    public List<Product> getProductsByCategory(String category) {

        if (category == null || category.trim() == "") {
            return null;
        } else {

            Connection con = connectionManager.getConnection();
            List<Product> list = new ArrayList<Product>();

            try {

                PreparedStatement getProductByCategoryStatement = con.prepareStatement(GET_PRODUCTS_BY_CATEGORY_STRING);

                getProductByCategoryStatement.setString(1, category);

                ResultSet rs = getProductByCategoryStatement.executeQuery();

                while (rs.next() && rs.getString("product_table.state").equals("In Stock")) {
                    list.add(extractProduct(rs));
                }

                return list;

            } catch (SQLException e) {
                throw new RuntimeException("Could not get products with genre:" + category, e);
            } finally {
                connectionManager.closeResource(con);
            }
        }

    }


    private Product extractProduct(final ResultSet resultSet) throws SQLException {

        return new Product(resultSet.getString("product_table.referenceNumber"),
                resultSet.getString("product_table.productName"), resultSet.getInt("product_table.price"),
                resultSet.getString("product_table.brand"), resultSet.getString("product_table.description"),
                resultSet.getString("product_table.genre"), resultSet.getString("product_table.state"));
    }


}
