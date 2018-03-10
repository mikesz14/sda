package com.test.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import oracle.jdbc.driver.OracleConnection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcHikari {

    private static final int MAX_SIZE = 3;

    public static void main(String[] args) {

        Connection conn = null;
        try {
            HikariConfig config = new HikariConfig();

            config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
            config.setUsername("test");
            config.setPassword("testpass");
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            config.setMaximumPoolSize(MAX_SIZE);


            DataSource dataSource = new HikariDataSource(config);

            Connection[] conns = new Connection[MAX_SIZE];
            for (int i = 0; i < MAX_SIZE * 2; i++) {
                conn = dataSource.getConnection();
                OracleConnection unwrapped = conn.unwrap(OracleConnection.class);
                System.out.println(i + " " + unwrapped.hashCode());
                if (i < MAX_SIZE) {
                    conns[i] = conn;
                }
                if (i >= MAX_SIZE - 1) {
                    System.out.println("close " + (i % MAX_SIZE));
                    conns[i % MAX_SIZE].close();
                }
            }

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
