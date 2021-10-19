package collections.avlTree;

import collections.bsTree.BSNode;
import collections.bsTree.IBSTree;

public interface IAVLTree<K extends Comparable<K>, V> extends IBSTree<K, V> {
    void leftRotate(BSNode<K, V> x);
    void rightRotate(BSNode<K, V> x);
}
