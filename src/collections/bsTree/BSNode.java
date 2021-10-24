package collections.bsTree;

public class BSNode<K, V> {

    private K key;
    private V value;
    private BSNode<K, V> parent;
    private BSNode<K, V> right;
    private BSNode<K, V> left;

    public BSNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public BSNode<K, V> getParent() {
        return parent;
    }

    public void setParent(BSNode<K, V> parent) {
        this.parent = parent;
    }

    public BSNode<K, V> getRight() {
        return right;
    }

    public void setRight(BSNode<K, V> right) {
        this.right = right;
    }

    public BSNode<K, V> getLeft() {
        return left;
    }

    public void setLeft(BSNode<K, V> left) {
        this.left = left;
    }
}
