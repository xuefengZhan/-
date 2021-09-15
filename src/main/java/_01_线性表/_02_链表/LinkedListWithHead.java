package _01_线性表._02_链表;


import _01_线性表.AbstractList;

/*
      02. 带有虚拟头结点的单向链表
     在增删的时候，需要考虑index==0的特殊情况，需要特殊处理
     虚拟头结点的目的是让头结点的处理和普通节点一样
     具体做法就是first指向一个不存值得节点，first.next为index==0的节点
 */
public class LinkedListWithHead<E> extends AbstractList<E> {

    //todo first 指向的节点不存储数据
    private Node<E> first = new Node<E>(null);


    private class Node<E>{
        E element;
        Node<E> next;

        public Node(E element){
            this.element = element;
            this.next = null;
        }

        public Node(E element,Node<E> next){
            this.element = element;
            this.next = next;
        }
    }

    private Node<E> node(int index){
        rangeCheck(index);
        //todo 改动1：
        //Node<E> current = first;
        Node<E> current = first.next;
        for(int i = 0;i < index;i++){
            current = current.next;
        }
        return current;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E oldELement = node.element;
        node.element = element;
        return oldELement;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        //todo : 改动2 这里体现了带虚拟头节点的优越之处
        // 当index=0的时候，prev就是first，
//        if(index == 0){
//            first = new Node(element,first);
//        }else{
//            Node<E> prev = node(index - 1);
//            prev.next = new Node(element,prev.next);
//        }
        Node<E> prev = index == 0 ? first : node(index  - 1);
        prev.next = new Node<E>(element,prev.next);

        size++;

    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        //todo 改动
//        if(index == 0){
//            oldElement = first.element;
//            first = first.next;
//        }else{
//            Node<E> prev  = node(index - 1);
//            oldElement = prev.next.element;
//            prev.next = prev.next.next;
//        }
        Node<E> prev = index == 0 ? first : node(index  - 1);
        E oldElement = prev.next.element;
        prev.next = prev.next.next;

        size--;
        return oldElement;
    }

    @Override
    public int indexOf(E element) {
        if(first == null) throw new RuntimeException("empty list");
        Node<E> current = first.next;
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
        //todo 改动
        first.next = null;
        size = 0;
    }

    @Override
    public String toString() {
        if(first == null) return "empty list";
        Node<E> node = first.next;
        StringBuilder sb = new StringBuilder();

        while(node != null){
            sb.append(node.element).append(" ");
            node = node.next;
        }
        return sb.toString();
    }
}
