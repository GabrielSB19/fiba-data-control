package collections;

public interface IBSTree <K extends Comparable<K>, V>{
    void inOrder();
    Node<K,V> search(K key);
    K mininum(K key);
    K maximun(K key);
    K sucessor(K key);
    void insertion(K key, V value);
    void delete(K key);
}
