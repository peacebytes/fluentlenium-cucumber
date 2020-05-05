package com.walmart.utility;

import java.sql.*;

public class MySqlUtil {
    private static Connection conn = null;
    private static PreparedStatement preparedStatement = null;
    //Locks
    static final Object readLock = new Object();
    static final Object writeLock = new Object();
    static final Object readWriteLock = new Object();
    static boolean enableReadLock = false;
    static boolean enableWriteLock = false;

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

    private void setReadLock(boolean enable) {
        enableReadLock = enable;
    }

    /**
     * Enable or disable write lock
     *
     * @param enable true -> enable , false -> disable
     */
    private void setWriteLock(boolean enable) {
        enableWriteLock = enable;
    }

    /**
     * Enable or disable read-write lock
     *
     * @param enable true -> enable , false -> disable
     */
    private void setReadWriteLock(boolean enable) {
        enableReadLock = enable;
        enableWriteLock = enable;
    }

    public static int getAvailableTestId() {
        if (enableReadLock && enableWriteLock) { //Доколку има read-write lock
            synchronized (readWriteLock) {
                return getAvailableTestIdPrivate();
            }
        } else {
            if (enableReadLock) { //Доколку има read-lock
                synchronized (readLock) {
                    return getAvailableTestIdPrivate();
                }
            } else {
                return getAvailableTestIdPrivate();
            }
        }
    }
    private static int getAvailableTestIdPrivate() {
        int testId = 0;
        try {
            getConnection();
            // Query
            String getQueryStatement = "select * from test_ids";

            preparedStatement = conn.prepareStatement(getQueryStatement);

            // Execute the Query, and get a java ResultSet
            ResultSet rs = preparedStatement.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) {
                int id = rs.getInt("test_id");
                String testStatus = rs.getString("test_status");
                if (testStatus.equals("available")) {
                    testId = id-1;
                    updateTestId(testId,"running");
                }
            }

            // Close connection
            preparedStatement.close();
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testId;
    }

    public static void updateTestId(int testId, String testStatus) {
        if (enableReadLock && enableWriteLock) {
            synchronized (readWriteLock) {
                updateTestIdPrivate(testId, testStatus);
            }
        } else {
            if (enableWriteLock) {
                synchronized (writeLock) {
                    updateTestIdPrivate(testId, testStatus);
                }
            } else {
                updateTestIdPrivate(testId, testStatus);
            }
        }
    }

    private static void updateTestIdPrivate(int testId, String testStatus) {
        try {
            getConnection();
            // Query
            String updateQueryStatement = "UPDATE test_ids SET test_status=\""+testStatus+"\" WHERE test_id=" + testId;

            preparedStatement = conn.prepareStatement(updateQueryStatement);

            // execute insert SQL statement
            preparedStatement.executeUpdate();
            //System.out.println(" Updated test " + testId + " to "+testStatus+" successfully !");
            // Close connection
            preparedStatement.close();
        } catch (

                SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() throws SQLException {
        try {

            conn.close();

        } catch (SQLException e) {

        }
    }
}
