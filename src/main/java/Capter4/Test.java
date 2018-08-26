package Capter4;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 上午10:55 2018/8/21
 * @ Description：For test
 * @ Modified By：
 * @Version: $
 */
public class Test {
    public static void main(String[] args) {
        //HashTableWithSeparateChaing<Integer> list = new HashTableWithSeparateChaing<Integer>();
        HashTableWithQuadraticProbing<Integer> list = new HashTableWithQuadraticProbing<Integer>();
        System.out.println(1^2);
        list.insert(89);
        list.print();

    }
}
