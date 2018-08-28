package Capter6;

import java.util.Arrays;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 下午3:42 2018/8/27
 * @ Description：排序算法,设置从小到大排序
 * @ Modified By：
 * @Version: $
 */
public class Sort<T extends Comparable<? super T>> {

    public T[] nums;

    public Sort(T[] nums) {
        this.nums = nums;
    }

    //算法1：插入排序
    //插入排序原理：循环nums[i]时设定，nums[0]~nums[i-1]是排好序的
    //问题就变成：拿出元素nums[i]，将其插入到已经排好序的元素中
    //应该从i-1下标向前遍历，因为前面插入一个后面的元素必然要后移，在寻找插入位置的过程中后移
    public void insertSort() {
        int j;
       for(int i = 1; i < nums.length; i++) {
           T temp = nums[i];
           for(j = i - 1; j >= 0 && (temp.compareTo(nums[j])) < 0; j--) {
               nums[j + 1] = nums[j];
           }
           //j和前i-1个位置比较，比较的位置是j，空闲的位置是j+1
           nums[j + 1] = temp;
       }
    }

    //算法2:希尔排序，相当于分组的插入排序
    //间隔hk选择length/2 /2 /2 。。。直到1
    public void shellSort() {
        int hk = nums.length / 2;
        for( ; hk >= 1; hk /= 2) {
            //单次hk排序，从i=0....hk-1排序hk次
                for(int j = hk; j < nums.length; j ++) {
                    //从j = i+hk 开始插入排序，认为前 j-hk是排好序的
                    T temp = nums[j];
                    int k;
                    for(k = j - hk; k >=0 && (temp.compareTo(nums[k])) < 0; k -= hk) {
                        //拿出nums[j]，从k = j - hk比较，比较位置 k 空闲位置 k+hk
                        nums[k + hk] = nums[k];
                    }
                    nums[k + hk] = temp;
                }
        }
    }

    //算法3：堆排序

    public void heapSort() {
        // 首先构建大顶堆，父节点上滤执行下滤操作
        for(int i = (nums.length - 1)/2 ; i >= 0; i--) {
            downSort(i,nums.length);
        }
        //将最大者放到最后面然后size--
        T temp;
        for(int j = nums.length - 1; j > 0; j--) {
            temp = nums[j];
            nums[j] = nums[0];
            nums[0] = temp;
            // size--,就是j，下滤
            downSort(0,j);
        }
    }
    private void downSort(int index, int end) { // end是结束元素+1
        //将index位置的元素放置到它应有的位置
        T temp = nums[index];
        //这里数组是从下标0开始的，注意父子节点下标
        //下滤 和子节点比较, 大顶堆找最大值
        int maxchild ;
        for( ; 2 * index + 1 < end ; ) {
            maxchild = 2 * index + 1;
           if(maxchild + 1 < end  && (nums[maxchild].compareTo(nums[maxchild+1])) < 0) {
               maxchild ++;
           }
            if(temp.compareTo(nums[maxchild]) < 0) {
               nums[index] = nums[maxchild];
               index = maxchild;
            }else{
               break;
            }
        }
        nums[index] = temp;
    }

    //算法4：归并算法
    //归并算法采用的是一种分治思想
    //分：将整个数组分成两部分，左边一部分再分成两部分，再分成两部分......直到最左边分的一部分只有一个元素(left = right)，再开始分右边也只剩下两个元素，这时处于最深层次递归 0 1,两个参数left rigth
    //治：最深层次的递归左半部分、右半部分只各有一个元素，比较大小放入 0 1，然后是 2 3 ，之后 01 23 两组四个元素比较......中间层次完成0~mid排序，右半部分再递归完成mid+1 ~length-1的排序
    // 最后一次计算时0~mid和mid+1 ~length-1合并，由此合并函数需要 left、mid、rigth三个参数
    //分治思想是递归十分有效地用法
    //合并函数如何合并，设置一个临时数组拷贝数组元素，三个指针分别指向左部分、右部分、和要排序的数组left
    //设置多个指针游标对于数组是一种常见的思想

    public void mergeSort() {
        Object[] tempNum = new Object[nums.length]; //临时数组
        mergeSort(0,nums.length-1,tempNum);
    }

    private void mergeSort(int left, int right, Object[] tempNum) {
        //两个参数left rigth
        // 递归出口：left = right时return
        if(left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(left,mid,tempNum);
        mergeSort(mid+1,right,tempNum);
        merge(left,mid,right,tempNum);
    }



    private void merge(int left, int mid, int right, Object[] tempNum) {
        // 1 复制元素
        for(int i = left; i <= right; i++) {
            tempNum[i] = nums[i];
        }
        // 2 归并
        int mid_index = mid + 1;
        int index = left;
        while(left <= mid && mid_index <= right) {
            if(((T)tempNum[left]).compareTo(((T)tempNum[mid_index])) < 0) {
                nums[index++] = (T)tempNum[left++];
            }else {
                nums[index++] = (T)tempNum[mid_index++];
            }
        }
        while(mid_index <= right) {
            nums[index++] = (T)tempNum[mid_index++];
        }
        while (left <= mid) {
            nums[index++] = (T)tempNum[left++];
        }
    }

    //除此之外，归并排序还可以使用非递归的方法实现，称为自底向上的归并排序
    //它的思想是直接从归并开始，将所有相邻的两个 1 个元素一一归并成两个元素
    //再再次基础上，将相邻的两个二元素 归并为四个元素.....循环直到所有
    public void mergeSort2() {
        Object[] tempNum = new Object[nums.length]; //临时数组
        for(int i = 1 ; i < nums.length; i *= 2) { //i 代表的是每次merge的元素个数
            for(int j = 0; j+i < nums.length; j += 2*i) {
                //left从0开始，每经过两个i做一次循环，直到right = j+2*i-1，但是不是所有都可以是正好i的倍数，最后一次比较右边的个数可能小于i
                //由此以mid作为循环结果，必有mid + 1 <length，此时才会有左右两部分
                //right = min (j+2*i-1,length-1)
                merge(j,j+i-1,Math.min(j+2*i-1,nums.length-1),tempNum);
            }
        }
    }

    //算法5：快速排序
    //快速排序的算法依然是使用递归，每次随机选择一个中枢元，小于中枢元的排列在左边、大于中枢元的排列在右边
    // 设置左右两个指针，当指正i=j时，至此可以确定这个中枢元的位置就是i处
    //遍历左边left~i-1,再遍历右边 i+1 ~ right
    //在这个算法里有两个问题：
    //1 中枢元的选择：一种简单的快速排序就是选择第一个元素 nums[left] 或者最后一个元素nums[right]
    //2 是对和中枢元相等时如何操作：理想情况下我们希望相等的元素左边也有、右边也有，故在游标移动的过程中如果碰到了相等的元素依然停下准备交换
    // 虽然这种操作有可能是交换两个相等数这样不必要的操作，但它在最大程度上避免出现两个不均衡的子数组，也是唯一一种不会超过二次时间的可能
    // 所以左边只略过小于的，右边只略过大于的
    //3 在游标的每次移动中，都需要再次对边界检查，因为进入一个while循环时i<=j，第二个循环i++可能会越界，但是这时已经引入第一个循环，不会因为在循环过程中条件不满足而退出第一个循环，只会在下次进入前检查条件
    // 因此嵌套游标的第二个循环同样需要检查条件,而且要放在前面保证nums[i]是有元素的
/*
    private void quickSort(int left, int right) {
        if(left >= right) return;
        T temp = nums[left];
        int i = left;
        //按照道理，拿出的第一个元素不需要比较,但是如果这个temp正好是最小值的话，那就必须保留这个left i
        int j = right +1;
        while (i < j) {
            while(i < j && nums[--j].compareTo(temp) > 0) { //先移动再判断
                // i < --j && nums[j].compareTo(temp) > 0
                // 但是注意越界判断时的 j已经减一了，真正的j要加一，不满足大小时的j就是真实的j
                //而当前的这种写法，i < j比较的是已经移动了一位之后的现状是否满足条件，如果不满足就是移动之后i==j了，必然不能再次操作了
                //判断边界不要改变游标的值，因为不满足的话会影响下面，判断大小可以改变游标的值，因为即便不满足下面也是这个位置交换
                System.out.println(j);
            }
            while(i < j && nums[++i].compareTo(temp) < 0  ){
                System.out.println(i);
            }
            if(i < j) { //交换，如果i=j就不需要交换了
                T change = nums[i];
                nums[i] = nums[j];
                nums[j] = change;
            }
        }
        // i = j处，就是元素temp应该在的位置,交换 left 与 i 的元素
        System.out.println(i + "" +j);
        nums[left] = nums[i];
        nums[i] = temp;
        quickSort(left, i -1);
        quickSort(i + 1, right);
    }
*/
    public void quickSort() {
        quickSort2(0,nums.length-1);
    }

    //根据上面我们知道要考虑很多问题，是否越界等等，原因在于中枢元选取不当
    //尤其是当一个元素是最小元素时，这个快速排序相当于什么都没做
    //一般情况下，我们采用三数中值分割法选取中枢元

    private void quickSort2(int left, int right) {
        if(left >= right) return;
        //中枢元
        int mid = (left + right) / 2;
        if(nums[mid].compareTo(nums[left]) < 0) {
            T change = nums[mid];
            nums[mid] = nums[left];
            nums[left] = change;
        }
        if(nums[right].compareTo(nums[left]) < 0) {
            T change = nums[right];
            nums[right] = nums[left];
            nums[left] = change;
        }
        if(nums[right].compareTo(nums[mid]) < 0) {
            T change = nums[mid];
            nums[mid] = nums[right];
            nums[right] = change;
        }
        T temp = nums[mid];
        nums[mid] = nums[right-1];
        nums[right-1] = temp; //中枢元存在right-1
        //中枢数,因为这个中枢数是中间位置的值，所以他不会存在是最值的问题，不需要注意边界
        //哪怕是相等也会停住游标
        int i = left;
        int j = right - 1;
        while (i < j) {
            while(nums[--j].compareTo(temp) > 0) { }
            while(nums[++i].compareTo(temp) < 0  ){}
            if(i < j) { //交换，如果i=j就不需要交换了
                T change = nums[i];
                nums[i] = nums[j];
                nums[j] = change;
            }
        }
        nums[right-1] = nums[i];//中枢元 和 right-1
        nums[i] = temp;
        quickSort2(left, i -1);
        quickSort2(i + 1, right);

    }

    //快速排序改进3：在小数组下，length<10,快速排序的运行时间不如插入排序，故我们在递归中加一个条件判断
    //当分割成小数组时，采用插入排序之间排序
    private static final int CUTOFF = 10;
    private void quickSort3(int left, int right) {
        if (left >= right) return;
        if (right - left >= CUTOFF) {
            //中枢元
            int mid = (left + right) / 2;
            if (nums[mid].compareTo(nums[left]) < 0) {
                T change = nums[mid];
                nums[mid] = nums[left];
                nums[left] = change;
            }
            if (nums[right].compareTo(nums[left]) < 0) {
                T change = nums[right];
                nums[right] = nums[left];
                nums[left] = change;
            }
            if (nums[right].compareTo(nums[mid]) < 0) {
                T change = nums[mid];
                nums[mid] = nums[right];
                nums[right] = change;
            }
            T temp = nums[mid];
            nums[mid] = nums[right - 1];
            nums[right - 1] = temp; //中枢元存在right-1
            //中枢数,因为这个中枢数是中间位置的值，所以他不会存在是最值的问题，不需要注意边界
            //哪怕是相等也会停住游标
            int i = left;
            int j = right - 1;
            while (i < j) {
                while (nums[--j].compareTo(temp) > 0) { }
                while (nums[++i].compareTo(temp) < 0) { }
                if (i < j) {
                    T change = nums[i];
                    nums[i] = nums[j];
                    nums[j] = change;
                }
            }
            nums[right - 1] = nums[i];//中枢元 和 right-1
            nums[i] = temp;
            quickSort3(left, i - 1);
            quickSort3(i + 1, right);
        } else {
            insertSort();
        }
    }

    // 快速排序应用：选择问题，输出N个数中第K小(大)的元素
    // 快速选择，同样小数组下还是插入排序优于快速选择
    // 快速选择思路:同样选取中枢元，将其放在应该的位置，当前的位置i如果大于K，则说明第K小的元素在左半部分，递归 left - i-1中 K-left小的元素
    // 如果 i = K - 1，则说明i就是第k小的返回
    // 否则就是在右半部分 I i+1 ~ right的第 k - (i+1)小的元素
    //这里测试就不加插入排序了
    private T quickSelect(int left, int right, int k) {
        //递归出口，k =1,rigth <=left
        System.out.println("k"+k);
        if(left >= right) {
            return nums[left];
        }
        //中枢元
        int mid = (left + right) / 2;
        if (nums[mid].compareTo(nums[left]) < 0) {
            T change = nums[mid];
            nums[mid] = nums[left];
            nums[left] = change;
        }
        if (nums[right].compareTo(nums[left]) < 0) {
            T change = nums[right];
            nums[right] = nums[left];
            nums[left] = change;
        }
        if (nums[right].compareTo(nums[mid]) < 0) {
            T change = nums[mid];
            nums[mid] = nums[right];
            nums[right] = change;
        }
        T temp = nums[mid];
        nums[mid] = nums[right - 1];
        nums[right - 1] = temp; //中枢元存在right-1
        int i = left;
        int j = right - 1;
        while (i < j) {
            while (nums[--j].compareTo(temp) > 0) { }
            while (nums[++i].compareTo(temp) < 0) { }
            if (i < j) {
                T change = nums[i];
                nums[i] = nums[j];
                nums[j] = change;
            }
        }
        nums[right - 1] = nums[i];//中枢元 和 right-1
        nums[i] = temp;
        //新的逻辑从这开始。。。。。。。。。。
        System.out.println(i+"---");
        if(i >= k) {
            //左半部分
            return quickSelect(left,i-1,k-left);
        }else if(k == i + 1){
            return nums[i];
        } else{
            //右半部分
            return quickSelect(i+1,right,k-i);
        }
    }
    public T quickSelect(int k) {
        return quickSelect(0,nums.length-1,k);
    }
}
