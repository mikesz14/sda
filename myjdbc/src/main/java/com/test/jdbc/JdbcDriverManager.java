package com.test.jdbc;

import java.sql.*;
import java.util.Properties;

public class JdbcDriverManager {
    public static void main(String[] args) {

        Connection conn = null;
        try {
            DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "test", "testpass");

            Statement stmt = conn.createStatement();
            stmt.execute("select * from persons");
            ResultSet rs = stmt.getResultSet();
            // do work

            while (rs.next()) {
                int personId = rs.getInt(1);
                String lastName = rs.getString(2);

                System.out.println(personId + " " + lastName);
            }
        } catch (Exception e) {
            // handle any exceptions as appropriate
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
