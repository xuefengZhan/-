package _01_线性表._02_链表;

import _01_线性表.AbstractList;

public class DoubleLinkedList<E> extends AbstractList<E> {

    private Node<E> first;
    private Node<E> last;

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

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        //todo index == size 有两种情况

        if (index == size) { // 往最后面添加元素
            Node<E> oldLast = last;
            last = new Node(element,oldLast,null);
            if (oldLast == null) { // 这是链表添加的第一个元素
                first = last;
            } else {
                oldLast.next = last;
            }
        } else { // 正常添加元素
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node(element,prev,next);
            next.prev = node;
            if (prev == null) { // index == 0
                first = node;
            } else {
                prev.next = node;
            }
        }

        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> oldNode = node(index);
        Node<E> prev = oldNode.prev;
        Node<E> next = oldNode.next;
        //移除第一个节点
        if(prev == null){
            first = next;
        }else{
            prev.next = next;
        }
        //移除最后一个节点
       if(next == null){
           last = prev;
       }else{
           next.prev = prev;
       }
        size--;
        return oldNode.element;
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
    public String toString(){
        Node node = first;
        if(first == null) return "empty list";
        StringBuilder sb = new StringBuilder();
        sb.append("first:").append(first.element);
        sb.append("--last:").append(last.element).append("\n");

        while(node  != null){
           sb.append(node).append(" --- ");
           node = node.next;
        }
        sb.append("\n");
        return sb.toString();
    }

}
class Test{
    public static void main(String[] args) {
        DoubleLinkedList<Integer> list = new DoubleLinkedList();

        list.add(1);
        System.out.println(list);
//        list.add(2);
//
//
//        list.add(0,3);
//        System.out.println(list); //3 1 2
//
//
//        list.set(1,4);
//        System.out.println(list); //3 4 2
//
//        //System.out.println(list.get(3));
//
//
//        System.out.println(list.remove(2));  //2
//        System.out.println(list);//3 4
//
//
//        System.out.println(list.indexOf(4));//1
//
//        list.clear();
//        System.out.println(list.size());//0
//
//        System.out.println(list);
//
//
//        list.add(1);
//        list.add(2);
//        list.add(null);
//        list.add(3);
//        list.add(4);
//
//        System.out.println(list);
//
//
//        System.out.println(list.indexOf(null));
//        System.out.println(list.indexOf(4));
    }
}