package com.michael.demos.blockchain;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * 类功能描述:
 * <pre>
 *   Main  参考 https://www.cnblogs.com/zacky31/p/9057193.html
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/10/29 19:52
 */
public class Main {

    /** 定义区块链 */
    public static ArrayList<Block> BLOCKCHAIN = new ArrayList<>();

    /** 挖矿计算量 越大越难 */
    public static int DIFFICULTY = 7;

    public static void main(String[] args) {
        long beginTime1 = System.currentTimeMillis();
        BLOCKCHAIN.add(new Block("Hi i am the first block", "0"));
        System.out.println("Trying to mine block 1...");
        BLOCKCHAIN.get(0).mineBlock(DIFFICULTY);
        long endTime1 = System.currentTimeMillis();
        System.out.println("Mining block 1 cost " + (endTime1 - beginTime1));

        long beginTime2 = System.currentTimeMillis();
        BLOCKCHAIN.add(new Block("Hi i am the second block", BLOCKCHAIN.get(BLOCKCHAIN.size() - 1).hash));
        System.out.println("Trying to  mine block 2...");
        BLOCKCHAIN.get(1).mineBlock(DIFFICULTY);
        long endTime2 = System.currentTimeMillis();
        System.out.println("Mining block 1 cost " + (endTime2 - beginTime2));

        long beginTime3 = System.currentTimeMillis();
        BLOCKCHAIN.add(new Block("Hi i am the third block", BLOCKCHAIN.get(BLOCKCHAIN.size() - 1).hash));
        System.out.println("Trying to  mine block 3...");
        BLOCKCHAIN.get(2).mineBlock(DIFFICULTY);
        long endTime3 = System.currentTimeMillis();
        System.out.println("Mining block 1 cost " + (endTime3 - beginTime3));

        System.out.println("\nBlockchain is Valid: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(BLOCKCHAIN);
        System.out.println(blockchainJson);
    }

    /** 区块链有效 */
    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[DIFFICULTY]).replace('\0', '0');

        for (int i = 1; i < BLOCKCHAIN.size(); i++) {
            currentBlock = BLOCKCHAIN.get(i);
            previousBlock = BLOCKCHAIN.get(i - 1);

            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal!");
                return false;
            }

            if (!previousBlock.hash.equals(currentBlock.preHash)) {
                System.out.println("Previous Hashes not equal!");
                return false;
            }

            if (!currentBlock.hash.substring(0, DIFFICULTY).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}
