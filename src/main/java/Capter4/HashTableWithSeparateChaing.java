package Capter4;

import java.util.LinkedList;
import java.util.List;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 上午10:08 2018/8/21
 * @ Description：使用分离链接法处理关键词冲突的问题
 * 将散射到同一个值的元素保留在一个链表中
 * insert时 若该元素是一个新元素则将他插入到链表的前端
 * @ Modified By：
 * @Version: $
 */
public class HashTableWithSeparateChaing<T> {

    private List<T>[] list; //每一个角标list[i]下都是一个链表list
    private static final int DEFALUT_SIZE = 11; //默认散射表的大小,值得注意的是散射表的大小最好选定素数（只能被1和自己整除的数字）
    private int size;//存储了多少数据

    public HashTableWithSeparateChaing() {
        //构造函数 list为空 size是defalut
        this.size = 0;
        list = new LinkedList[DEFALUT_SIZE];
        for(int i = 0; i < list.length; i++) {
            list[i] = new LinkedList<T>();
        }
    }
    public int myHash(T t) {
        //此处散列函数设置为hashcode % size
        int hashValue = t.hashCode() % list.length;
        if(hashValue < 0) {
            hashValue += list.length;
        }
        return hashValue;
    }

    public void insert(T t) {
      List getList =  list[myHash(t)];
      if(!getList.contains(t)) {
          getList.add(t);
          this.size++;
      }
    }
    public void remove(T t) {
        List getList =  list[myHash(t)];
        if(getList.contains(t)) {
            getList.remove(t);
            this.size--;
        }
    }
    public int size() {
        return this.size;
    }
    public void print() {
        for(int i = 0; i < list.length; i++) {
            System.out.println("第"+i+"行："+list[i].toString());
        }
    }
}
