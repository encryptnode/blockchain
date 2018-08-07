package com.encryptnode.blockchain.dbController;

import com.encryptnode.blockchain.Blocks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class PostgreSQL {
    public static void BlockToDB(Blocks block) {
        String inputInfo = "'" + block.hash + "', '" + block.previousHash + "', '" + block.timeStamp + "'";
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/encryptnode",
                            "tyler", "123");
            c.setAutoCommit(false);

            stmt = c.createStatement();

            String sql = "INSERT INTO blocks (block_hash, prev_hash, time_stamp) "
                    + "VALUES (" + inputInfo + ");";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Pushed to database successfully");
    }


}