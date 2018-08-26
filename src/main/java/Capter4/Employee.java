package Capter4;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 上午10:00 2018/8/21
 * @ Description：自定义类型，用于在HashTABLE中存放，需要重写Hashcode()函数
 * @ Modified By：
 * @Version: 1.0$
 */
public class Employee {
    private String name;
    private int age;

    @Override
    public int hashCode() {
        return name.hashCode();//将name的哈希值作为key
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Employee && this.name.equals(((Employee)obj).name);//是否是这个类 且 name是否相等
    }
}
