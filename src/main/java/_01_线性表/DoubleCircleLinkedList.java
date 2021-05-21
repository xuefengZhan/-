package _01_线性表;

public class DoubleCircleLinkedList<E> extends DoubleLinkedList<E>{

    private Node<E> first;
    private Node<E> last;

    private class Node<E>{
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(){}
        public Node(E element,  Node<E> prev, Node<E> next){
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
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        Node<E> next ;
        Node<E> prev ;
        if(index == size){
            next = first;
        }else{
            next = node(index);
        }
        if(index == 0){
            prev = last;
        }else{
            prev = node(index -1 );
        }

        Node<E> newNode = new Node(element,prev,next);
        //插头
        if(index == 0){
            if(size == 0){
                first = newNode;
                last = newNode;
            }else{
                first.prev = newNode;
                first = newNode;
            }
        }else if(index == size){
            last.next = newNode;
            last = newNode;
        }
        //插尾

        //插中间

        size++;
    }

    @Override
    public E remove(int index) {
        return null;
    }


}
