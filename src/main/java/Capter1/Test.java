package Capter1;

import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 下午1:34 2018/8/16
 * @ Description：For Test
 * @ Modified By：
 * @Version: $
 */
public class Test {
    public static void main(String[] args) throws Exception {

        Collection<Object> collection = new Collection(new Object[]{1,2,3,4,"1233"});
        System.out.println(collection.getSize());
        collection.remove(collection.getSize()-2);
        System.out.println(Arrays.toString(collection.getNums()));
    }

}
