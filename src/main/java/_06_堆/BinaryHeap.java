package _06_堆;

import BinaryTreePrinter.src.com.mj.printer.BinaryTreeInfo;
import BinaryTreePrinter.src.com.mj.printer.BinaryTrees;

import java.util.Comparator;

//二叉堆
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo{



    protected E[] elements;

    public BinaryHeap(){
        super();
    }
    public BinaryHeap(Comparator<E> comparator){
        super(comparator);
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap(E[] elements, Comparator<E> comparator)  {
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
    public BinaryHeap(E[] elements){
        this(elements,null);
    }


    private void heapify() {
        // 自上而下的上滤
//		for (int i = 1; i < size; i++) {
//			siftUp(i);
//		}

        // 自下而上的下滤
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            downShift(i);
        }
    }



    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size=0;
    }


    //todo 获取根元素

    private void emptyCheck(){
        if(size == 0){
            throw new IndexOutOfBoundsException("Heap is Empty");
        }
    }

    //todo 添加
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
    private void elementNotNullCheck(E element){
        if(element == null){
            throw new IllegalArgumentException("element can not be null");
        }
    }

    //上滤  将index位置的元素上滤
    private void upShift1(int index){
        //1.父节点位置
        if(index == 0) return;

        while(index>0){
            int parentIndex = (index-1)>>1;
            E parentElement = elements[parentIndex];
            E element = elements[index];
            if(compare(parentElement,element) >=0){
               return;
            }else{
                elements[parentIndex] = element;
                elements[index] = parentElement;
                index = parentIndex;
            }
        }
    }
    //上滤  优化版本
    private void upShift(int index){
        //1.父节点位置

        E tmp = elements[index];

        while(index>0){
            int parentIndex = (index-1)>>1;
            E parentElement = elements[parentIndex];
            if(compare(parentElement,tmp) >= 0) break;
            elements[index] = parentElement;
            index = (index-1)>>1;
        }
        //index=0  ||   compare(parentElement,tmp) >= 0
        elements[index] = tmp;
    }
    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size);
        elements[size++] = element;
        //添加逻辑：当新节点比父节点大，交换位置
        upShift(size-1);

    }

    private void downShift(int index){
        E tmp = elements[index];
        //3.如果 2 i + 1 ⩽ n − 1 ，它的左子节点的索引为 2 i + 1
        //4.如果 2 i + 1 > n − 1 ，它无左子节点

        //5.如果 2 i + 2 ⩽ n − 1，它的右子节点的索引为 2 i + 2
        //6.如果 2 i + 2 > n − 1 ，它无右子节点

        // 也就是说i> (n-2)>>1的时候就是叶子结点了
        //index必须是非叶子节点
        //完全二叉树中 第一个叶子结点的索引就是非叶子节点的个数   也就是 size>>1
        while(index <= (size-2)>>1){

            //取出左右节点中最大的
            //左右都有  将左右中最大的换上去
            int leftIndex = (index<<1)+1;
            int rightIndex = (index<<1)+2;

            //因为左子节点区间更大，所以默认为左Index
            int maxIndex=leftIndex;

            if(index <= (size-3)>>1){
                //左右都有的情况
                maxIndex = compare(elements[leftIndex],elements[rightIndex])>= 0 ? leftIndex:rightIndex;
            }

            if( compare(tmp,elements[maxIndex])>=0 ) break;
            elements[index] =elements[maxIndex];
            index = maxIndex;
        }
        elements[index] = tmp;
    }
    @Override
    public E remove() {
        emptyCheck();
        E max = elements[0];
//        elements[0] = elements[size-1];
//        elements[size-1] =null;
//        size--;
        int lastIndex = --size;
        elements[0] = elements[lastIndex];
        elements[lastIndex] =null;


        downShift(0);
        return max;
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }


    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        E root = null;
        //没有元素可以删除的时候，就放进去
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            downShift(0);
        }
        return root;

    }


    //返回索引
    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        Integer index = (Integer) node;
        if((index<<1)+2<= size){
            return (index<<1)+1;
        }else{
            return null;
        }
    }

    @Override
    public Object right(Object node) {
        Integer index = (Integer) node;
        int rightIndex = (index<<1)+2;
        if(rightIndex <= size-1){
            return rightIndex;
        }else{
            return null;
        }
    }

    @Override
    public Object string(Object node) {
        return elements[(Integer)node];
    }
}
class TestBinaryHeap{
//    public static void main(String[] args) {
//        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>();
//        Integer[] arr = {68,72,43,50,38,10,90,65};
//        for (Integer integer : arr) {
//            binaryHeap.add(integer);
//        }
//
//        BinaryTrees.println(binaryHeap);
//        binaryHeap.remove();
//        BinaryTrees.println(binaryHeap);
//        binaryHeap.replace(70);
//        BinaryTrees.println(binaryHeap);
//
//        BinaryHeap<Integer> binaryHeap2 = new BinaryHeap<>();
//        Integer[] data = {88,45,53,41,16,6,70,18,85,98,81,23,36,43,37};
//        BinaryHeap<Integer> heap = new BinaryHeap(data);
//        BinaryTrees.println(heap);
//
//    }

    //小顶堆测试
    public static void main(String[] args) {
        // 新建一个小顶堆
        BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        // 找出最大的前k个数
        int k = 3;
        Integer[] data = {51, 30, 39, 92, 74, 25, 16, 93,
                91, 19, 54, 47, 73, 62, 76, 63, 35, 18,
                90, 6, 65, 49, 3, 26, 61, 21, 48};
        for (int i = 0; i < data.length; i++) {
            if (heap.size() < k) { // 前k个数添加到小顶堆
                heap.add(data[i]); // logk
            } else if (data[i] > heap.get()) { // 如果是第 k + 1 个数，并且大于堆顶元素
                heap.replace(data[i]); // logk
            }
        }
        BinaryTrees.println(heap);
        // O(nlogk)
    }

}