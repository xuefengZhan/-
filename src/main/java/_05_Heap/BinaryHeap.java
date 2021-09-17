package _05_Heap;

import BinaryTreePrinter.src.com.mj.printer.BinaryTreeInfo;
import BinaryTreePrinter.src.com.mj.printer.BinaryTrees;

import java.util.Comparator;

public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(Comparator<E> comparator) {
        super(comparator);
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap() {
        this(null);
    }


    //批量建堆

    private void heapify() {
        // 自上而下的上滤
//		for (int i = 1; i < size; i++) {
//			siftUp(i);
//		}

        // 自下而上的下滤
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    public BinaryHeap(E[] array,Comparator<E> comparator){
        super(comparator);

        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            size = elements.length;
            int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];
            }
            heapify();
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    private void swift(int i, int j) {
        E tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;
    }

    private void ensureCapacity(int size) {
        if (size == 0) {
            elements = (E[]) new Object[DEFAULT_CAPACITY];
        }
        if (size == elements.length) {
            int newCapacity = size + (size >> 1);
            E[] newElements = (E[]) new Object[newCapacity];

            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        elements[size++] = element;

        siftUp(size - 1);

    }


    private void siftUp(int index) {
        E element = elements[index];
        while (index > 0) {
            // 父节点索引 = (子节点索引-1) / 2
            int parentIndex = (index - 1) >> 1;
            E parent = elements[parentIndex];
            if (compare(element, parent) <= 0) break;

            // 将父元素存储在index位置
            elements[index] = elements[parentIndex];

            // 重新赋值index
            index = parentIndex;
        }
        elements[index] = element;
    }


    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is Empty");
        }
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    public void siftDown(int index) {
        E old = elements[index];
        while (index <= (size - 2) >> 1) {
            int leftIndex = (index << 1) + 1;
            int rightIndex = leftIndex + 1;
            int maxIndex = leftIndex;

            if (index <= (size - 3) >> 1) {//左右都有
                maxIndex = compare(elements[leftIndex], elements[rightIndex]) > 0 ? leftIndex : rightIndex;
            }
            if (compare(elements[maxIndex], old) > 0) {
                elements[index] = elements[maxIndex];
                index = maxIndex;
            } else {
                break;
            }
        }
        elements[index] = old;
    }

    @Override
    public E remove() {
        emptyCheck();
        E old = elements[0];
        elements[0] = elements[size - 1];
        elements[size - 1] = null;
        size--;
        siftDown(0);
        return old;
    }

    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        E old = null;
        if (size == 0) {
            elements[0] = element;
            size ++;
        }else{
            old = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return old;
    }


    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        Integer index = (Integer) node;
        index = (index << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        Integer index = (Integer) node;
        index = (index << 1) + 2;
        return index >= size ? null : index;

    }

    @Override
    public Object string(Object node) {
        Integer index = (Integer) node;
        return elements[index];
    }
}

//
class Test {
    public static void main(String[] args) {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(68);
        heap.add(72);
        heap.add(43);
        heap.add(50);
        heap.add(38);
        heap.add(10);
        heap.add(90);
        heap.add(65);
        BinaryTrees.println(heap);
        heap.remove();
        BinaryTrees.println(heap);
        heap.remove();
        BinaryTrees.println(heap);
        heap.remove();
        BinaryTrees.println(heap);
        heap.remove();
        BinaryTrees.println(heap);
        heap.remove();
        BinaryTrees.println(heap);
        heap.remove();
        BinaryTrees.println(heap);
//
//        System.out.println(heap.replace(70));
//        BinaryTrees.println(heap);


        //批量建堆测试
//        Integer[] array =  {88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37};
//        BinaryHeap<Integer> heap2 = new BinaryHeap<Integer>(array, null);
//        BinaryTrees.println(heap2);

    }
}
