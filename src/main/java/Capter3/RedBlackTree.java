package Capter3;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 下午4:13 2018/8/24
 * @ Description：红黑树的插入删除实现
 * @ Modified By：
 * @Version: $
 */
public class RedBlackTree<T extends Comparable<? super T>> {
    private static class RedBlackTreeNode<T>{
        //除了存放数据、左右子节点、还需要存放一个color
        public T data;
        public RedBlackTreeNode<T> left;
        public RedBlackTreeNode<T> right;
        public int color;//设置0为红，黑为1

        public RedBlackTreeNode(T data, RedBlackTreeNode<T> left, RedBlackTreeNode<T> right, int color) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.color = color;
        }
    }

    private final int BLACK_COLOR = 1;
    private final int RED_COLOR = 0;

    private RedBlackTreeNode<T> header;//不是根节点，作为根节点的父节点
    private RedBlackTreeNode<T> nullNode;//指向NUll

    public RedBlackTree() {
        this.nullNode = new RedBlackTreeNode<T>(null,null,null,BLACK_COLOR);
        this.nullNode.left = this.nullNode.right = this.nullNode;//空节点左右儿子都是空节点
        this.header = new RedBlackTreeNode<T>(null,this.nullNode,this.nullNode,BLACK_COLOR) ;//header的左右儿子是空节点
    }

    private RedBlackTreeNode<T> singleLeft(RedBlackTreeNode<T> node) {
        RedBlackTreeNode<T> left = node.left;
        //改变t.left和t之间的关系
        left.right = node;
        node.left = left.right;
        //返回父节点 left
        return left;
    }
    private RedBlackTreeNode<T> singRight(RedBlackTreeNode<T> node) {
        RedBlackTreeNode<T> right = node.right;
        node.right = right.left;
        right.left = node;
        return right;
    }
    private RedBlackTreeNode<T> rotate(T t,RedBlackTreeNode<T> parent) { //parent是红节点的父节点，t是要插入到parent的子节点的子节点下的元素
        //只有父节点为红时需要旋转
        //判断是左旋转、右旋转、还是双旋转
        if(compare(t,parent) < 0) { // t < parent , t 在parent的左子树
            if(compare(t,parent.left) < 0){ //左侧一字型
                return singleLeft(parent);
            }else{
                //先右转 再左转
                singRight(parent.right);
                return singleLeft(parent);
            }
        }else{
            if(compare(t,parent.right) > 0) {
                return singRight(parent);
            }else{
                singleLeft(parent.right);
                return singRight(parent);
            }
        }
    }
    private int compare(T t, RedBlackTreeNode<T> node) {
        if(node == this.header) {
            return 1;
        }
        return t.compareTo(node.data);
    }
}
