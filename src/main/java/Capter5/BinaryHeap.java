package Capter5;


/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 下午5:09 2018/8/23
 * @ Description：二叉堆，用来实现优先队列，
 * 二叉堆由数组实现
 * 正常入队，出队时删除最小值，
 * 实现enqueue dequeque和构造方法
 * @ Modified By：
 * @Version: 1.0$
 */
public class BinaryHeap <T extends Comparable<? super T>> {

    private int size; //实际存放的大小
    private Object[] heap;
    private final int DEFALUT_HEAP_SIZE = 10; //默认数组的大小，记住数组是从下标1开始的

    public BinaryHeap() {
        //创建空二叉堆
        this.size = 0;
        this.heap = new Object[DEFALUT_HEAP_SIZE];
    }

    public BinaryHeap(T[] list) {
        //提供接收数组的构造器，将list建立大顶堆
        this.size = list.length;
        buildHeap(list);
    }
    public void buildHeap(T[] list) {
        //利用下滤，对每个i位置上不满足序列的排序
        // 下滤要求下层是排好序的
        // 所以要从底到顶
        this.heap = new Object[this.size+DEFALUT_HEAP_SIZE];
        int i = 1;
        for(T t : list){
            this.heap[i++] = t;
        }
        for(int j = this.size/2; j >= 1; j--) {// 父节点开始--
            downSort(j);
        }
    }

    public void enqueue(T t) {
        //从下角标1 开始，父节点 i/2
        //入队，上滤
        // 上滤：从底层向上层查找适合t的位置，找父节点
        //如果t的父节点小于t 直接插入
        //否则把t的父节点拉下来，在t的父节点的位置上查找
        this.size++;//先加1 这时size表示的是底层要插入的空穴i
        int i = this.size;
        for(this.heap[0] = t;i >= 1; i = i/2) { // i是父节点，向上范围 >=1
            if(((T)this.heap[i/2]).compareTo(t) > 0){ //父节点比较大，下移
                this.heap[i] = this.heap[i/2];
            }else{
                break;
            }
        }
        this.heap[i] = t;
    }

    public T dequeue() {
        //出队，返回删除最小元素(root),把最后一个元素拿到root处，下滤排序
        T min = (T)this.heap[1];
        this.heap[1] = this.heap[this.size];
        this.size--;
        downSort(1);
        System.out.println(min);
        return min;
    }
    private void downSort(int index) {
        // index位置的元素不符合二叉堆特性
        // 下滤查找适合他的位置并排序
        //下滤，从顶层向下查找适合当前元素的位置，找子节点
        //下层是符合排序的
        // i的子节点 2i 2i+1
        // 如果i的子节点比i要小，把最小的子节点上移，在子节点的位置继续向下查找 直到i的子节点不比i小
        T t = (T)this.heap[index];//拿到这个元素
        //有两个子节点，先找最小的子节点
        int i = index;
        int child ;//第一个子节点
        for(; i <= this.size / 2; i = child){ // i是父节点，向下 i <= size/2
            child = 2 * i;
            if(child != this.size && ((T)this.heap[child]).compareTo((T)this.heap[child+1]) > 0) { //找最小的子节点,如果只有一个节点那就是2i
                child++;
            }
            if(((T)this.heap[child]).compareTo(t) < 0) {
                //把child上移
                this.heap[i] = this.heap[child];
            }else{
                break; //否则跳出，子节点大，i就是要放的位置
            }
        }
        this.heap[i] = t;
    }

}
