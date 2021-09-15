//package _04_哈希表;
//
//import _02_二叉树._01_BinaryTree;
//import _02_二叉树._04_RBTree;
//
//public class HashMap<K,V> implements Map<K,V>{
//
//
//    private static final boolean RED = false;
//    private static final boolean BLACK = true;
//    private static final int DEFAULT_CAPACITY = 16;
//
//    private int size; // 哈希表中所有元素个数
//    private Node<K,V>[] table;
//    public HashMap(){
//        table = new Node[DEFAULT_CAPACITY];
//    }
//
//
//    private static class Node<K, V> {
//        K key;
//        V value;
//        boolean color = RED;
//        Node<K, V> left;
//        Node<K, V> right;
//        Node<K, V> parent;
//
//        public Node(K key, V value, Node<K, V> parent) {
//            this.key = key;
//            this.value = value;
//            this.parent = parent;
//        }
//
//        public boolean isLeaf() { // 是否为叶子结点
//            return left == null && right == null;
//        }
//
//        public boolean hasTwoChildren() { // 是否有两个子节点
//            return left != null && right != null;
//        }
//
//        public boolean isLeftChild() { // 是否为左节点
//            return parent != null && this == parent.left;
//        }
//
//        public boolean isRightChild() { // 是否为右节点
//            return parent != null && this == parent.right;
//        }
//
//        public Node<K, V> sibling() { // 返回兄弟节点
//            if (isLeftChild()) {
//                return parent.right;
//            }
//
//            if (isRightChild()) {
//                return parent.left;
//            }
//            return null;
//        }
//    }
//
//    //todo 给节点染色
//    //返回值为node  返回染完色的node
//    private Node<K,V> color(Node<K,V> node, boolean color) {
//        if (node == null) return node;
//        ((Node<K,V>) node).color = color;
//        return node;
//    }
//
//    private Node<K,V> red(Node<K,V> node) {
//        return color(node, RED);
//    }
//
//    private Node<K,V> black(Node<K,V> node) {
//        return color(node, BLACK);
//    }
//
//    //todo 获取node的颜色
//    private boolean colorOf(Node<K,V> node) {
//        if (node == null) return BLACK;//空节点在红黑树中往往是叶子结点，因此返回黑色
//        return ((Node<K,V>) node).color;
//    }
//
//    //todo 判断节点是否是黑色节点
//    private boolean isBlack(Node<K,V> node) {
//        return colorOf(node) == BLACK;
//    }
//
//    //todo 判断节点是否是黑色节点
//    private boolean isRed(Node<K,V> node) {
//        return colorOf(node) == RED;
//    }
//
//    public int size() {
//        return size;
//    }
//
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//    public void clear() {
//        if(size==0){
//            return;
//        }
//        for (int i = 0; i < table.length; i++) {
//            table[i] = null;
//        }
//        size = 0;
//    }
//
//    private int index(K key){
//        if(key == null) return 0;
//        int hash = key.hashCode();
//        return (hash ^ (hash>>>16)) & (table.length-1);
//    }
//
//    protected void rotateLeft(Node<K,V> node) {
//        Node<K,V> parent = node.right;
//        Node<K,V> child =  parent.left;
//        parent.left = node;
//        node.right = child;
//        afterRotate(node,parent,child);
//    }
//
//    protected void rotateRight(Node<K,V> node){
//        Node<K,V> parent = node.left;
//        Node<K,V> child =  parent.right;
//        parent.right = node;
//        node.left = child;
//        afterRotate(node,parent,child);
//
//    }
//
//
//
//    protected void afterRotate(Node<K,V> grand, Node<K,V> parent, Node<K,V> child){
//
//        parent.parent = grand.parent;
//        if(grand.isLeftChild()){
//            grand.parent.left = parent;
//        }else if(grand.isRightChild()){
//            grand.parent.right = parent;
//        }else{
//            root = parent;
//        }
//
//        if(child != null){
//            child.parent = grand;
//        }
//
//        grand.parent = parent;
//    }
//
//    protected void afterAdd(Node<K,V> node) {
//        Node<K,V> parent = node.parent;
//
//        if (parent == null) {
//            black(node);
//            return;
//        }
//
//        if (isBlack(parent)) {
//            return;
//        }
//
//        Node<K,V> uncle = parent.sibling();
//
//        Node<K,V> grand = parent.parent;
//        if (isRed(uncle)) {
//            black(parent);
//            black(uncle);
//            Node<K,V> newGrand = red(grand);
//            afterAdd(newGrand);
//            return;
//        }
//        if (parent.isLeftChild()) {//L
//            if (node.isLeftChild()) {//LL
//                black(parent);
//                red(grand);
//                rotateRight(grand);
//            } else { //LR
//                black(node);
//                red(grand);
//                rotateLeft(parent);
//                rotateRight(grand);
//            }
//        } else { //R
//            if (node.isLeftChild()) {//RL
//                black(node);
//                red(grand);
//                rotateRight(parent);
//                rotateLeft(grand);
//            } else { //RR
//                black(parent);
//                red(grand);
//                rotateLeft(grand);
//            }
//        }
//    }
//
//    private void afterPut(Node<K, V> node){
//        Node<K, V> parent = node.parent;
//
//        // 添加的是根节点 或者 上溢到达了根节点
//        if (parent == null) {
//            black(node);
//            return;
//        }
//
//        // 如果父节点是黑色，直接返回
//        if (isBlack(parent)) return;
//
//        // 叔父节点
//        Node<K, V> uncle = parent.sibling();
//        // 祖父节点
//        Node<K, V> grand = red(parent.parent);
//        if (isRed(uncle)) { // 叔父节点是红色【B树节点上溢】
//            black(parent);
//            black(uncle);
//            // 把祖父节点当做是新添加的节点
//            afterPut(grand);
//            return;
//        }
//
//        // 叔父节点不是红色
//        if (parent.isLeftChild()) { // L
//            if (node.isLeftChild()) { // LL
//                black(parent);
//            } else { // LR
//                black(node);
//                rotateLeft(parent);
//            }
//            rotateRight(grand);
//        } else { // R
//            if (node.isLeftChild()) { // RL
//                black(node);
//                rotateRight(parent);
//            } else { // RR
//                black(parent);
//            }
//            rotateLeft(grand);
//        }
//    }
//
//
//    @Override
//    public V put(K key, V value) {
//        int index = index(key);
//        Node<K,V> root = table[index];
//        if(root == null){
//            root = new Node<>(key,value,null);
//            table[index] = root;
//            size++;
//            return null;
//        }
//    }
//
//    @Override
//    public V get(K key) {
//        return null;
//    }
//
//    @Override
//    public V remove(K key) {
//        return null;
//    }
//
//    @Override
//    public boolean containsKey(K key) {
//        return false;
//    }
//
//    @Override
//    public boolean containsValue(V value) {
//        return false;
//    }
//
//    @Override
//    public void traversal(Visitor<K, V> visitor) {
//
//    }
//}
