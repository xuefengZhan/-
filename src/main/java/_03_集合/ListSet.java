package _03_集合;

import _01_线性表.List;
import _01_线性表._02_链表.LinkedList;

public class ListSet<E> implements Set<E>{
    private LinkedList<E> list = new LinkedList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void claer() {
        list.clear();
    }

    public boolean contains(E element) {
        return list.contains(element);
    }

    //todo Set核心方法
    public void add(E element) {
        // if(list.contains(element)) return;

        int index = list.indexOf(element);
        if(index == List.ELEMENT_NOT_FOUND){ // 没有该元素
            list.add(element); // 没有就添加
        }else{
            list.set(index, element); // 已经有就替换
        }
    }

    public void remove(E element) {
        int index = list.indexOf(element);
        if(index != List.ELEMENT_NOT_FOUND){
            list.remove(index);
        }
    }

    public void traversal(Visitor<E> visitor) {
        int size = list.size();
        for(int i = 0; i < size; i++){
            visitor.visit(list.get(i));
        }
    }

}
class TestListSet{
    public static void main(String[] args) {
       ListSet<Integer> set =  new ListSet<Integer>();
       set.add(2);
       set.add(3);
       set.add(6);
       set.add(7);
       set.add(2);

    }
}

