package com.encryptnode.blockchain;

import com.encryptnode.blockchain.dbController.PostgreSQL;

import java.util.Date;

public class Blocks {

    public String hash;
    public String previousHash;
    private String data;
    public String userID;
    public Date timeStamp;
    private  int nonce;


    public Blocks(String data, String previousHash, String userID){
        this.data = data;
        this.previousHash = previousHash;
        this.userID = userID;
        this.timeStamp = new java.util.Date();
        this.hash = calculateHash();
    }

    public String calculateHash(){
        String calculatedHash = Hashing.applySha256(
                previousHash +
                        timeStamp +
                        Integer.toString(nonce) +
                        data
        );
        return calculatedHash;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        PostgreSQL.BlockToDB(this);
        System.out.println("Block Minded: " + hash);
        PostgreSQL.setLastHash(this);
    }
}
