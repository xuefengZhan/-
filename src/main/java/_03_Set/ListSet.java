package _03_Set;

import _01_线性表.List;
import _01_线性表._02_链表.LinkedList;

public class ListSet<E> implements Set<E> {
    private List<E> list = new LinkedList<>();


    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void claer() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        int index = list.indexOf(element);
        if (index != List.ELEMENT_NOT_FOUND) {
            return;
        }
        list.add(index, element);
    }

    @Override
    public void remove(E element) {
        int index = list.indexOf(element);
        if (index != List.ELEMENT_NOT_FOUND) {
            list.remove(index);
        }
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if (visitor == null) return;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            visitor.visit(list.get(i));
        }
    }
}
