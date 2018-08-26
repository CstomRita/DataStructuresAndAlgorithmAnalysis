package Capter4.CuckooHash;

// 同来存放散列函数的集合
// 布谷鸟存放的对象类型必须实现了HashFamily接口，提供多个hash方法
// 提供一个接口
public interface HashFamily<T> {
    int hash(T t, int which);
    int getNumberOfFuctions();
    void generateNewFunctions();

}
