package _01_线性表._04_栈;

import _01_线性表.List;
import _01_线性表._01动态数组.ArrayList;

public class Stack<E>{
    //地层结构
    private List<E> list = new ArrayList<>();

    public void clear(){
        list.clear();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void push(E element){
        list.add(element);
    }

    public E pop(){
        return list.remove(list.size() - 1);
    }

    public E peek(){
        return list.get(list.size() - 1);
    }
}
