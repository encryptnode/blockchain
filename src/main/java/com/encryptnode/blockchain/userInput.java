package com.encryptnode.blockchain;

import com.encryptnode.blockchain.dbController.PostgreSQL;

import java.util.Scanner;

public class userInput {

    public static int getUserID(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your username: ");
        String userID = scanner.next();

        int ID = PostgreSQL.checkForUser(userID);

        return ID;
    }
}
