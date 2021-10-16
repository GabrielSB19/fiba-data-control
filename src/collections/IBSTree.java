package collections;

public interface IBSTree <K extends Comparable<K>, V>{
    void inOrder();
    V search(K key);
    K mininum();
    K maximun();
    K sucessor(K key);
    void insertion(K key, V value);
    void delete(K key);
}
