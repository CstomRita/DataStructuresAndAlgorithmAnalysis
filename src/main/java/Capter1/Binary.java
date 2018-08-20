package Capter1;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 上午10:28 2018/8/16
 * @ Description：第5題:輸出二進制表示1的個數
 * @ Modified By：
 * @Version: $
 */
public class Binary {

    //利用遞歸算法：
    // 出口：0是0 1是1
    //條件：奇數1的個數是n/2的個數加1，如果它是偶数,那么n的二进制中1的个数与n/2中1的个数是相同的
    //那麼3就是2 4就是1
    //這裡用動態規劃代替遞歸
    //題目中沒有考慮負數的情況，加上本身這也一道算法題，
    public static int oneInBinary(int n) {
        if( n < 0) {
            n = - n ;
        }
        if(n <= 1) {
            return n;
        }
        int[] result = new int[n];
        result[0] = 0;
        result[1] = 1;
        for(int i = 2;i < n; i++) {
            if(i%2 == 0) {
                result[i] = result[i/2];
            }else{
                result[i] = result[i/2] + 1;
            }
        }

        return  result[n-1];
    }

    public static int numsOfOneInBinary(int n) {
        //在Java中運用最多的就是位運算
        //n-1和n做&運算會抵消掉N最右邊的1
        //直到變成0
        //具體原來看二進制
        int result = 0;
        while(n!= 0) {
            n = n & (n-1);
            result++;
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(oneInBinary(18));
        System.out.println(numsOfOneInBinary(18));
    }
}
