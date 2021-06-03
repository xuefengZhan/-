package _02_二叉树;

import java.util.Comparator;

public class _04_RBTree<E> extends _02_BinarySearchTree<E> {
    private static final boolean RED=false;
    private static final boolean BLACK=true;

    public _04_RBTree() {
    }

    public _04_RBTree(Comparator comparator) {
        super(comparator);
    }

    private static class RBNode<E> extends Node<E> {
        boolean color = RED;
        public RBNode(E element, Node<E> parent) {
        }
    }



    /**
     * 一些辅助函数
     */

    //todo 给节点染色
    //返回值为node  返回染完色的node
    private Node<E> color(Node<E> node,boolean color){
        if(node == null) return node;
        ((RBNode<E>)node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node){
        return color(node,RED);
    }
    private Node<E> black(Node<E> node){
        return color(node,BLACK);
    }

    //todo 获取node的颜色
    private boolean colorOf(Node<E> node){
        if(node == null) return BLACK;//空节点在红黑树中往往是叶子结点，因此返回黑色
        return ((RBNode<E>)node).color;
    }

    private boolean isBlack(Node<E> node){
        return colorOf(node) == BLACK;
    }




    @Override
    protected void afterAdd(Node<E> node) {
        super.afterAdd(node);
    }

    @Override
    protected void afterRemove(Node<E> node) {
        super.afterRemove(node);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return super.createNode(element, parent);
    }
}
