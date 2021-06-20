package _06_堆;

public interface Heap<E>{
    public static final int DEFAULT_CAPACITY=10;
    int size();
    boolean isEmpty();
    void clear();
    void add(E element);
    E get();
    E remove();
    E replace(E element);
}
