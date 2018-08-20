package Capter3;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 下午2:46 2018/8/20
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface Generator<T> {

    /**
     - 用以产生新对象
     - @return
     */
    public T next();

}