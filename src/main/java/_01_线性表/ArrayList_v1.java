package _01_线性表;

public class ArrayList_v1<E> implements List<E> {

    private int size;
    private E[] elements;
    //默认的数组容量大小
    private static final int DEFAULT_CAPACITY = 10;
    //-1下标：代表没有这个元素
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList_v1() {

        this(DEFAULT_CAPACITY);
    }

    public ArrayList_v1(int capacity) {
        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        elements = (E[])new Object[capacity];
    }

    private void rangeCheck(int index){
        if(index >= size || index < 0){}
            throw new RuntimeException("Index Out of Range");
    }
    private void rangeCheckForAdd(int index){
        if(index > size || index < 0){}
            throw new RuntimeException("Index Out of Range For Add");
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) == ELEMENT_NOT_FOUND;
    }

    @Override
    public void add(E element) {
        add(size,element);
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
        }
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    private void ensureCapacity(int size){
        if(size == 0){
            elements = (E[])new Object[DEFAULT_CAPACITY];
        }
        if(size == elements.length){
            int oldCapacity = size;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            E[] newElements = (E[]) new Object[newCapacity];

            for(int i = 0;i<size;i++){
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacity(size);

        for(int i = size;i>index;i--){
            elements[i] = elements[i-1];
        }
        elements[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E oldElement = get(index);
        for(int i =index;i < size-1;i++){
            elements[i] = elements[i+1];
        }
        elements[size-1] = null;
        size --;
        return oldElement;
    }

    @Override
    public int indexOf(E element) {
        if(element == null){
            for(int i=0;i < size;i++){
                if(elements[i] == null){
                    return i;
                }
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {

        for(int i = 0;i<size;i++){
            elements[i] = null;
        }
        size=0;
    }
}
