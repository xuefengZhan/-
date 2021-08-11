package _01_线性表._01动态数组;

import _01_线性表.AbstractList;

public class ArrayList<E> extends AbstractList<E> {


    private E[] elements;
    //默认的数组容量大小
    private static final int DEFAULT_CAPACITY = 10;


    public ArrayList() {
    }

    public ArrayList(int capacity) {
        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        elements = (E[])new Object[capacity];
    }



    @Override
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
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
        }else{
                for (int i = 0; i < size; i++) {
                    if(elements[i].equals(element)) return i;
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


    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++) {
            sb.append(elements[i]).append(" ");
        }
        return sb.toString();
    }
}
