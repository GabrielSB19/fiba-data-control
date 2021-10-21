package collections.bsTree;

public interface IBSTree<K extends Comparable<K>, V> {
    String inOrder();

    BSNode<K, V> search(K key);

    K minimum(K key);

    K maximum(K key);

    K sucessor(K key);

    BSNode<K, V> add(K key, V value);

    BSNode<K, V> delete(K key);

}
