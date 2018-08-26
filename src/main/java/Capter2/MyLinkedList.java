package Capter2;

import java.util.Stack;
import java.util.Vector;

/**
 * @ Author     ：CstomRita
 * @ Date       ：Created in 下午2:38 2018/8/18
 * @ Description：双链表实现LinkedList
 * 1 MyLinkedList中需要包含到两端的链 表的大小，由多个节点构成
 * 2 还需要节点类，包含数据 和 两个链
 *      所谓的链就是记录该节点前面和后面的两个节点，故在节点类需要放置两个同为节点类的变量
 * @ Modified By：
 * @Version: 1.0$
 */
public class MyLinkedList<T> {
    private static class Node<T>{ //节点
        public T data;
        public Node<T> prev;
        public Node<T> next;
        public Node(T t , Node<T> p, Node<T> n) {
            this.data = t;
            this.prev = p;
            this.next = n;
        }
    }
    private int size;
    private Node<T> beginNode;
    private Node<T> endNode;

    public MyLinkedList(){
        clear();
    }
    public void clear(){
        this.size = 0;
        this.beginNode = new Node<T>(null,null,null);//Data null previous null next endNote
        this.endNode = new Node<T>(null,beginNode,null);
        this.beginNode.next = this.endNode;
    }
    public Node<T> getNode(int index) {
        Node<T> node;
        if(index < this.size/2) {
            node = this.beginNode.next;//头节点的后一个是第一个元素
            for(int i = 0; i < index;i++) {
                node = node.next; //index-1的后一个就是index
            }
        }else{
            node = this.endNode;//注意这里如果是在最末尾插入需要获取endnote，这样的话下面循环要多循环一次
            for(int i = this.size;i > index;i--) {
                node = node.prev; //index+1的前一个就是index
            }
        }
        return node;
    }
    public void add(int index,T t) {
        if(index < 0 || index > this.size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node<T> node = getNode(index);
        Node<T> addNode = new Node<T>(t,node.prev,node);//插入到index，前节点index-1 后节点index
        //再修改index前节点和indx-1后节点为插入元素即可
        //注意逻辑顺序
        node.prev = addNode;
        addNode.prev.next = addNode;
        this.size++;
    }
    public void remove(int index) {
        //删除就是将index+1的前节点改为index-1，index-1的后节点改为index+1
        Node<T> node = getNode(index);
        node.next.prev = node.prev;
        node.prev.next = node.next;
        this.size--;
    }
    public String toString() {
        String s = "[ ";
        //在这里遍历，从头节点到尾节点
        Node<T> node = this.beginNode;
        while(node.next != this.endNode) { //后一个节点不是endnote就继续
            node = node.next;
            s+= node.data + " ";
        }
        return s+"]";
    }
}
