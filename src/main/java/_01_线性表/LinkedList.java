package _01_线性表;

public class LinkedList<E> extends AbstractList<E>{

    private Node<E> first;


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
        Node<E> current = first;
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
        if(first == null){
            first = new Node(element);
        }else if(index == 0){
            first = new Node(element,first);
        }else{
            Node<E> prev = node(index - 1);
            prev.next = new Node(element,prev.next);
        }
        size++;

    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E oldElement = null;
        if(index == 0){
            oldElement = first.element;
            first = first.next;
        }else{
            Node<E> prev  = node(index - 1);
            oldElement = prev.next.element;
            prev.next = prev.next.next;
        }
        size--;
        return oldElement;
    }

    @Override
    public int indexOf(E element) {
        if(first == null) throw new RuntimeException("empty list");
        Node<E> current = first;
        for (int i = 0; i < size; i++) {
            if((element == null && element == current.element) || (element != null && element.equals(current.element))){
                //必须是element.equals(current.element)   反过来不行
                //因为element做了 ！= null 判断，短路与
                //如果反过来，element=3的情况下，会遍历到current.element = null的时候，因为在3前面
                //这个时候就会报空指针异常了
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
        size = 0;
    }

    @Override
    public String toString() {
        Node<E> node = first;
        StringBuilder sb = new StringBuilder();

        while(node != null){
            sb.append(node.element).append(" ");
            node = node.next;
        }
        return sb.toString();
    }
}
