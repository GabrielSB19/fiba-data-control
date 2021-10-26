package collections;

import collections.bsTree.BSNode;

import java.util.ArrayList;

public interface ITree<K extends Comparable<K>, V> {
    ArrayList<V> inOrder();

    BSNode<K, V> search(K key, V value);

    void filter(ArrayList<V> filter, K key);

    BSNode<K, V> sucessor(BSNode<K, V> node);

    BSNode<K, V> add(K key, V value);

    BSNode<K, V> delete(K key, V value);

}
