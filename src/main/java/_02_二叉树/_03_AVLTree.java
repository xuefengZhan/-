package _02_二叉树;

import BinaryTreePrinter.src.com.mj.printer.BinaryTreeInfo;
import BinaryTreePrinter.src.com.mj.printer.BinaryTrees;

import java.util.Comparator;

public class _03_AVLTree<E> extends _05_BinaryBalancedSearchTree<E>{

    public _03_AVLTree(){}

    public _03_AVLTree(Comparator comparator){
        super(comparator);
    }

    private static class AVLNode<E> extends Node<E>{
        private int height = 1; // 叶子节点的高度为1

        AVLNode(){}

        AVLNode(E element,Node<E> parent){
            this.element = element;
            this.parent = parent;
        }

        //获取节点的平衡因子
        public int balanceFacotr(){
            int leftHeight = left==null? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right==null? 0 : ((AVLNode<E>)right).height;
            return leftHeight - rightHeight;
        }

        //更新节点的高度
        public void updateHeight(){
            int leftHeight =  left==null? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right==null? 0 : ((AVLNode<E>)right).height;
            height = Math.max(leftHeight,rightHeight) + 1;
        }


        //返回较高的子节点，如果一样高，返回同方向的子节点
        public Node<E> tallerChild(){
            int leftHeight = left==null? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right==null? 0 : ((AVLNode<E>)right).height;
            Node<E> ReNode = leftHeight > rightHeight? left : right;
            if(leftHeight == rightHeight){//返回相同方向的
                if(this.isLeftChild()){
                    ReNode = left;
                }else{
                    ReNode = right;
                }
            }
            return ReNode;
        }
    }


    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);
        updateHeight(grand);
        updateHeight(parent);
    }

    private boolean isBalanced(Node<E> node){
        return Math.abs(((AVLNode<E>)node).balanceFacotr()) <= 1;
    }

    private void updateHeight(Node<E> node){
        ((AVLNode<E>)node).updateHeight();
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<E>(element,parent);
    }


    //给Node恢复平衡  node是高度最低的失衡节点g
    private void rebalance(Node<E> node){
        //必须得获取n\p\g的关系，才能知道是四种失衡情况的哪一种
        //p是g左右子树中高度最高的节点
        //n是p左右子树中高度最高的节点
        Node<E> parent = ((AVLNode<E>)node).tallerChild();
        Node<E> n =  ((AVLNode<E>)parent).tallerChild();
        if(parent.isLeftChild()){
            if(n.isLeftChild()){
                //LL
                rotateRight(node);
            }else{
                //LR
                rotateLeft(parent);
                rotateRight(node);
            }
        }else{
            if(n.isLeftChild()){
                //RL
                rotateRight(parent);
                rotateLeft(node);
            }else{
                //RR
                rotateLeft(node);
            }
        }

    }

    @Override
    protected void afterAdd(Node<E> node) {
        //沿着parent一直往上找到第一个失衡的父节点
       while((node=node.parent)!= null){
           //新节点的父节点肯定是平衡的，因此从这往上一直更新到
           //失衡祖宗节点的高度
           if(isBalanced(node)){
               updateHeight(node);
           }else{
               //这是第一个不平衡的节点
                rebalance(node);
                break;//只需要恢复第一个不平衡节点即可
           }
       }
    }

    @Override
    protected void rotate(Node<E> r, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f) {
        super.rotate(r, b, c, d, e, f);
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    //todo 删除节点可能会引发多个祖父节点失衡，因此对比 afterAdd()
    // afterAdd只需要修复最低的失衡节点即可，因此只需要去掉break即可。
    // 需要注意的是：在删除节点的过程中，我们并没有修改del节点的parent，
    // 因此node=node.parent是一直有效的。
    protected void afterRemove(Node<E> node,Node<E> replacement) {
        //沿着parent一直往上找到第一个失衡的父节点
        while((node=node.parent)!= null){
            //新节点的父节点肯定是平衡的，因此从这往上一直更新到
            //失衡祖宗节点的高度
            if(isBalanced(node)){
                updateHeight(node);
            }else{
                //这是第一个不平衡的节点
                rebalance(node);
            }
        }
    }
}

class AVLT{
    public static void main(String[] args) {
        _03_AVLTree<Integer> avlt = new _03_AVLTree<>();
        //avlt.add()
        avlt.add(35);
        avlt.add(37);
        avlt.add(34);
        avlt.add(56);
        avlt.add(25);
        avlt.add(62);
        avlt.add(57);
        avlt.add(9);
        avlt.add(74);
        avlt.add(32);
        avlt.add(94);
        avlt.add(80);
        avlt.add(75);
        avlt.add(100);
        avlt.add(16);
        avlt.add(82);
        BinaryTrees.println(avlt);

    }
}