package _01_线性表._05_队列;

public class _03_CircleQueue<E> {
    private int size;
    private E[] elements;
    private int front;//队头元素下标
    private int tail;

    public _03_CircleQueue(){
        elements = (E[]) new Object[10];
    }

    public int size(){
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            // elements[index(i)] = null;
            elements[(i + front) %elements.length] = null;
        }
        size = 0;
        front = 0;
    }



    public void enQueue(E element) {
        ensureCapacity(size);
        elements[(front + size) % elements.length] = element;
        size++;
    }

    private void ensureCapacity(int size){
        if(size == 0){
            elements = (E[])new Object[10];
        }
        if(size == elements.length){
            int oldCapacity = size;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            E[] newElements = (E[]) new Object[newCapacity];

            for(int i = 0;i<size;i++){
                newElements[i] = elements[(i+front)%elements.length];
            }
            elements = newElements;
            front = 0;
        }
    }

    public E deQueue() {
        ensureCapacity(size+1);
        E oldValue = elements[front];
        elements[front] = null;
        front = (front+1)%elements.length;
        size--;
        return oldValue;
    }

    public E front(){
        return elements[front];
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size ; i++) {
            sb.append(" ").append(elements[(front+i)%elements.length]);
        }
        sb.append(" ]");
        return sb.toString();
    }

}
class test{
    public static void main(String[] args) {
        _03_CircleQueue<Integer> cq = new _03_CircleQueue<>();
        for (int i = 0; i < 10; i++) {
            cq.enQueue(i);
        }
        System.out.println(cq);//[ 0 1 2 3 4 5 6 7 8 9 ]
        System.out.println(cq.front());

        cq.deQueue();
        System.out.println(cq);//[ 1 2 3 4 5 6 7 8 9 ]
        System.out.println(cq.front());

        cq.enQueue(10);
        System.out.println(cq);//[ 1 2 3 4 5 6 7 8 9 10 ]
        System.out.println(cq.front());


        cq.enQueue(11);
        System.out.println(cq);//[ 1 2 3 4 5 6 7 8 9 10 11 ]
        System.out.println(cq.size());
        System.out.println(cq.front());

        cq.deQueue();
        System.out.println(cq);//[ 2 3 4 5 6 7 8 9 10 11 ]
        System.out.println(cq.size());//10
        System.out.println(cq.front());//2
    }
}
