package _02_二叉树;

import BinaryTreePrinter.src.com.mj.printer.BinaryTrees;

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
            this.element = element;
            this.parent = parent;
        }

        @Override
        public String toString() {
            String str = "";
            if(color==RED){
                str = "R_";
            }
            return str + element.toString();

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

        //todo case2:parent为红色，并且Unlce节点为红色
        Node<E> uncle = parent.sibling();

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
        // 注意：这里获取brother节点不能这么获取,因为在afterRemove()方法中，node是已经被删除的节点
        // 这意味着：如果node是左子节点，那么parent.left==null
        //Node<E> brother = node.sibling();
        //     public Node<E> sibling() { // 红黑树中用到, 返回兄弟节点
        //            if (isLeftChild()) {
        //                return parent.right;
        //            }
        //
        //            if (isRightChild()) {
        //                return parent.left;
        //            }
        //            return null;
        //        }

        //public boolean isLeftChild(){ // 判断自己是不是左子树
        //            return parent!=null && this==parent.left;
        //        }

        // 由于aftrerRemove()方法是在remove()方法之后调用的，
        // 那么remove()方法中，node节点被删除后：
        // if(del == del.parent.left){
        //            del.parent.left = child;
        //        }else{
        //            del.parent.right = child;
        // del就是被删除节点node,那么在这里调用node.sibling() 会调用 node.isLeftChild()
        // 如果ndoe是左子节点，那么node.parent.left 已经为null了，右子节点同理
        // 因此node调用isLeftChild的时候，praent.left==null了，this不为null，所以判断会有问题。
        // 无法获取isLeftChild()，也就无法获取sibling()了


        //这里处理的方法是：如果node.parent.left == null 那么node就是左子节点。 （暂时感觉有歧义）
        //还用node.isLeftChild()判断是当父节点只有一个黑色元素的时候，父节点下溢，黑色元素下溢和子节点合并，但是并不是真正
        //的删除这个黑色元素，也就是说这个黑色元素的parent.left或者parent.right 仍然等于这个黑色元素
        boolean left = parent.left == null || node.isLeftChild();
        Node<E> brother = left ? parent.right : parent.left;

        boolean parentColor = colorOf(parent);
        //3.2 兄弟节点可以借  兄弟节点为黑色，且有子节点。
       if(left){ //和else完全对称，左->右  右->左
           if(isRed(brother)){
               black(brother);
               red(parent);
               rotateLeft(parent);
               //更换兄弟 兄弟是黑色节点
               brother = parent.right;
           }
           //到这里了，brother肯定为黑色
           if(isBlack(brother.left) && isBlack(brother.right)){
               //brother是光杆司令,父节点要和子节点合并=> 父节点染黑，兄弟节点染红 这么处理完后相当于删除了父节点
               boolean parentBlack = isBlack(parent);
               black(parent);
               red(brother);
               if(parentBlack){
                   //父节点为黑色，父节点向下融合后就为空了，因此相当于删除掉一个叶子结点，左删除后处理。
                   afterRemove(parent,null);
               }
           }else{
               //兄弟节点至少有一个红色
               if(isBlack(brother.right)){
                   rotateRight(brother);
                   brother = brother.parent;
               }
               //旋转brother之后，统一变为LL的情况  先染色 再旋转
               //（这里brother旋转之后是没有变的,因此要在上面LR的情况修改一下brother）
               color(brother,colorOf(parent));
               black(brother.right);
               black(parent);
               rotateLeft(parent);
           }
       }else{//如果node是右子节点
           if(isRed(brother)){
                black(brother);
                red(parent);
                rotateRight(parent);
                //更换兄弟 兄弟是黑色节点
                brother = parent.left;
           }
           //到这里了，brother肯定为黑色
           if(isBlack(brother.left) && isBlack(brother.right)){
            //brother是光杆司令,父节点要和子节点合并=> 父节点染黑，兄弟节点染红 这么处理完后相当于删除了父节点
               boolean parentBlack = isBlack(parent);
               black(parent);
               red(brother);
               if(parentBlack){
                   //父节点为黑色，父节点向下融合后就为空了，因此相当于删除掉一个叶子结点，左删除后处理。
                   afterRemove(parent,null);
               }
           }else{
               //兄弟节点至少有一个红色
               if(isBlack(brother.left)){//LR
                    rotateLeft(brother);
                    brother = brother.parent;
               }
               //旋转brother之后，统一变为LL的情况  先染色 再旋转
               //（这里brother旋转之后是没有变的,因此要在上面LR的情况修改一下brother）
               color(brother,colorOf(parent));
               black(brother.left);
               black(parent);
               rotateRight(parent);
           }

           }

       }

    }


class RBTest{
    public static void main(String[] args) {
        Integer data[] = new Integer[]{
                55,87,56,74,96,22,62,20,79,68,90,50
        };

        _04_RBTree<Integer> rb = new _04_RBTree();
        rb.add(55);
        rb.add(87);
        rb.add(56);
        rb.add(74);
        rb.add(96);
        rb.add(22);
        rb.add(62);
        rb.add(20);
        rb.add(79);
        rb.add(68);
        rb.add(90);
        rb.add(50);


        BinaryTrees.println(rb);
        for (int i = 0; i < data.length; i++) {
            rb.remove(data[i]);
            System.out.println("["+data[i]+"]");
            BinaryTrees.println(rb);
        }
        BinaryTrees.println(rb);


    }
}
