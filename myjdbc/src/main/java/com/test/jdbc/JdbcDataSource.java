package com.test.jdbc;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcDataSource {
    public static void main(String[] args) {

        Connection conn = null;
        try {
            OracleDataSource dataSource = new OracleDataSource();
            //dataSource.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            dataSource.setDriverType("thin");
            dataSource.setServerName("localhost");
            dataSource.setDatabaseName("xe");
            dataSource.setPortNumber(1521);
            dataSource.setUser("test");
            dataSource.setPassword("testpass");

            conn = dataSource.getConnection();

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
