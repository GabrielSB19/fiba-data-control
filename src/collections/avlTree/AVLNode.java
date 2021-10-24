package collections.avlTree;

import collections.bsTree.BSNode;

public class AVLNode<K, V> extends BSNode<K, V> {

    private int height;

    public AVLNode(K key, V value) {
        super(key, value);
        height = 0;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
