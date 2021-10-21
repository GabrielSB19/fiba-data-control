package collections.rbTree;

import collections.bsTree.IBSTree;

public interface IRBTree<K extends Comparable<K>, V> extends IBSTree<K,V> {
    void leftRotate(RBNode<K,V> x);
    void rightRotate(RBNode<K,V> x);
    void rbInsertion(K key, V value);
    void rbDeletion(K key);
}
