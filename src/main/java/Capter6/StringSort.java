package Capter6;

import java.util.ArrayList;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 下午4:27 2018/8/28
 * @ Description：字符串排序
 * @ Modified By：
 * @Version: $
 */
public class StringSort {
    private String[] arr;
    public StringSort(String[] arr) {
        this.arr = arr;
    }

    //字符串数组排序的主要算法 是基数排序
    //对字符串中的每一个字符做桶排序
    //字符基于ASC码，有256个值
    private static final int BUTKET = 256;

    //算法1：普通基数排序，每个字符串具有相同长度
    //创建一个256个容量的List[]数组，一开始每个list都是空
    //从字符串的最末一位开始，对各个字符串的最后一位字符进行桶排序
    //桶排序：1 依据每个字符的值放入数组[值]单元中 2 依据数组0-length的顺序读出值即为顺序的，将这个顺序的字符串放入arr中，便可以保证按下一位读取中，在同一个桶里的字符串是末位小的先进去保证有序
    public void radixSortA() {
        ArrayList<String>[] list_arr = new ArrayList[BUTKET];
        //先初始化list数组，每个为空list
        for(int i = 0; i < BUTKET; i++) {
            list_arr[i] = new ArrayList<String>();
        }

        for(int index = arr[0].length()-1; index >= 0; index--) {

            for(String s : arr) {
                list_arr[s.charAt(index)].add(s);
            }
            int i = 0;
            for(ArrayList<String> list : list_arr) {

                for(String s : list) {
                    arr[i++] = s;
                }
                //每个list循环之后清空，因为list[]没有清空的方法，需要在循环时清空list
                list.clear();
            }
        }

    }

    //算法2：改进，不使用List[]数组，改用两个普通数组：一个int数组count，count[k+1]表示桶k中元素的个数,count[k]表示k-1元素的个数。。。。
    //那么我们可以知道count[k]+count[k-1]累计加起来就是小于k的元素个数
    //字符串数组从小到大排列，小于k有n个，那么元素k存放在哪？  n+1
    //另外一个数组temp作为输入数组的临时数组，用来存放每次桶排序之后的顺序数组
    public void radixSortB() {
        int[] count = new int[BUTKET+1];
        String[] temp = new String[arr.length];

        for(int index = arr[0].length()-1; index >= 0; index--) {
            //放入数组
            for(int i = 0; i < arr.length; i++) {
                count[arr[i].charAt(index) + 1]++;
            }
            //count[k]+count[k-1]
            for(int j = 1; j < count.length; j++) {
                count[j] += count[j-1];
            }
            //temp作为输入数组的临时数组，用来存放每次桶排序之后的顺序数组
            for(int k = 0; k < arr.length; k ++) {
                temp[count[arr[k].charAt(index)] + 1] = arr[k];
            }

            // 桶排序之后交换 arr temp进入下一位循环
            String[] change = arr;
            arr = temp;
            temp = change;
            //注意这里的交换，只是交换了引用，实际上arr数组的内容并没有变化
            //这样就有这样的一个问题：如果交换了偶数次，arr重新指向了arr，而arr又是排好序列的
            //但是如果交换了奇数次，排好序列的是temp，arr数组的内部依然是乱序的，所以需要在最后交换元素
        }
        if(arr[0].length() % 2 == 1) { //字符串有多少字符，就进行多少桶排序，自然交换多少次引用
            for(int i = 0; i < arr.length; i++) {
                arr[i] = temp[i];
            }
        }
    }

    //算法改进3：原先的都是字符串数组中字符串相同长度，此处变成不同长度
    //不同长度时首先要比较字符串长度大小，按照长度排序，此处长度是一个小数字可以采用桶排序,
    //采用第一种方法的List[]数组改变
    //用于排序字符串大小的list数组大小为arr中最长的字符串长度+1（因为数组从0开始），这里采用参数定义
    //用于排序内容的List数组大小为256

    public void radixSortC(int maxLength) {

       ArrayList<String>[] length_list = new ArrayList[maxLength+1];
       for(int i = 0; i < length_list.length; i++) {
           length_list[i] = new ArrayList<String>();
       }
       for(String s : arr) {
           length_list[s.length()].add(s);
       }
       int j = 0;
       for(ArrayList<String> list : length_list) {
           for(String s : list) {
               arr[j++] = s;
           }
       }
       ////////////至此arr中按照字符串长度大小排序/////////////////////
        ArrayList<String>[] list_arr = new ArrayList[BUTKET];
        for(int i = 0; i < BUTKET; i++) {
            list_arr[i] = new ArrayList<String>();
        }
        //对于不同样长的字符串，首位对齐，
        //我们对各个数组存放的元素了解一下
        // length_list[i+1]中存放的是长度为i+1的字符串，他们在arr中的起始下标为 maxLength-length_list[i+1].size()
        //也就是从这个下标开始，有第i为字符串
        int startIndex = arr.length;
        for (int index = maxLength - 1; index >= 0; index--) {
            startIndex -= length_list[index+1].size();

            for(int k = startIndex; k < arr.length; k++) {
                list_arr[arr[k].charAt(index)].add(arr[k]);
            }
            int id = startIndex;//从有此一位的下标开始顺序，不要动用前面短字符串位置
            for(ArrayList<String> list : list_arr) {

                for(String s : list) {
                    arr[id++] = s;
                }
                //每个list循环之后清空，因为list[]没有清空的方法，需要在循环时清空list
                list.clear();
            }
        }
    }

}
