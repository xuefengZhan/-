package _02_二叉树;

import java.util.Comparator;

public class _04_RBTree<E> extends _05_BinaryBalancedSearchTree<E> {
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

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<E>(element, parent);
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

    //todo 判断节点是否是黑色节点
    private boolean isBlack(Node<E> node){
        return colorOf(node) == BLACK;
    }
    //todo 判断节点是否是黑色节点
    private boolean isRed(Node<E> node){
        return colorOf(node) == RED;
    }


    //添加后处理
    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        //todo case1:父节点是black
        //如果parent==null，意味着新添加的节点为root，此时将此节点染成黑色
        //注意：这里也解决了上溢到根节点，根节点溢出新的根节点的问题
        //新溢出的根节点要染成黑色
        if(parent==null){
            black(node);
            return;
        }
        //如果parent是黑色节点，那么直接返回即可，不需要做多的操作
        if(isBlack(parent)){
            return;
        }


        Node<E> uncle = parent.sibling();

        //todo case2:Unlce节点为红色
        // 1.parent 和 Uncle染成black
        // 2.grand上溢  ： 将grand染成红色，然后当做新节点添加到
        Node<E> grand = parent.parent;
        if(isRed(uncle)){
            black(parent);
            black(uncle);
            Node<E> newGrand = red(grand);//将grand染成红色
            afterAdd(newGrand);//当做一个新的节点添加，注意染成红色后就是添加后的状态了，只需要做afterADd处理
            return;
        }
        //todo case3: Uncle节点不是红色
        // Uncle节点不是红色还有四种情况 LL LR RR RL
        if(parent.isLeftChild()){//L
            if(node.isLeftChild()){//LL
                //parent染成黑色
                //grand染成红色  并且单旋操作
                black(parent);
                red(grand);
                rotateRight(grand);
            }else{ //LR
                //自己染成黑色，grand染成红色
                black(node);
                red(grand);
                rotateLeft(parent);
                rotateRight(grand);
            }
        }else{ //R
            if(node.isLeftChild()){//RL
                black(node);
                red(grand);
                rotateRight(parent);
                rotateLeft(grand);
            }else{ //RR
                black(parent);
                red(grand);
                rotateLeft(grand);
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node,Node<E> replacement) {
        //todo case1.被删除的节点为红色
        if(isRed(node)){
            return;
        }
        //todo case2.被删除的节点的替代节点为红色
        //将染成黑色即可
        if(isRed(replacement)){
            black(replacement);
            return;
        }
        //todo case3.被删除的节点为单独的black节点
        // 3.1 删除节点为根节点
        Node<E> parent = node.parent;
        if( parent == null) return;

    }


}
