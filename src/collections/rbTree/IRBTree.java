package collections.rbTree;

public interface IRBTree<K extends Comparable<K>, V> {
    void leftRotate(RBNode<K,V> x);
    void rightRotate(RBNode<K,V> x);
    void rbInsertion(K key, V value);
    void rbDeletion(K key);
}
