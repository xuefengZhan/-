package _02_二叉树;

import java.util.LinkedList;
import java.util.Queue;

public class _01_BinaryTree<E> {
    protected int size; // 元素数量
    protected Node<E> root; // 根节点

    protected static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(){}

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() { // 是否叶子节点
            return left == null && right == null;
        }

        public boolean hasTwoChildren() { // 是否有两个子节点
            return left != null && right != null;
        }

        public boolean isLeftChild(){ // 判断自己是不是左子树
            return parent!=null && this==parent.left;
        }

        public boolean isRightChild(){ // 判断自己是不是右子树
            return parent!=null && this==parent.right;
        }
        /*
         * 返回兄弟节点
         */
        public Node<E> sibling() { // 红黑树中用到, 返回兄弟节点
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }
            return null;
        }
    }

    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    //定义访问元素的接口，具体操作
    public static interface Visitor<E>{
        void visit(E element);
    }

    private void preorderTraversal(Node<E> node,Visitor<E> visitor){
        if(node == null) return;
        //System.out.println(node.element);
        visitor.visit(node.element);
        preorderTraversal(node.left,visitor);
        preorderTraversal(node.right,visitor);
    }

    public void preorderTraversal(Visitor<E> visitor){
        preorderTraversal(root,visitor);
    }
    //中序遍历
    //todo 二叉搜索树的中序遍历结果要么是升序要么是降序
    private void inorderTraversal(Node<E> node,Visitor<E> visitor){
        if(node == null) return;
        inorderTraversal(node.left,visitor);
        //System.out.println(node.element);
        visitor.visit(node.element);
        inorderTraversal(node.right,visitor);
    }
    public void inorderTraversal(Visitor<E> visitor){
        inorderTraversal(root,visitor);
    }

    //后序遍历
    private void postorderTraversal(Node<E> node,Visitor<E> visitor){
        if(node == null || visitor == null) return;
        postorderTraversal(node.left,visitor);
        postorderTraversal(node.right,visitor);
        //System.out.println(node.element);
        visitor.visit(node.element);
    }

    public void postorderTraversal(Visitor<E> visitor){
        postorderTraversal(root,visitor);
    }

    //层序遍历
    public void levelOrderTranversal(Visitor<E> visitor){
        if(root == null) return;
        Queue<Node<E>> que =  new LinkedList();
        que.offer(root);
        while(!que.isEmpty()){
            Node<E> poll = que.poll();
            //System.out.println(poll.element);
            visitor.visit(poll.element);

            if(poll.left != null){
                que.offer(poll.left);
            }
            if(poll.right != null){
                que.offer(poll.right);
            }
        }
    }

    //求树高度 递归
    public int height1() {
        return height1(root);
    }
    private int height1(Node<E> node){
        if(node == null) return 0;
        return Math.max(height1(node.left),height1(node.right))+1;
    }

    //求树的高度 迭代
    public int height2(){
        if(root == null) return 0;
        Queue<Node<E>> que = new LinkedList();
        que.offer(root);
        int height = 0;
        int levelSize = 1;
        while(!que.isEmpty()){
            Node<E> poll = que.poll();

            if(poll.left != null){
                que.offer(poll.left);
            }
            if(poll.right != null){
                que.offer(poll.right);
            }

            if(--levelSize == 0 ){
                height++;
                levelSize = que.size();
            }
        }
        return height;

    }

    protected boolean isLeaf(Node<E> node){
        return node.left == null && node.right == null;
    }

    public boolean isComplete(){
        if(root == null) return false;

        Queue<Node<E>> que = new LinkedList();
        que.offer(root);

        // flag代表是否要求后面都是叶子节点
        // 比如遍历到一个节点 flag == null && right == null
        //  或者是 left != null && right == null
        // 则要求这个节点后面的节点都是叶子节点

        boolean flag = false;

        while(!que.isEmpty()){
            Node<E> poll = que.poll();

            if(flag && !isLeaf(poll)) return false;

            if(poll.left != null){
                que.offer(poll.left);
                if(poll.right != null){
                    que.offer(poll.right);
                }else{
                    flag = true;
                }
            }else{
                if(poll.right != null){
                    return false;
                }else{
                    flag = true;
                }
            }
        }
        return true;
    }

    //获取node得前驱节点
    protected Node<E> predesessor(Node<E> node){
        if(node == null) return null;
        Node<E> prev;
        //todo case1. 左子树不为null
        if(node.left != null){
            prev = node.left;
            while(prev.right != null){
                prev = prev.right;
            }
            return prev;
        }
        //todo case2.左子树为null，父节点不为null
        while(node.parent != null && node != node.parent.right){
            node = node.parent;
        }
        //要么node.parent == null 要么 node== node.parent.right
        return node.parent;

    }

    protected Node<E> sucessor(Node<E> node){
        if(node == null) return null;
        if(node.right != null){
            Node<E> next = node.right;
            while(next.left != null){
                next = next.left;
            }
            return next;
        }
        while(node.parent != null && node != node.parent.left){
            node = node.parent;
        }
        return node.parent;
    }

    protected Node<E> createNode(E element, Node<E> parent){
        return new Node<>(element, parent); // 默认返回一个通用节点
    }
}
