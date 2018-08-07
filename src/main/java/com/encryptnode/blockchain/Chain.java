package com.encryptnode.blockchain;

import java.util.ArrayList;
import java.util.LinkedList;

public class Chain {

    public static LinkedList<Blocks> blockchain = new LinkedList<>();
    public static int difficulty = 5;


    public static Boolean isChainValid() {
        Blocks currentBlock;
        Blocks previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //Checks to make sure chain is valid
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare registered and calculated has
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}