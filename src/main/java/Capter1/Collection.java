package Capter1;

import java.util.ArrayList;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 上午11:13 2018/8/16
 * @ Description：13題 定義一個泛型類Collection
 * @ Modified By：
 * @Version: $
 */
public class Collection<T> {
    private Object[] nums;
    private int size;
    public Collection(Object[] args) {
        this.nums = args;
        this.size = args.length;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public T[] getNums() {
        return  (T[])this.nums;
    }
    public void setNums(T[] nums) {
        this.nums = nums;
    }
    public boolean isEmpty() {
        return (nums.length == 0);
    }
    public void makeEmpty() {
        this.nums = new Object[]{};
        this.size = 0;
    }
    public void insert(T t) {
        Object[] newOne = new Object[size+1];
        System.arraycopy(this.nums,0,newOne,0,size);
        newOne[size+1] = (Object)t;
        this.nums = newOne;
    }
    public void remove(int index) {
        //不要忘記判斷條件,index的條件和nums是否為空
        if(index >= this.size || index < 0 || this.size == 0) {
            return;
        }
        Object[] newOne = new Object[size-1];
        System.arraycopy(this.nums,0,newOne,0,index);
        System.arraycopy(this.nums,index+1,newOne,index,this.size-index-1);
        this.nums = newOne;
    }
    public boolean isPresent(T t) {
        for(Object obj : nums){
            if(obj.equals(t)) {
                return true;
            }
        }
        return false;
    }


}
