package _01_线性表._01动态数组;

import _01_线性表._01动态数组.ArrayList;

public class ArrayList_test {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);


        list.add(0,9);

        list.remove(1);

        System.out.println(list.size());




        System.out.println(list);
    }
}
