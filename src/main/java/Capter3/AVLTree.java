package Capter3;

import sun.util.locale.provider.AvailableLanguageTags;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 上午8:48 2018/8/20
 * @ Description：AVL平衡树
 * @ Modified By：
 * @Version: $
 */
public class AVLTree<T extends Comparable<? super T>> {

    private static class AVLNode<T> {
        public T data;
        public AVLNode<T> left;
        public AVLNode<T> right;
        public int height;//记录这棵树的高度

        public AVLNode(T data, AVLNode<T> left, AVLNode<T> rigth, int height) {
            this.data = data;
            this.left = left;
            this.right = rigth;
            this.height = height;
        }
    }

    private AVLNode<T> root;
    public AVLTree() {
        clear();
    }
    public void clear() {
        this.root = null;
    }
    public void insert(T t) {
        this.root = insert(t,this.root);
    }
    public void remove(T t) {
        this.root = remove(t,this.root);
    }
    //重点在于Insert
    //1 类似BinaryTree查找到要插入的地方

    private AVLNode<T> insert(T t,AVLNode<T> node) {
        if(node == null) { //要插入的地方
            return new AVLNode(t,null,null,0);
        }
       int index = t.compareTo(node.data);
        if(index < 0) {
            node.left = insert(t,node.left);
        }else if(index > 0) {
            node.right = insert(t,node.right);
        }else{
            //重复值不做处理
        }
        return reBanlance(node);//这个node就是从哪里开始查找更新一遍的root
    }
    private AVLNode<T> findMin(AVLNode<T> node) {
        //查找最小值，只有node.left!=null 就递归
        if(node == null) {
            return null;
        }
        if(node.left != null) {
            return findMin(node.left);
        }else{
            return node;
        }
    }
    private AVLNode<T> remove(T t, AVLNode<T> node) {
        //remove和insert一样，只要return reBanlance(node)就可以保证结构不变
        if(node == null) {
            return null;
        }
        int index = t.compareTo(node.data);
        if(index < 0) {
            node.left = remove(t,node.left);
        }else if(index > 0) {
            node.right = remove(t,node.right);
        }else{
            //查找到了，要删除
            //删除时分类是两个子节点还是一个子节点
            //如果只有一个子节点，删除之后将被删除节点的子节点连接到被删除节点的父节点上
            //如果是两个子节点，要去找右子节点的最小值，将他代替被删除节点，这样又相当于删除了最小值的原节点
            if(node.left != null && node.right != null) {
                AVLNode<T> minNode = findMin(node.right);
                node.data = minNode.data;
                node.right = remove(minNode.data,node.right);
            }else{
                node = node.left == null?node.right:node.left;
            }
        }
        return node;
    }
    private int height(AVLNode<T> node) {
        return node==null?-1:node.height;//null的height是-1，叶节点的height是0
    }
    private AVLNode<T> reBanlance(AVLNode<T> node) { //重新平衡更新,自底向上
        if(node == null) {
            return node;
        }
        //下面是为了平衡二叉树，分为左右 左左
        if(height(node.left) - height(node.right) > 1) {//左子树不平衡
            if(height(node.left.left) >= height(node.left.right)) {
                node = singleLeft(node);//单侧旋转
            }else{
                //双侧
                node = doubleLeft(node);
            }

        }else if(height(node.right) - height(node.left) > 1) {
            if(height(node.right.right) >= height(node.right.left)) {
                // 单侧旋转
                node = singRight(node);
            }else {
                // 双侧
                node = doubleRight(node);
            }
        }
        //右右 右左
        //除了平衡二叉树，还需要更新每个node 的height
        //递归自顶向下是为了查找一条路径，return可以将查找到的这条路径更新
        //由于递归return时把路径再次自底向上遍历一遍用来更新
        node.height = Math.max(height(node.left),height(node.right)) + 1;//子节点height+1
        return node;
    }
    private AVLNode<T> singleLeft(AVLNode<T> node) {
        AVLNode<T> left = node.left;
        //改变t.left和t之间的关系
        left.right = node;
        node.left = left.right;
        node.height = Math.max(height(node.left),height(node.right)) + 1;
        left.height = Math.max(height(left.left),height(left.left)) + 1;
        //返回父节点 left
        return left;
    }
    private AVLNode<T> singRight(AVLNode<T> node) {
        AVLNode<T> right = node.right;
        node.right = right.left;
        right.left = node;
        node.height = Math.max(height(node.left),height(node.right)) + 1;
        right.height = Math.max(height(right.left),height(right.left)) + 1;
        return right;
    }
    private AVLNode<T> doubleLeft(AVLNode<T> node) {
        //先右旋再左旋
        node.left = singRight(node.left);
        return singleLeft(node);
    }
    private AVLNode<T> doubleRight(AVLNode<T> node) {
        node.right = singleLeft(node.right);
        return singleLeft(node);
    }
}
