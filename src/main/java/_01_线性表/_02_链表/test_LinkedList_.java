package _01_线性表._02_链表;

import _01_线性表._03循环链表.DoubleCircleLinkedList;
import org.junit.Test;

public class test_LinkedList_ {
    @Test
    public void test_LinkedList(){
        LinkedListWithHead<Integer> list = new LinkedListWithHead();

        list.add(1);
        list.add(2);


        list.add(0,3);
        System.out.println(list); //3 1 2


        list.set(1,4);
        System.out.println(list); //3 4 2

        //System.out.println(list.get(3));


        System.out.println(list.remove(2));  //2
        System.out.println(list);//3 4


        System.out.println(list.indexOf(4));//1

        list.clear();
        System.out.println(list.size());//0

        System.out.println(list);


        list.add(1);
        list.add(2);
        list.add(null);
        list.add(3);
        list.add(4);

        System.out.println(list);

        System.out.println(list.indexOf(4));
    }


    @Test
    public void test_DoubleCircleLinkedList(){
        DoubleCircleLinkedList<Integer> list = new DoubleCircleLinkedList();

        list.add(1);
        System.out.println(list); //3 1 2
        list.add(2);


        list.add(0,3);
        System.out.println(list); //3 1 2


        list.set(1,4);
        System.out.println(list); //3 4 2

        //System.out.println(list.get(3));


        //System.out.println(list.remove(2));  //2
        //System.out.println(list);//3 4


        System.out.println(list.indexOf(4));//1

        list.clear();
        System.out.println(list.size());//0

        System.out.println(list);


        list.add(1);
        list.add(2);
        list.add(null);
        list.add(3);
        list.add(4);

        System.out.println(list);


        System.out.println(list.indexOf(null));
        System.out.println(list.indexOf(4));
    }
}
