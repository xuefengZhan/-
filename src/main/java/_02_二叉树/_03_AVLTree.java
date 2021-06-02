package _02_二叉树;

import java.util.Comparator;

public class _03_AVLTree<E> extends _02_BinarySearchTree<E>{
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

    //旋转
    private void rotateLeft(Node<E> node) {
        Node<E> p = node.right;
        Node<E> tmp = p.left;

        //1.更改左右子节点
        p.left = node;
        node.right = tmp;
        if (node.parent == null) {
            root = node;
        } else if (node == node.parent.left) {
            node.parent.left = p;
        } else {
            node.parent.right = p;
        }

        p.parent = node.parent;
        node.parent = p;
        if (tmp != null) {
            tmp.parent = node;
        }

        //更新高度
        updateHeight(node);
        updateHeight(p);
    }


        private void rotateRight(Node<E> node){
            Node<E> p = node.left;
            Node<E> n = p.left;
            Node<E> tmp = p.right;

            p.right = node;
            node.left = tmp;
            if (node.parent == null) {
                root = node;
            } else if (node == node.parent.left) {
                node.parent.left = p;
            } else {
                node.parent.right = p;
            }

            p.parent = node.parent;
            node.parent = p;
            if (tmp != null) {
                tmp.parent = node;
            }
            //更新高度
            updateHeight(node);
            updateHeight(p);
        }

        //统一旋转操作
    private void rotate(
            Node<E> r, // 子树的根节点
            Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f) {
        // 让d成为这颗子树的根结点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }
        // b-c
        b.right = c;
        if (c != null) {
            c.parent = b;
        }
        updateHeight(b);

        // e-f
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        updateHeight(f);

        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
        updateHeight(d);
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

    //todo 删除节点可能会引发多个祖父节点失衡，因此对比 afterAdd()
    // afterAdd只需要修复最低的失衡节点即可，因此只需要去掉break即可。
    // 需要注意的是：在删除节点的过程中，我们并没有修改del节点的parent，
    // 因此node=node.parent是一直有效的。
    protected void afterRemove(Node<E> node) {
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
