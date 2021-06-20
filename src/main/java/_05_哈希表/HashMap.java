package _05_哈希表;

import _02_二叉树._01_BinaryTree;
import _04_映射.Map;

public class HashMap<K,V> implements Map<K,V> {
    private static final boolean RED =false;
    private static final boolean BLACK = true;
    private int size;
    private static final int DEFAULT_CAPACITY = 1<<4;
    private Node<K,V>[] table;

    public HashMap(){
        table = new Node[DEFAULT_CAPACITY];
    }

    private static class Node<K,V>  {
        K key;
        V value;
        Node<K,V> left;
        Node<K,V> right;
        Node<K,V> parent;
        boolean color = RED;


        public Node(){}

        public Node(K key,V value, Node<K,V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public boolean isLeaf() { // 是否叶子节点
            return left == null && right == null;
        }

        public boolean hasTwoChildren() { // 是否有两个子节点
            return left != null && right != null;
        }

        public boolean isLeftChild(){ // 判断自己是不是左子树
            return parent!=null && this==parent.left;
        }

        public boolean isRightChild(){ // 判断自己是不是右子树
            return parent!=null && this==parent.right;
        }


        public Node<K,V> brother() { // 红黑树中用到, 返回兄弟节点
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }
            return null;
        }



    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {
        if(size == 0) return;
        for (int i = 0; i < size; i++) {
            table[i] = null;
        }
        size = 0;
    }
    //获取key的索引
    private int index(K key){
        if(key == null) return 0;
        int hash = key.hashCode();
        return (hash ^ (hash >>> 16)) & (table.length-1);
    }


    @Override
    public V put(K key, V value) {
        //todo 1.获取key的索引
        int index = index(key);
        Node<K,V> root = table[index];
        if(root == null){
            root = new Node<>(key,value,null);
            table[index] = root;
            size++;
            return null;
        }
        return null;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {

    }
}
