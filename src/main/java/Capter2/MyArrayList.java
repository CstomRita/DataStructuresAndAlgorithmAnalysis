package Capter2;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 下午1:22 2018/8/18
 * @ Description：实现ArrayList
 * 1 保持基础数组，数组的容量，以及存放在MyArrayList上的当前项数
 * 2 提供改变基础数组容量的机制：拷贝到新数组，并允许虚拟机回收老数组
 * 3 提供get set方法
 * 4 提供基本的size isEmpty clear remove 以及不同版本的add，如果数组大小和容量相同那么add还需要增加容量(2n+1)加一是为了解决大小是0的情况
 * 5 提供Iterator接口
 * @ Modified By：
 * @Version: 1.0$
 */
public class MyArrayList<T> {
    private final int DEFAULT_CAPACITY = 10;//默认数组容量

    private int size;//数组中具体存放的数 数量 数组的容量则是list.length
    private Object[] list;//由于数组的协变性，需要定义为Object[]而非T[]

    public MyArrayList() {
        clear();
    }
    public int size(){
        return this.size;
    }
    public boolean isEmpty() {
        return this.size == 0 ;
    }
    public void clear() {
        this.size = 0;
        this.list = new Object[DEFAULT_CAPACITY];
    }
    public T get (int index) {
        if(index < 0 || index >= this.size) {
            throw new ArrayIndexOutOfBoundsException(0);//溢出
        }
        return (T)list[index];
    }

    public void set(int index, T t) {
        if(index < 0 || index >= this.size) {
            throw  new ArrayIndexOutOfBoundsException();
        }
        this.list[index] = t;
    }

    //创建一个方法用来扩充容量,新建一个length的数组，size不变
    public void ensureCapacity(int capacity) {
        if(capacity < this.size) {
            return;
        }
        Object[] oldList = this.list;
        this.list = new Object[capacity];
        for(int i = 0; i < this.size; i++) {
            this.list[i] = oldList[i];
        }

    }
    public void remove(int index) {
        if(index < 0 || index >= this.size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for(int i = index; i < this.size; i++) {
            list[i] = list[i+1];
        }
        this.size--;
    }
    public void add(int index,T t) {
        //add的index检查允许size，不允许size+1
        if(index < 0 || index > this.size) {
            throw  new ArrayIndexOutOfBoundsException();
        }
        //在判断是否需要扩充，当size和容量一致时
        if(list.length == this.size) {
            ensureCapacity(this.size * 2 + 1);
        }
        this.size++;
        for(int i = index + 1; i < this.size; i++) {
            list[i] = list[i-1];
        }
        list[index] = t;
    }
    public void add(T t) {
        add(this.size,t);
    }
    public String toString() {
        String result = "[";
        for(int i = 0; i < this.size - 1; i++) {
            result += list[i] + ",";
        }
        return result+list[this.size - 1]+"]";
    }

}
