package Capter6;

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


}
