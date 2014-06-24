package com.peoplewearus.web.spring.db;

import com.peoplewearus.web.spring.crypt.BCrypt;
import com.peoplewearus.web.spring.data.AdminNotFoundException;
import com.peoplewearus.web.spring.domain.Admin;
import com.peoplewearus.web.spring.services.AdminRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class SqlAdminRepository implements AdminRepository {

    private static final String INSERT_ADMIN_STRING = "insert into admin_table values (null, ?, ?, ?, ?)";
    private static final String GET_ADMIN_BY_EMAIL_STRING = "SELECT * from admin_table where email = ?";
    private static final String GET_ADMIN_BY_ID_AND_PASSWORD = "SELECT * from admin_table where email = ? and password = ?";
    private static final String GET_ALL_ADMINS = "SELECT * from admin_table";
    private static final String UPDATE_ADMIN_STRING = "UPDATE admin_table SET password=?";

    private final ConnectionManager connectionManager;

    public SqlAdminRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Admin getAdmin(String email) {

        if (email == null || email.trim() == "") {
            return null;
        } else {
            Connection con = connectionManager.getConnection();

            try {
                PreparedStatement getAdminByEmailStatement = con.prepareStatement(GET_ADMIN_BY_EMAIL_STRING);
                getAdminByEmailStatement.setString(1, email);

                ResultSet rs = getAdminByEmailStatement.executeQuery();

                if (rs.next()) {

                    return extractAdmin(rs);

                } else {
                    return null;
                }

            } catch (SQLException e) {
                throw new RuntimeException("Could not get admin with email: " + email, e);
            } finally {
                connectionManager.closeResource(con);
            }
        }
    }

    public void addAdmin(String firstName, String lastName, String email, String password) {

        Connection con = connectionManager.getConnection();

        try {
            con.setAutoCommit(false);
            PreparedStatement insertAdminStatement = con.prepareStatement(INSERT_ADMIN_STRING);

            String cryptPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

            insertAdminStatement.setString(1, lastName);
            insertAdminStatement.setString(2, email);
            insertAdminStatement.setString(3, firstName);
            insertAdminStatement.setString(4, cryptPassword);
            insertAdminStatement.execute();

            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException("Rollback failed!", e1);
            }

            throw new RuntimeException("Could not add user", e);
        } finally {
            connectionManager.closeResource(con);
        }
    }


    @Override
    public Admin authenticate(String email, String password) throws AdminNotFoundException {


        if (password == null || password.trim() == "") {
            return null;
        } else {

            Admin admin = getAdmin(email);

            if (admin != null) {

                if (BCrypt.checkpw(password, admin.getPassword())) {
                    return admin;
                } else {
                    return null;
                }

            } else {
                return null;
            }

        }
    }

    public Collection<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<Admin>();

        Connection con = connectionManager.getConnection();

        try {
            PreparedStatement getAllAdminsByStatement = con
                    .prepareStatement(GET_ALL_ADMINS);

            ResultSet rs = getAllAdminsByStatement.executeQuery();

            while (rs.next()) {
                admins.add(extractAdmin(rs));


            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all admins", e);
        } finally {

        }

        return admins;
    }


    public void updateAdmin(String password) {

        Connection con = connectionManager.getConnection();

        try {
            con.setAutoCommit(false);
            PreparedStatement updateAdminStatement = con.prepareStatement(UPDATE_ADMIN_STRING);

            updateAdminStatement.setString(1, password);
            updateAdminStatement.execute();

            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException("Rollback failed!", e1);
            }

            throw new RuntimeException("Could not update admin", e);
        } finally {
            connectionManager.closeResource(con);
        }
    }

    public boolean checkAdmin(String email, String password, String firstName, String lastName) {


        if (email == null || email.trim() == "" || password == null || password.trim() == "" || firstName == null || firstName.trim() == "" || lastName == null || lastName.trim() == "") {

            return true;

        } else {
            Connection con = connectionManager.getConnection();

            try {

                PreparedStatement getUserByIdStatement = con.prepareStatement(GET_ADMIN_BY_EMAIL_STRING);
                getUserByIdStatement.setString(1, email);

                ResultSet rs = getUserByIdStatement.executeQuery();

                return rs.next();

            } catch (SQLException e) {
                throw new RuntimeException("Could not get user with id:" + firstName, e);
            } finally {
                connectionManager.closeResource(con);
            }
        }

    }


    private Admin extractAdmin(final ResultSet resultSet) throws SQLException {

        return new Admin(resultSet.getString("admin_table.firstName"),
                resultSet.getString("admin_table.lastName"), resultSet.getString("admin_table.email"),
                resultSet.getString("admin_table.password"));
    }


}


