package Capter2;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 下午2:03 2018/8/18
 * @ Description：For Test
 * @ Modified By：
 * @Version: 1.0$
 */
public class Test {
    public static void main(String[] args) {
//        MyArrayList<Integer> list = new MyArrayList<Integer>();
//        list.add(3);
//        list.add(5);
//        list.remove(0);
//        System.out.println(list.size());
//        System.out.println(list.toString());
        MyLinkedList<Integer> list1 = new MyLinkedList<Integer>();
        list1.add(0,1);
        list1.add(1,4);
        list1.add(2,6);
        list1.add(1,6);

        LinkedList   list2 = new LinkedList();
        list2.addFirst(1);
        list2.addFirst(3);
        System.out.println(list2.getFirst());
        System.out.println(list2);
    }
}
