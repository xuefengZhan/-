package _01_线性表;

public class LinkedList_test {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList();

        list.add(1);
        list.add(2);


        list.add(0,3);
        System.out.println(list);


        list.set(1,4);
        System.out.println(list);

        //System.out.println(list.get(3));


        System.out.println(list.remove(2));
        System.out.println(list);


        System.out.println(list.indexOf(4));

        list.clear();
        System.out.println(list.size());
        System.out.println(list);

        list.add(1);
        list.add(2);
        list.add(null);
        list.add(3);
        list.add(4);

        System.out.println(list);

        System.out.println(list.indexOf(4));


    }
}
