package com.michael.demos.blockchain;

/**
 * 类功能描述:
 * <pre>
 *   区块
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/10/29 19:48
 */
public class Block {

    /** 存放数字签名 */
    public String hash;
    /** 前面块的签名 */
    public String preHash;
    /** 数据 */
    private String data;
    /** 时间戳 */
    private long timeStamp;

    private int nonce;

    public Block(String data, String preHash) {
        this.data = data;
        this.preHash = preHash;
        this.timeStamp = System.currentTimeMillis();
        this.hash = calculateHash();
    }

    /** 计算数字签名 */
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(preHash + timeStamp + nonce + data);
        return calculatedhash;
    }

    /** 挖矿 */
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!!" + hash);
    }
}
