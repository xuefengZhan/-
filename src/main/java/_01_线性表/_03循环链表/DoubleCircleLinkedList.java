package _01_线性表._03循环链表;

import _01_线性表.AbstractList;
import _01_线性表._02_链表.DoubleLinkedList;

public class DoubleCircleLinkedList<E> extends AbstractList<E> {

    private  Node<E> first;
    private  Node<E> last;

    private class Node<E>{
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(){}
        public Node(E element,Node<E> prev,Node<E> next){
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();

            if(prev == null){
                sb.append("null");
            }else{
                sb.append(prev.element);
            }
            sb.append(",");
            if(element == null){
                sb.append("null");
            }else{
                sb.append(element);
            }
            sb.append(",");
            if(next == null){
                sb.append("null");
            }else{
                sb.append(next.element);
            }
            return sb.toString();
        }

    }

    @Override
    public int indexOf(E element) {
        if(first == null) throw new RuntimeException("empty list");

        Node<E> current = first;
        for (int i = 0; i < size; i++) {
            if((element == null && element == current.element) || (element != null && element.equals(current.element))){
                return i;
            }else{
                current = current.next;
            }


        }
        return ELEMENT_NOT_FOUND;

    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        Node<E> node = node(index);
        E oldELement = node.element;
        node.element = element;
        return oldELement;
    }

    private Node<E> node(int index){
        rangeCheck(index);
        Node<E> current = first;
        if(index <= size/2){
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        }else{
            current = last;
            for(int i = size-1;i >= index + 1;i--){
                current = current.prev;
            }
        }
        return current;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("first = ").append(first);
        sb.append(":::last = ").append(last);
        sb.append("<<<>>>");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            sb.append(node).append(" | ");
            node = node.next;
        }
        return sb.toString();

    }
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if(index == size){
            Node node = new Node(element, last, first);
            if(size == 0){
                first = node;
                last = node;
                first.prev = last;
                last.next = first;
            }else{
                last.next = node;
                first.prev = node;
                last = node;
            }
        }else{
            //index < size
            //index == 0
            //next 通用
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node(element,prev,next);
            prev.next = node;
            next.prev = node;
            if(index == 0){
                first = node;
            }
        }
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node;
        if(index == 0){
            node = first;
            if(size == 1){
                first = null;
                last = null;
            }else{
                first = first.next;
                first.prev = last;
                last.next = first;
            }
        }else if(index == size - 1){
            node = last;
            last = last.prev;
            last.next = first;
            first.prev = last;
        }else{
            node = node(index);
            node.prev.next = node.next.prev;
            node.next.prev = node.prev.next;
        }
        size--;
        return node.element;

    }

}
class Test{
    public static void main(String[] args) {
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

