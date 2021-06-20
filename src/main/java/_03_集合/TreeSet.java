package _03_集合;

import _02_二叉树._01_BinaryTree;
import _02_二叉树._04_RBTree;

public class TreeSet<E> implements Set<E>{
    private _04_RBTree<E> tree = new _04_RBTree<>();

    public int size() {
        return tree.size();
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public void claer() {
        tree.clear();
    }

    public boolean contains(E element) {
        return tree.contains(element);
    }

    public void add(E element) {
        tree.add(element); // 红黑树自带去重
    }

    public void remove(E element) {
        tree.remove(element);
    }

    public void traversal(Visitor<E> visitor) {
        tree.inorderTraversal(new _01_BinaryTree.Visitor<E>() {
            @Override
            public void visit(E element) {
                visitor.visit(element);
            }
        });
    }

}
