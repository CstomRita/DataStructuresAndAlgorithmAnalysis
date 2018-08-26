package Capter4.CuckooHash;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 下午9:36 2018/8/21
 * @ Description：for test
 * @ Modified By：
 * @Version: $
 */
public class Test {
    public static void main(String[] args) {
        StringHashFamily stringHashFamily = new StringHashFamily(3);
        CuckooHashTable<String> table = new CuckooHashTable<String>(stringHashFamily);
        table.insert("123");
        Map map = new HashMap();
        System.out.println(((TreeMap)map).getClass());
    }
}
