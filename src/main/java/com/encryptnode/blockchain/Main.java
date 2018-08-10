package com.encryptnode.blockchain;


import com.encryptnode.blockchain.dbController.PostgreSQL;

import static com.encryptnode.blockchain.Chain.blockchain;
import static com.encryptnode.blockchain.Chain.difficulty;
import static com.encryptnode.blockchain.Chain.isChainValid;

public class Main {

    public static void main(String[] args) {

        int ID = userInput.getUserID();

        String lasthash = PostgreSQL.getLastHash();
        blockchain.add(new Blocks("Hi im the first block", lasthash, ID));
        System.out.println("Trying to Mine block... ");
        blockchain.get(0).mineBlock(difficulty);


        for(int i = 1; i > 0; i ++){

            blockchain.add(new Blocks("You mined a new block!", blockchain.get(blockchain.size()-1).hash, ID));
            System.out.println("Trying to mine block ...");
            blockchain.get(i).mineBlock(difficulty);
            System.out.println("\nBlockchain is Valid: " + isChainValid());
//            String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
//            System.out.println(blockchainJson);





        }


    }
}
