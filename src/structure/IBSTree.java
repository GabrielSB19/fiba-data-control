package structure;

public interface IBSTree <K extends Comparable<K>, V extends Comparable<V>{
    void inOrder();
    V search(K key);
    V mininum();
    V maximun();
    V sucessor();
    void insertion(K key, V value);
    boolean delete(K key);
}
