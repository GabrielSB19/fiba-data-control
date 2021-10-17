package collections.rbTree;

public interface IRBTree<K extends Comparable<K>, V> {
    int getSize();

    boolean isEmpty();

    void add(K key, V value);

    boolean contains(K key);

    V get(K key);

    void set(K key, V newValue);

    V remove(K key);
}
