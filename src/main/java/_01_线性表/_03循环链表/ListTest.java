package _01_线性表._03循环链表;

import org.junit.Test;

public class ListTest {

   @Test
    public void test_DCLT(){
       DoubleCircleLinkedList<Integer> list = new DoubleCircleLinkedList();

       list.add(1);
       System.out.println(list);  // 1

       list.add(2);  // 1 2
       System.out.println(list);


       list.add(0,3); //3 1 2
       System.out.println(list);


       list.set(1,4);
       System.out.println(list); //3 4 2

       System.out.println(list.get(2));


       System.out.println(list.remove(2));  //2
       System.out.println(list);//3 4


       System.out.println(list.indexOf(4));//1

       list.clear();
       System.out.println(list.size());//0

       System.out.println(list);

       System.out.println("清空重来");

       list.add(1);
       list.add(2);
       list.add(null);
       list.add(3);
       list.add(4);
       // 1 2 null 3 4

       System.out.println(list);


       System.out.println(list.indexOf(null));
       System.out.println(list.indexOf(4));


       list.remove(0);
       System.out.println(list);  // 2 null 3 4

       list.remove(3);
       System.out.println(list); // 2 null 3

   }
}
