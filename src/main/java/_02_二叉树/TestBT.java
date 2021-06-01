package _02_二叉树;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestBT{
    _02_BinarySearchTree<Integer> bst;
    @Before
    public void init(){
        bst = new _02_BinarySearchTree<>();
        bst.add(8);
        bst.add(4);
        bst.add(6);
        bst.add(5);
        bst.add(7);
        bst.add(2);
        bst.add(3);
        bst.add(1);
        bst.add(13);
        bst.add(10);
        bst.add(9);
        bst.add(12);
        bst.add(11);
    }

    @Test
    public void test01(){

    }
    @Test
    public void test02(){
        bst.remove(13);
    }

    @After
    public void last(){
        bst.inorderTraversal(new _02_BinarySearchTree.Visitor<Integer>(){
            @Override
            public void visit(Integer element) {
                System.out.println(element);
            }
        });
    }
}