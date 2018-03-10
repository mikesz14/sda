package com.test.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcHikariProperties {

    private static final int MAX_SIZE = 3;

    public static void main(String[] args) {

        Connection conn = null;
        try {
            HikariConfig config = new HikariConfig("/hikari.properties");
            DataSource dataSource = new HikariDataSource(config);

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
