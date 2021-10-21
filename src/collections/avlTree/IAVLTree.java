package collections.avlTree;

import collections.bsTree.BSNode;
import collections.bsTree.IBSTree;

public interface IAVLTree<K extends Comparable<K>, V> extends IBSTree<K, V> {
    BSNode<K, V> leftRotate(BSNode<K, V> x);

    BSNode<K, V> rightRotate(BSNode<K, V> x);

    void rebalance(BSNode<K, V> node, K key);

    void avlInsertion(K key, V value);

    void avlDeletion(K key);
}
