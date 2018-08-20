package Capter3;

import Capter1.Binary;

import java.util.Comparator;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 上午11:03 2018/8/19
 * @ Description：BinarySearchTree
 * @ Modified By：
 * @Version: $
 */
public class BinarySearchTree<T extends Comparable<? super T>> {
    private static class BinaryNode<T> {
        public T data;
        public BinaryNode<T> left;
        public BinaryNode<T> right;

        public BinaryNode(T data,BinaryNode<T> left,BinaryNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

    }
    private BinaryNode<T> root;
    public BinarySearchTree() {
        this.root = null;
    }
    public boolean isEmpty() {
        return this.root == null;
    }
    public boolean contains(T t) {//从顶root开始查就是全部查找一遍了
        return contains(t,this.root);
    }
    private boolean contains (T t , BinaryNode<T> node) {
        if(node == null) {
            return false;
        }
        int index = t.compareTo(node.data);
        //理解一下树的概念，有点像二分查找
        if(index < 0) {//t比较小，应该找node的左子树（这个节点的左节点）
            return contains(t,node.left);
        }else if(index > 0) {//由子树
            return contains(t,node.right);
        }else{ //相等
            return true;
        }
    }
    public void insert(T t) {
        System.out.println("insert " + t);
        this.root = insert(t,this.root);
    }
    private BinaryNode<T> insert(T t, BinaryNode<T> node) {
        if(node == null) { //查找到最后的时候
            return new BinaryNode<T>(t,null,null);
        }
        //不是第一个数，就需要查找了，树的作用是按顺序存储
        //如果查到相同的，不做处理，因为不存放相同元素
        //没有相同的，那么最后查到位置node==null的位置就是应该插入的位置
        //然后返回一个只保存Data的node，再修改与之相连的left 或 right指向这个new node
        //因为insert不会改变结构，add到的一定是叶节点
        int index = t.compareTo(node.data);
        if(index < 0) { //left
            node.left = insert(t,node.left);
            System.out.println("left" + node.left.data);
        }else if(index > 0) { //right
            node.right = insert(t,node.right);
            System.out.println("right"+node.right.data);
        }else{

        }
        return node;
    }
    public void remove(T t){
        remove(t,this.root);
    }
    //删除
    //如果是叶节点 直接删除
    //如果只有一个子节点，删除之后将被删除节点的子节点连接到被删除节点的父节点上
    //如果是两个子节点，要去找右子节点的最小值，将他代替被删除节点，这样又相当于删除了最小值的原节点
    private  BinaryNode<T> remove(T t,BinaryNode<T> node) {
        if(node == null) { //找不到
            return node;
        }
        int index = t.compareTo(node.data);
        if(index < 0) {
            node.left = remove(t,node.left);
        }else if(index > 0) {
            node.right = remove(t,node.right);
        }else { //找到了这个节点node
            if(node.right != null && node.left != null) { //两个子节点
                BinaryNode<T> minNode = findMin(node.right);
                node.data = minNode.data;//替换成minNode
                node.right = remove(minNode.data,node.right);
            }else{
                node = (node.left == null)? node.right:node.left;
            }
        }
        return node;
    }
    private BinaryNode<T> findMin (BinaryNode<T> node) {
        //查找node 节点下的最小值，走左节点
        if(node == null) {
            return node;
        }
        if(node.left != null) {
            return findMin(node.left);
        }else{
            return node;
        }
    }
    private void print(BinaryNode<T> node) {
        if(node == null) {
            System.out.println("--------");
            return;
        }
        System.out.println(node.data+"  ");
        print(node.left);
        print(node.right);
    }
    public void print() {
        print(this.root);
    }
}
