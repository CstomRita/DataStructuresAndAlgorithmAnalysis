package Capter3;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 下午4:49 2018/8/20
 * @ Description：编写一个程序通过改变第几位字母替换至少可以变成至少15种单词的单词（改变的字母是同一位置上的）
 * 参数List<String>words是单词列表,返回符合要求的单词列表(单词1，List<相似单词列表>)
 * 最优解：
 *  0 不要思维定式，不要想着拿出一个单词遍历去查找和他差一个单词的是否在list里，顺序读取两个单词判断是否差一个字母
 *  1 按照单词长度划分组，同一长度的单词比较是否只差一个字母
 *  2 如何判断差一个字母——优化，根据单词长度从第0位到最后一位，依次删除第几位字母，剩下的相等的就是符合要求的
 * @ Modified By：
 * @Version: 3.0-最优解$
 */
public class Solution {

    //主程序入口
    public Map<String,List<String>> getWordList(List<String> words) {
        //存放结果
        Map<String,List<String>> resultList = new TreeMap<String, List<String>>();

        //按照长度划分<长度，list<单词列表>>
        Map<Integer,List<String>> wordsByLength = new TreeMap<Integer, List<String>>();
        for(String word : words) {
            insertListByKey(wordsByLength,word.length(),word);
        }
        //单独拿出每个map单独比较
        //map遍历 entry entryset
        for(Map.Entry<Integer,List<String>> entry : wordsByLength.entrySet()) {
            //entry就是一个数 对应 一个list了
            List<String> sameLengthWords = entry.getValue();
            int length = entry.getKey();//单词长度

            for(int i = 0; i < length; i++) {
                //删除第i位字母，并将删除后的作为key，把删除后相同的作为list存放在一个map里
                Map<String,List<String>> deleteOneCharWords = new TreeMap<String, List<String>>();
                for(String str : sameLengthWords) {
                    String key = str.substring(0,i)+str.substring(i+1);
                    insertListByKey(deleteOneCharWords,key,str);
                }
                //此时拿到删除i字母后剩下的单词 会有多个map
                //单独拿出其中一个map的list，循环以list中的每个元素做key，list添加剩余的元素
                for(List<String> samewords : deleteOneCharWords.values()){ //遍历拿出每个map的list samewords
                    for(String word1 : samewords){
                        for(String word2 : samewords) {
                            if(!word1.equals(word2)) { //不是同一个单词，就word1做key Word2做list 添加
                                insertListByKey(resultList,word1,word2);
                            }
                        }
                    }
                }
            }
        }
        return resultList;
    }
    public <T> void insertListByKey(Map<T,List<String>> map,T key, String value) {
        //按照给定的key，为Map<x，list<>>中的list insert value
        //由于key的类型不唯一，这里使用泛型方法，value就是单词String类型
        List<String> list = map.get(key);
            list.add(value);
            map.put(key,list);
    }
}
