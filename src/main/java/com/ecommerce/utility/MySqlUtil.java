package com.ecommerce.utility;

import java.sql.*;

public class MySqlUtil {
    private static Connection conn = null;
    private static PreparedStatement preparedStatement = null;

    private static Connection getConnection() {
        try {
            if (conn == null) {
                String url = "jdbc:mysql://localhost:3306/";
                String dbName = "fluentlenium_cucumber";
                String driver = "com.mysql.jdbc.Driver";
                String userName = "root";
                String password = "pass1234";
                Class.forName(driver).newInstance();
                conn = DriverManager.getConnection(url + dbName, userName, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static int getAvailableTestId() {
        int testId = 0;
        try {
            getConnection();
            // Query
            String getQueryStatement = "select * from test_ids";
            preparedStatement = conn.prepareStatement(getQueryStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String testStatus = rs.getString("test_status");
                if (testStatus.equals("available")) {
                    testId = rs.getInt("test_id")-1;
                }
            }

            // Close connection
            preparedStatement.close();
            disconnect(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testId;
    }

    public static void updateTestId(int testId, String testStatus) {
        try {
            getConnection();
            // Query
            String updateQueryStatement = "UPDATE test_ids SET test_status=\""+testStatus+"\" WHERE test_id=" + testId;
            preparedStatement = conn.prepareStatement(updateQueryStatement);
            preparedStatement.executeUpdate();

            // Close connection
            preparedStatement.close();
            disconnect(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void disconnect(Connection connection) throws SQLException {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
