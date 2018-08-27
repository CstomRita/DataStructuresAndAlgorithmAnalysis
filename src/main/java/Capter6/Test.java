package Capter6;

import java.util.Arrays;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 下午4:05 2018/8/27
 * @ Description：fortest
 * @ Modified By：
 * @Version: $
 */
public class Test {

    private static Sort<Integer> sort = new Sort(new Integer[]{4,8,1,0,4,9,7});

    public static void main(String[] args) {
        sort.mergeSort2();
        System.out.println(Arrays.toString(sort.nums));
    }
}
