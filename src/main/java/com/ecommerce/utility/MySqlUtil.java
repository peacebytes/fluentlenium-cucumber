package com.ecommerce.utility;

import java.sql.*;

import static com.ecommerce.utility.MyTimeOut.LARGE_TIME_OUT;
import static com.ecommerce.utility.MyTimeOut.SMALL_TIME_OUT;

public class MySqlUtil {
    private static Connection conn = null;

    private static Connection getConnection() throws Exception{
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "fluentlenium_cucumber";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "pass1234";
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url + dbName, userName, password);
        return conn;
    }

    public static int getAvailableTestId() {
        int testId = 1;
        int elapsedWait = SMALL_TIME_OUT;
        while (elapsedWait < LARGE_TIME_OUT) {
            try {
                getConnection();
                String queryStatement = "select * from test_ids where test_status=\"available\" LIMIT 1";
                PreparedStatement preparedStatement = conn.prepareStatement(queryStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    String testStatus = rs.getString("test_status");
                    if (testStatus.equals("available")) {
                        testId = rs.getInt("test_id");
                        //update
                        queryStatement = "update test_ids set test_status=\"running\" WHERE test_id=" + testId;
                        preparedStatement = conn.prepareStatement(queryStatement);
                        preparedStatement.executeUpdate();
                        preparedStatement.close();
                    }
                }

                // Close connection
                preparedStatement.close();
                disconnect();
                break;
            } catch (Exception ex){
                try{
                    Thread.sleep(SMALL_TIME_OUT);
                }catch (InterruptedException Interex){
                }
                elapsedWait += SMALL_TIME_OUT;
            }
        }
        return --testId;
    }

    public static void updateTestId(int testId, String testStatus) {
        testId++;
        int elapsedWait = SMALL_TIME_OUT;
        while (elapsedWait < LARGE_TIME_OUT) {
            try {
                getConnection();
                // Query
                String updateQueryStatement = "update test_ids set test_status=\""+testStatus+"\" where test_id=" + testId;
                PreparedStatement preparedStatement = conn.prepareStatement(updateQueryStatement);
                preparedStatement.executeUpdate();

                // Close connection
                preparedStatement.close();
                disconnect();
                break;
            } catch (Exception ex){
                ex.printStackTrace();
                try{
                    Thread.sleep(SMALL_TIME_OUT);
                }catch (InterruptedException Interex){
                }
                elapsedWait += SMALL_TIME_OUT;
            }
        }
    }

    private static void disconnect() {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
