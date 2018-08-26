package Capter4.CuckooHash;

import java.util.List;
import java.util.Random;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 下午7:58 2018/8/21
 * @ Description：布谷鸟散列
 * @ Modified By：
 * @Version: $
 */
public class CuckooHashTable<T> { //
    private static final double Max_LOAD = 0.5;//超过这些，扩展散列表
    private static final int MAX_CHANGE = 5;//替换操作最大次数，超过就换新的散列函数
    private static final int MAX_NEW_COUNT = 1;//有时我们换好几次散列函数依然死循环冲突，这个限制换散列函数的次数，超过的话用扩展list的方法解决冲突
    private static final int DEFALUT_TABLE_SIZE = 11;//默认TABLEsize
    private int size;

    private int functionNumber;//有多少hash函数
    private HashFamily<? super T> functions ;
    private Object[] list;//这里直接创建了一个一位数组，其实是简化了，按道理应该是二维的表示第几张表的第几个位置

    //是否为素数
    private boolean isPrime(int num) {
        if (num == 2 || num == 3) {
            return true;
        }
        if (num % 6 != 1 && num % 6 != 5) {
            return false;
        }
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
    //n后面的下一个素数
    private int nextPrime(int n) {
        boolean state = isPrime(n);
        while (!state) {
            state = isPrime(++n);
        }
        return n;
    }
    //调用第几个hash函数并Mod tableSize
    private int myHash( T t,int which) {
        int hash = this.functions.hash(t,which);
        hash %= list.length;
        if(hash < 0) {
            hash += list.length;
        }
        return hash;
    }
    // 查找,因为有多个散列表，有个元素会散列出多个位置，要全部查找
    private int contains(T t) {
        for(int i = 0; i < this.functionNumber; i++) {
            int hash = myHash(t,i);//第i个散列表的位置
            if (list[hash] != null && list[hash].equals(t)) {
                return hash;
            }
        }
        return -1;//未找到
    }
    public void remove(T t) {
        int index = contains(t);
        if(index != -1) {
            list[index] = null;
            this.size--;
        }
    }

    private void expand() {
        T[] oldList = (T[])this.list;
        this.list = new Object[nextPrime(this.list.length)];
        this.size = 0;
        for(T t : oldList) {
            if(t != null) {
                insert(t);
            }
        }
    }
    private void reBuild() {
        //获取新的散列函数并填充，不改变list大小
        this.functions.generateNewFunctions();//重新生成散列函数
        T[] oldList = (T[])this.list;
        this.list = new Object[this.list.length];
        this.size = 0;
        for(T t : oldList) {
            if(t != null) {
                insert(t);
            }
        }
    }
    //插入是最为繁琐的，
    // 一方面要替换插入
    //如果替换操作超过了多少，为了防止死循环，要换新的散列函数并填充原来数组 rebuild
    //还要考虑到size大于length/2时要扩展 expand
    public void insert(T t) {
        if(contains(t) != -1){
            return;
        }
        if(this.size >= this.list.length * Max_LOAD) {
            expand();
        }
        //具体的插入
        int newCount = 0;//生成新散列的次数
        while(true) {
            for(int change = 0; change < MAX_CHANGE; change++) { //可以交换MAX_ChanGE次
                //查找每一个散列函数 看有没有空位置
                for(int i= 0; i < this.functionNumber; i++) {
                    int index = myHash(t,i);
                    if(list[index] == null) {
                        list[index] = t;
                        this.size++;
                        return;
                    }
                }
                //找不到可用位置，就要替换，这里采用随机替换
                int oldIndex = -1;
                int newIndex = -1;
                int count = 0;//随机生成多少次
                while(count ++ < 5 && newIndex == oldIndex){ //随机
                     newIndex = myHash(t,new Random().nextInt(this.functionNumber));//随机找一个散列
                }
                //找到交换的newIndex
                oldIndex = newIndex;
                T tToChange = (T)list[oldIndex];
                list[oldIndex] = t;
                t = tToChange;
            }
            //交换MAXCHANGE次依然冲突，根据条件选择是生成新的散列表还是扩充
            if(newCount < MAX_NEW_COUNT) { //如果没到5 生成新散列
                reBuild();
            }else{//否则扩充
                newCount = 0;
                expand();
            }
        }
    }
    public CuckooHashTable(HashFamily<? super T> functions) { //传递hashFunction
        this.functions = functions;
        this.functionNumber = functions.getNumberOfFuctions();
        this.size = 0;
        this.list = new Object[DEFALUT_TABLE_SIZE];
    }
}
