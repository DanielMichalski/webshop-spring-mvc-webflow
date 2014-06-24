package com.peoplewearus.web.spring.db;

import com.peoplewearus.web.spring.crypt.BCrypt;
import com.peoplewearus.web.spring.data.UserAlreadyExistsException;
import com.peoplewearus.web.spring.data.UserNotFoundException;
import com.peoplewearus.web.spring.domain.User;
import com.peoplewearus.web.spring.services.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//import se.GroupRed.validator.EmailValidator;


public class SqlUserRepository implements UserRepository {

    private static final String INSERT_USER_STRING = "insert into user_table values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,1)";
    private static final String GET_USER_BY_EMAIL_STRING = "SELECT * from user_table where email = ?";
    private static final String GET_USER_BY_ID_AND_PASSWORD = "SELECT * from user_table where email = ? and password = ?";
    private static final String GET_ALL_USERS = "SELECT * from user_table";
    private static final String UPDATE_USER_STRING = "UPDATE user_table SET firstName=?, lastName=?, co=?, street=?, postal=?, city=?, country=?, phone=?, gender=? WHERE email=?";

    private final ConnectionManager connectionManager;

    public SqlUserRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    // används i display User

    @Override
    public User findUser(String email, String password) throws UserNotFoundException {

        if (authenticate(email, password)) {
            User user = getUser(email);
            return user;
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User getUser(String email) {
        // EmailValidator emailValidator = new EmailValidator();

        if (email == null || email.trim() == "" /*
                                                 * ||
												 * emailValidator.validateEmail
												 * (email) == false
												 */) {
            return null;
        } else {
            Connection con = connectionManager.getConnection();

            try {
                PreparedStatement getUserByEmailStatement = con.prepareStatement(GET_USER_BY_EMAIL_STRING);
                getUserByEmailStatement.setString(1, email);

                ResultSet rs = getUserByEmailStatement.executeQuery();

                if (rs.next()) {
                    // User user = new User(rs.getString(2), rs.getString(3),
                    // rs.getString(4), rs.getString(5),
                    // rs.getString(6), rs.getString(7), rs.getString(8),
                    // rs.getString(9), rs.getString(11));

                    return extractUser(rs);

                } else {
                    return null;
                }

            } catch (SQLException e) {
                throw new RuntimeException("Could not get user with email: " + email, e);
            } finally {
                connectionManager.closeResource(con);
            }
        }
    }

    @Override
    public boolean authenticate(String email, String password) {

        if (password == null || password.trim() == "") {
            return false;
        } else {

            User user = getUser(email);

            if (user != null) {

                if (BCrypt.checkpw(password, user.getPassword())) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        }
    }

    public void addUser(String firstName, String lastName, String co, String street, String zipCode, String city,
                        String country, String phone, String gender, String email, String password) {

        Connection con = connectionManager.getConnection();

        try {
            con.setAutoCommit(false);
            PreparedStatement insertUserStatement = con.prepareStatement(INSERT_USER_STRING);

            String cryptPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

            insertUserStatement.setString(1, firstName);
            insertUserStatement.setString(2, lastName);
            insertUserStatement.setString(3, co);
            insertUserStatement.setString(4, street);
            insertUserStatement.setString(5, zipCode);
            insertUserStatement.setString(6, city);
            insertUserStatement.setString(7, country);
            insertUserStatement.setString(8, phone);
            insertUserStatement.setString(9, gender);
            insertUserStatement.setString(10, email);
            insertUserStatement.setString(11, cryptPassword);
            insertUserStatement.execute();

            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException("Rollback failed!", e1);
            }

            throw new RuntimeException("Could not add user", e);
            //throw new UserAlreadyExistsException();
        } finally {
            connectionManager.closeResource(con);
        }
    }

    public Collection<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        Connection con = connectionManager.getConnection();

        try {
            PreparedStatement getAllUsersByStatement = con
                    .prepareStatement(GET_ALL_USERS);

            ResultSet rs = getAllUsersByStatement.executeQuery();

            while (rs.next()) {
                users.add(extractUser(rs));


            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all users", e);
        } finally {

        }

        return users;
    }


    public void updateUser(String firstName, String lastName, String co, String street, String postal, String city, String country, String phone, String gender, String email) {

        Connection con = connectionManager.getConnection();

        try {
            con.setAutoCommit(false);
            PreparedStatement updateUserStatement = con.prepareStatement(UPDATE_USER_STRING);

            updateUserStatement.setString(1, firstName);
            updateUserStatement.setString(2, lastName);
            updateUserStatement.setString(3, co);
            updateUserStatement.setString(4, street);
            updateUserStatement.setString(5, postal);
            updateUserStatement.setString(6, city);
            updateUserStatement.setString(7, country);
            updateUserStatement.setString(8, phone);
            updateUserStatement.setString(9, gender);
            updateUserStatement.setString(10, email);
            updateUserStatement.execute();

            con.commit();
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


    public boolean checkUser(String firstName, String lastName, String co, String street, String postal,
                             String city, String country, String phone, String gender, String email, String password) throws UserAlreadyExistsException {

        if (firstName == null || firstName.trim() == "" || lastName == null || lastName.trim() == "" || street == null
                || street.trim() == "" || postal == null || postal.trim() == "" || city == null || city.trim() == ""
                || country == null || country.trim() == "" || phone == null || phone.trim() == ""
                || email == null || email.trim() == "" || password == null || password.trim() == "") {
            return true;

        } else {
            Connection con = connectionManager.getConnection();

            try {

                PreparedStatement getUserByIdStatement = con.prepareStatement(GET_USER_BY_EMAIL_STRING);
                getUserByIdStatement.setString(1, email);

                ResultSet rs = getUserByIdStatement.executeQuery();

                if (rs.next()) {
                    throw new UserAlreadyExistsException();
                } else
                    return rs.next();
            } catch (SQLException e) {

                throw new RuntimeException("Could not get user with email:" + email, e);

            } finally {
                connectionManager.closeResource(con);
            }
        }

    }


    private User extractUser(final ResultSet resultSet) throws SQLException {

        return new User(resultSet.getString("user_table.firstName"),
                resultSet.getString("user_table.lastName"), resultSet.getString("user_table.co"),
                resultSet.getString("user_table.street"), resultSet.getString("user_table.postal"),
                resultSet.getString("user_table.city"),
                resultSet.getString("user_table.country"), resultSet.getString("user_table.phone"),
                resultSet.getString("user_table.gender"), resultSet.getString("user_table.email"),
                resultSet.getString("user_table.password"));
    }

}
