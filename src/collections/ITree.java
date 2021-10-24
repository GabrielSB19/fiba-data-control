package collections;

import collections.bsTree.BSNode;

import java.util.ArrayList;

public interface ITree<K extends Comparable<K>, V> {
    ArrayList<V> inOrder();

    BSNode<K, V> search(K key, V value);

    K minimum(K key, V value);

    K maximum(K key, V value);

    K sucessor(K key, V value);

    BSNode<K, V> add(K key, V value);

    void set(K key, V newValue, V value);

    BSNode<K, V> delete(K key, V value);

}
