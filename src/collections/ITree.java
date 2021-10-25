package collections;

import collections.bsTree.BSNode;

import java.util.ArrayList;

public interface ITree<K extends Comparable<K>, V> {
    ArrayList<V> inOrder();

    BSNode<K, V> search(K key, V value);

    void filter(ArrayList<V> filter, K key);

    BSNode<K, V> minimum(K key, V value);

    K maximum(K key, V value);

    BSNode<K, V> sucessor(K key, V value);

    BSNode<K, V> add(K key, V value);

    BSNode<K, V> delete(K key, V value);

}
