package _01_线性表;

public class DoubleLinkedList<E> extends AbstractList<E>{

    private Node<E> first;
    private Node<E> last;

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
        Node<E> node = node(index);
        E oldELement = node.element;
        node.element = element;
        return oldELement;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        //插头
        if(index == 0){
            Node<E> newNode = new Node(element,null,first);
            if(size == 0){
                last =  newNode;
            }else{
                first.prev = newNode;
            }
            first = newNode;
        }else{
            //插尾
            if(index == size){
                Node<E> next = null;
                last.next = new Node(element,last,null);
                last = last.next;
            }else{
                //插中间
                Node<E> next = node(index);
                Node<E> prev = next.prev;
                Node newNode = new Node(element,prev,next);
                prev.next = newNode;
                next.prev = newNode;
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
    public String toString(){
        Node node = first;
        if(first == null) return "empty list";
        StringBuilder sb = new StringBuilder();
        sb.append("first:").append(first.element);
        sb.append(" last:").append(last.element).append(" ");

        while(node  != null){
           sb.append(node).append(" ");
           node = node.next;
        }
        sb.append("\n");
        return sb.toString();
    }

}
