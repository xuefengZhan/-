package _02_二叉树;

import BinaryTreePrinter.src.com.mj.printer.BinaryTrees;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestBT{
    @Test
    /*
        测试二叉搜索树 add方法
     */
    public void test0(){
        Integer[] data = new Integer[] {7, 4, 9, 2, 5, 8, 11, 3};
        _02_BinarySearchTree<Integer> bst = new _02_BinarySearchTree<>();

        for (Integer datum : data) {
            bst.add(datum);
        }
        BinaryTrees.print(bst);
    }

    /*
        测试二叉树的前序遍历
     */
    @Test
    public void test01(){
        Integer[] data = new Integer[] {7, 4, 2, 1, 3, 5,12};
        _02_BinarySearchTree bst = new _02_BinarySearchTree<>();

        for (Integer datum : data) {
            bst.add(datum);
        }
        BinaryTrees.print(bst);
        System.out.println();
        bst.preorderTraversal(new _01_BinaryTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return element == 3;
            }
        });
    }
    /*
        测试中序遍历
     */
    @Test
    public void test02(){
        Integer[] data = new Integer[] {7, 4, 2, 1, 3, 5,12};
        _02_BinarySearchTree bst = new _02_BinarySearchTree<Integer>();
        for (Integer datum : data) {
            bst.add(datum);
        }
        BinaryTrees.print(bst);
        System.out.println();
        bst.inorderTraversal(new _01_BinaryTree.Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.println(element);
                return element == 7;
            }
        });
    }
    /*
        测试后续遍历
     */
    @Test
    public void test03(){
        Integer[] data = new Integer[] {7, 4, 2, 1, 3, 5,12};
        _02_BinarySearchTree bst = new _02_BinarySearchTree<Integer>();
        for (Integer datum : data) {
            bst.add(datum);
        }
        BinaryTrees.print(bst);
        System.out.println();
        bst.postorderTraversal(new _01_BinaryTree.Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.println(element);
                return element == 7;
            }
        });
    }
    /*
        层序遍历
     */
    @Test
    public void test04(){
        Integer[] data = new Integer[] {7, 4, 2, 1, 3, 5,12};
        _02_BinarySearchTree bst = new _02_BinarySearchTree<Integer>();
        for (Integer datum : data) {
            bst.add(datum);
        }
        BinaryTrees.print(bst);
        System.out.println();

        bst.levelOrderTranversal(new _01_BinaryTree.Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.println(element);
                return element == 3;
            }
        });
    }
    /*
        测试获取树高度
     */
    @Test
    public void test05(){
        Integer[] data = new Integer[] {7, 4, 2, 1, 3, 5,12};
        _02_BinarySearchTree bst = new _02_BinarySearchTree<Integer>();
        for (Integer datum : data) {
            bst.add(datum);
        }
        BinaryTrees.print(bst);
        System.out.println();

        System.out.println(bst.height1());
        System.out.println(bst.height2());
    }

    /*
        测试前继节点获取
     */
    @Test
    public void test06(){
        Integer[] data = new Integer[] {7, 4, 2, 1, 3, 5,12};
        _02_BinarySearchTree bst = new _02_BinarySearchTree<Integer>();
        for (Integer datum : data) {
            bst.add(datum);
        }
        BinaryTrees.print(bst);
        System.out.println();

        System.out.println(bst.predesessor(bst.node(1)));//null
        System.out.println(bst.predesessor(bst.node(2)));//1
        System.out.println(bst.predesessor(bst.node(3)));//2
        System.out.println(bst.predesessor(bst.node(4)));//3
        System.out.println(bst.predesessor(bst.node(5)));//4
        System.out.println(bst.predesessor(bst.node(7)));//5
        System.out.println(bst.predesessor(bst.node(12)));//7
    }

    /*
        测试remove
     */
    @Test
    public void test07(){
        Integer[] data = new Integer[] {7, 4,9, 2,5,8,11,3,12, 1};
        _02_BinarySearchTree bst = new _02_BinarySearchTree<Integer>();
        for (Integer datum : data) {
            bst.add(datum);
        }
        BinaryTrees.print(bst);
        System.out.println();
        bst.remove(9);
        BinaryTrees.print(bst);
        System.out.println();
    }
}