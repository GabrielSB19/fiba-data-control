package collections;

import collections.bsTree.BSNode;

import java.util.ArrayList;

public interface ITree<K extends Comparable<K>, V> {
    ArrayList<V> inOrder();

    BSNode<K, V> search(K key);

    K minimum(K key);

    K maximum(K key);

    K sucessor(K key);

    BSNode<K, V> add(K key, V value);

    void set(K key, V value);

    BSNode<K, V> delete(K key);

}
