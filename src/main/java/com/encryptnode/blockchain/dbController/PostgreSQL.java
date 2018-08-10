package com.encryptnode.blockchain.dbController;

import com.encryptnode.blockchain.Blocks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;


public class PostgreSQL {
    public static String DB_URL = "jdbc:postgresql://ec2-54-225-76-201.compute-1.amazonaws.com:5432/d8imvua4k1tucn?user=rzlajnimzpnjuo&password=4acb3fc818ed2c08307d953cd531e71f7069eb9ffcd79715c559a5dc6df39508&sslmode=require";

    public static void BlockToDB(Blocks block) {
        String inputInfo = "'" + block.userID + "', '" + block.hash + "', '" + block.previousHash + "', '" + block.timeStamp + "'";
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(DB_URL);
            c.setAutoCommit(false);

            stmt = c.createStatement();

            String sql = "INSERT INTO blocks (user_id, block_hash, prev_hash, time_stamp) "
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

    public static void setLastHash(Blocks block){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(DB_URL);
            c.setAutoCommit(false);

            stmt = c.createStatement();

            String sql = "DELETE FROM last; "
                    + ""
                    + "INSERT INTO last (hash)"
                    + "VALUES ('" + block.previousHash + "');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

    }

    public static String getLastHash() {
        Connection c = null;
        Statement stmt = null;
        String lastHash = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(DB_URL);

            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM last;");
            while (rs.next()) {
                lastHash = rs.getString("hash");
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Last hash in chain was" + lastHash);

        return lastHash;
    }

    public static int checkForUser(String input){
        Connection c = null;
        Statement stmt = null;
        String userName = null;
        int userID = 0;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(DB_URL);
            c.setAutoCommit(false);

            System.out.print("checking for user");
            TimeUnit.SECONDS.sleep(1);
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + input + "';");
            while(rs.next()){
                userName = rs.getString("username");
                userID = rs.getInt("user_id");

            }

            rs.close();
            stmt.close();
            c.close();
    }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        if(userName == null){
            System.out.println("No account matching that name");
            System.out.println("Please try again...");
            System.out.println("if you have not made an account, please visit:");
            System.out.println("www.encryptnode.com");
            System.exit(0);
        }else {
            System.out.println("Login Successful! Happy mining!");

        }
        return userID;
    }


}