package collections.avlTree;

import collections.bsTree.BSNode;

public class AVLNode<K, V> extends BSNode<K, V> {

    private int bFactor;

    public AVLNode(K key, V value, int factor) {
        super(key, value);
        this.bFactor = factor;
    }

    public int getFactor() {
        return this.bFactor;
    }

    public void setFactor(int factor) {
        this.bFactor = factor;
    }

}
