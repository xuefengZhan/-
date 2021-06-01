package _01_线性表._05_队列;

import _01_线性表._02_链表.LinkedList;

public class _01_Queue<E> {
    LinkedList<E> queue = new LinkedList<E>();
    public int size() {
        return queue.size();
    }
    public boolean isEmpty() {
        return queue.isEmpty();
    }
    public void clear() {
        queue.clear();
    }
    public void enQueue(E element) {
        queue.add(element);
    }
    public E deQueue() {
        return queue.remove(0);
    }
    public E front(){
        return queue.get(0);
    }
}
