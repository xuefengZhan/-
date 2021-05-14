package _01_线性表;

public abstract class AbstractList<E> implements List<E>{
    protected int size;

    protected void rangeCheck(int index){
        if(index >= size || index < 0){
            throw new RuntimeException("Index Out of Range");
        }

    }
    protected void rangeCheckForAdd(int index){
        if(index > size || index < 0){
            throw new RuntimeException("Index Out of Range For Add");
        }

    }

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size ==0;
    }

    public boolean contains (E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    public void add (E element) {
        add(size,element);
    }

    public int remove(E element){

        int index = indexOf(element);
        remove(indexOf(element));

        return index;
    }


}
