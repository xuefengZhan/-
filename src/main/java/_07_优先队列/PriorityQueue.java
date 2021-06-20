package _07_优先队列;

import _06_堆.BinaryHeap;

import java.util.Comparator;

public class PriorityQueue<E> {
    private BinaryHeap<E> heap;

    // 通过 comparator 自定义优先级高低
    public PriorityQueue(Comparator<E> comparator) {
        heap = new BinaryHeap<>(comparator);
    }

    public PriorityQueue() {
        this(null);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void clear() {
        heap.clear();
    }

    public void enQueue(E element) {
        heap.add(element);
    }

    public E deQueue() {
        return heap.remove();
    }

    public E front() {
        return heap.get();
    }
}
class Person implements Comparable<Person> {
    private String name;
    private int boneBreak;
    public Person(String name, int boneBreak) {
        this.name = name;
        this.boneBreak = boneBreak;
    }

    @Override
    public int compareTo(Person person) {
        return this.boneBreak - person.boneBreak;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", boneBreak=" + boneBreak + "]";
    }
}
class Main {
    public static void main(String[] args) {
        PriorityQueue<Person> queue = new PriorityQueue<>();
        queue.enQueue(new Person("Jack", 2));
        queue.enQueue(new Person("Rose", 10));
        queue.enQueue(new Person("Jake", 5));
        queue.enQueue(new Person("James", 15));

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }
    }
}
