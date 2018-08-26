package Capter4;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 上午11:27 2018/8/21
 * @ Description：探测散列表，使用平法探测法消除冲突
 * 探测散列表存储的数据 是为其分配另外的存储单元（EntrySet）
 * 又因为探测散列表中删除属于懒惰删除
 * 我们需要创建一个存储单元类 存放数据 和 删除标记 activeIndex
 *
 * @ Modified By：
 * @Version: $
 */
public class HashTableWithQuadraticProbing<T> {

    private class HashEntrySet<T> {
        private T data;
        private boolean activeIdex;//删除 不存活 false

        //我们需要对activeIndex设置set方法
        public void setActiveIdex(Boolean activeIdex){
            this.activeIdex = activeIdex;
        }
        public HashEntrySet(T t) {
            this.data = t;
            this.activeIdex = true;
        }
    }

    private int size;//Table元素大小
    private HashEntrySet<T>[] list;//存放数据的单元集合
    private final int DEFALUT_TABLE_SIZE = 10;//默认散射列表大小，
    // 存放的数据过多则需要扩充list.length，那时list.length != Defalut_size了
    //按照探测散列表的要求，当size > list.length/2时就需要扩充散列表了

    public HashTableWithQuadraticProbing() {
        //初始化
        this.size = 0;
        //集合为空
        this.list = new HashEntrySet[DEFALUT_TABLE_SIZE];
        for(int i = 0; i < list.length; i++) {
            list[i] = null;
        }
    }

    public int myHash(T t) {
        int hashValue = t.hashCode() % list.length;
        if(hashValue < 0) { //考虑溢出为负数
            hashValue += list.length;
        }
        return hashValue;
    }

    public int contains(T t) { //获取t的位置
        int index = myHash(t);//获取这个数，本该存放的位置，然后按照冲突i=1 i=2 i=3的向后遍历
        int i = 1;
        while( list[index] != null &&!list[index].data.equals(t)){
            //如果查到的list[index]==null则表示不存在这个数，因为如果有这个数这个位置不会为空
            //同样如果插入，插入的位置也应该是这个地方
            index = index + (2*i-1); // i^2 - (i-1)^2 --> 2i-1
            i++;
            if(index > list.length) {
                index -= list.length;//例如9再向后遍历应该是0，循环遍历
            }
        }
        return index;
    }

    public void insert(T t) {
        int i = contains(t);
        if(list[i] == null) {
            list[i] = new HashEntrySet<T>(t);
            this.size++;
        }else if(!list[i].activeIdex) { //activeIndex false
            list[i].setActiveIdex(true);
            this.size++;
        }

        if(size >= list.length/2) {
            //需要扩充list
        }

    }

    public void remove(T t) {
        int i = contains(t);
        if(list[i]!=null && list[i].activeIdex) { //不为空且active才能删除
            list[i].setActiveIdex(false);
            this.size--;
        }
    }
    public void print(){
        for(int i = 0; i < list.length; i++) {
            if(list[i] != null && list[i].activeIdex) {
                System.out.println("第"+i+"行"+list[i].data);
            }
        }
    }
}
