package Capter1;

import java.util.Arrays;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 上午9:06 2018/8/16
 * @ Description：第一題：選擇問題：N個數中選擇第K個最大者
 * @ Modified By：
 * @Version: 1.0$
 */
public class SelectionProblem {

    //排序，選擇快速排序。。
    private static void sortNum(int[] nums,int start,int end){
        if(start >= end ) {
            return;
        }
        int i = start;
        int j = end;
        int base = nums[start];
        while(i <= j) {
            while(nums[i] > base && i < end) {
                i++;
            }
            while(nums[j] < base && j > start) {
                j--;
            }
            if(i <= j) {
                swap(i,j,nums);
                i++;
                j--;
            }
        }
        //最後的i j 是不相等差一位的
        if(i > start) {
            sortNum(nums,start,j);//i比較排到j這樣過濾掉排好的那一位
        }
        if(j < end) {
            sortNum(nums,i,end);//j比較排到i
        }
    }

    private static void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

   private static int[] kMaxSort(int[] nums,int k) {
        //第一種方法：選擇數組中前K個最大值，取前K個值做初始值并排序，依次遍歷剩下的元素，如果比第K個值小則忽略，否則將其放到正確的位置
       int[] result = new int[k];
       for(int i = 0; i < k; i++) {
           result[i] = nums[i];
       }
       sortNum(result,0,k-1);
       System.out.println(Arrays.toString(result));
       for(int j = k; j < nums.length; j++) {
           if(nums[j] > result[k-1]) {
               for(int index = k - 2;index >= 0; index--) {
                   if(nums[j] > result[index]) {
                       result[index + 1] = result[index];
                       if(index == 0) {
                           result[0] = nums[j];
                       }
                   }else {
                       result[index + 1] = nums[j];
                       break;
                   }
               }
           }
           System.out.println(Arrays.toString(result));
       }
       return result;
   }

   public static void sortSoulution(int[] nums ,int k) {
       //一個解決方案首先是判斷輸入條件的合法性
       //沒有判斷輸入條件合法性的方法最好不要定義為public，定義成private
       if(nums.length == 0 || k > nums.length) {
            System.out.println("輸入不合法");
            return;
        }
       System.out.println(Arrays.toString(kMaxSort(nums,k)));
   }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,5,8,2,4,8,1};
        sortSoulution(nums,4);


    }

}
