package com.encryptnode.blockchain;

import com.encryptnode.blockchain.dbController.PostgreSQL;

import java.util.Scanner;

public class userInput {

    public static String getUserID(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your username: ");
        String userID = scanner.next();

        PostgreSQL.checkForUser(userID);

        return userID;
    }
}
