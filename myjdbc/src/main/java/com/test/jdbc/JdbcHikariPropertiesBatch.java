package com.test.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

public class JdbcHikariPropertiesBatch {

    private static final int MAX_SIZE = 3;

    public static void main(String[] args) {

        Connection conn = null;
        try {
            HikariConfig config = new HikariConfig("/hikari.properties");
            DataSource dataSource = new HikariDataSource(config);

            conn = dataSource.getConnection();

            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();

            stmt.addBatch("insert into persons values(100, 'tom')");
            stmt.addBatch("insert into persons values(101, 'tom1')");

            int[] counts = stmt.executeBatch();

            conn.commit();

            System.out.println(Arrays.toString(counts));
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
