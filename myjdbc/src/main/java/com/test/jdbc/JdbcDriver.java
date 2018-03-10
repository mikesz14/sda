package com.test.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JdbcDriver {
    public static void main(String[] args) {

        Connection conn = null;
        try {
            Properties props = new Properties();
            props.put("user", "test");
            props.put("password", "testpass");

            Driver d = (Driver) Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            conn = d.connect("jdbc:oracle:thin:@localhost:1521:xe", props);

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
