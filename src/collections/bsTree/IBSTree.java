package collections.bsTree;

public interface IBSTree<K extends Comparable<K>, V> {
    String inOrder();

    BSNode<K, V> search(K key);

    K minimum(K key);

    K maximum(K key);

    K sucessor(K key);

    void add(K key, V value);

    void delete(K key);

}
