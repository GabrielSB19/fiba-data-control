package collections.avlTree;

import collections.bsTree.BSNode;
import collections.bsTree.BSTree;

public class AVLTree<K extends Comparable<K>, V> extends BSTree<K, V> implements IAVLTree<K, V> {
    private AVLNode<K, V> root;

    @Override
    public void leftRotate(AVLNode<K, V> x) {
        BSNode<K, V> y = x.getRight();
        BSNode<K, V> z = y.getLeft();
 
        y.setLeft(x);
        x.setRight(z);

        updateHeight(x);
        updateHeight(y);
    }

    @Override
    public void rightRotate(BSNode<K, V> x) {
        BSNode<K, V> y = x.getLeft();
        BSNode<K, V> z = y.getRight();
 
        y.setRight(x);
        x.setLeft(z);

        updateHeight(x);
        updateHeight(y);
    }

    @Override
    public void add(K key, V value) {

    }

    protected int getBalance(AVLNode<K, V> node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }

    

}
