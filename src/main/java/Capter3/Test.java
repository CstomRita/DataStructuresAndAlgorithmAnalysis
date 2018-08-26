package Capter3;

import java.util.*;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 下午5:04 2018/8/19
 * @ Description：For test
 * @ Modified By：
 * @Version: $
 */
public class Test {

    public static void main(String[] args) {
//        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
//        tree.insert(6);
//        tree.insert(2);
//        tree.insert(1);
//        tree.insert(4);
//        tree.insert(7);
//        tree.insert(8);
//        tree.insert(0);
//        tree.remove(2);
//        tree.print();
    }
    static class BasicGenerator<T> implements Generator<T> {

        private Class<T> type;//类型

        public BasicGenerator(Class<T> type) { //传入参数
            this.type = type;

        }
        public  T next() {
            try {
                return this.type.newInstance();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }
}
