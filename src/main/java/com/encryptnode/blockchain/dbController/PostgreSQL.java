package com.encryptnode.blockchain.dbController;

import com.encryptnode.blockchain.Blocks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;


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

    public static void setLastHash(Blocks block){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/encryptnode",
                            "tyler", "123");
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
                    .getConnection("jdbc:postgresql://localhost:5432/encryptnode",
                            "tyler", "123");
            c.setAutoCommit(false);
            System.out.println("Opened database!");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM last;");
            while (rs.next()) {
                lastHash = rs.getString("hash");
            }
            rs.close();
            stmt.close();
            c.close();
            System.out.println("Closed conection database!");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Last hash in chain was" + lastHash);

        return lastHash;
    }

    public static String checkForUser(String input){
        Connection c = null;
        Statement stmt = null;
        String userName = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/encryptnode",
                            "tyler", "123");
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
            System.out.println("Please try again");
            System.out.println("if you have not made an account, please visit:");
            System.out.println("www.encryptnode.com");
            System.exit(0);
        }else {
            System.out.println("Login Successful! Happy mining!");

        }
        return userName;
    }


}