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

    private static Sort<Integer> sort = new Sort(new Integer[]{1,2,5,3,4});

    public static void main(String[] args) {
//        sort.quickSort();
//        System.out.println(Arrays.toString(sort.nums));
        System.out.println(sort.quickSelect(4));
    }
}
