package Capter4.CuckooHash;

import java.util.Random;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 下午9:27 2018/8/21
 * @ Description：为String类实现HashFamily接口，利用随机数生成hash
 * @ Modified By：
 * @Version: $
 */
public class StringHashFamily implements HashFamily<String> {
    private int[] hashes;//存放哈希值
    public StringHashFamily(int i) {//有多少散射函数
        this.hashes = new int[i];
        generateNewFunctions();
    }
    public int hash(String s, int which) {
        int index = this.hashes[which];
        int hash = 0;
        for(int i = 0; i < s.length(); i++) {
            hash = hash * index + s.charAt(i);
        }
        return hash;
    }

    public int getNumberOfFuctions() {
        return hashes.length;
    }

    public void generateNewFunctions() { //随机生成
        for(int i = 0; i < hashes.length; i++) {
            this.hashes[i] = new Random().nextInt();
        }
    }
}
