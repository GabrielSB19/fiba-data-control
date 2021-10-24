package collections.avlTree;

import collections.bsTree.BSTree;

public class AVLTree<K extends Comparable<K>, V> extends BSTree<K, V> {

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private int height(AVLNode<K, V> node) {
        return (node == null) ? 0 : node.getHeight();
    }

    private void updateHeight(AVLNode<K, V> node) {
        AVLNode<K, V> castL = (AVLNode<K, V>) node.getLeft();
        AVLNode<K, V> castR = (AVLNode<K, V>) node.getRight();
        node.setHeight(1 + max(height(castL), height(castR)));
    }

    public AVLNode<K, V> leftRotate(AVLNode<K, V> x) {
        AVLNode<K, V> y = (AVLNode<K, V>) x.getRight();

        x.setRight(y.getLeft());
        y.getLeft().setParent(x);
        y.setParent(x.getParent());

        if (x.getParent() == null) {
            root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }

        y.setLeft(x);
        x.setParent(y);

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    public AVLNode<K, V> rightRotate(AVLNode<K, V> x) {
        AVLNode<K, V> y = (AVLNode<K, V>) x.getLeft();

        x.setLeft(y.getRight());
        y.getRight().setParent(x);
        y.setParent(x.getParent());

        if (x.getParent() == null) {
            root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }

        y.setRight(x);
        x.setParent(y);

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    @Override
    public AVLNode<K, V> add(K key, V value) {
        AVLNode<K, V> newNode = new AVLNode<>(key, value);
        AVLNode<K, V> ancester = (AVLNode<K, V>) super.add(newNode);
        if (ancester != null) {
            updateHeight(ancester);
            rebalance(ancester, key);
        }
        return ancester;
    }

    @Override
    public AVLNode<K, V> delete(K key, V value) {
        AVLNode<K, V> ancester = (AVLNode<K, V>) super.delete(key, value);
        if (ancester != null) {
            rebalance(ancester, key);
        }
        return ancester;
    }

    private int getBalanceFactor(AVLNode<K, V> node) {
        if (node == null) {
            return 0;
        }
        AVLNode<K, V> castL = (AVLNode<K, V>) node.getLeft();
        AVLNode<K, V> castR = (AVLNode<K, V>) node.getRight();

        return height(castL) - height(castR);
    }

    public void rebalance(AVLNode<K, V> node, K key) {
        int balance = getBalanceFactor(node);

        if (balance < -1 && key.compareTo(node.getLeft().getKey()) < 0) {
            rightRotate(node);
        }

        if (balance > 1 && key.compareTo(node.getRight().getKey()) > 0) {
            leftRotate(node);
        }

        // Left Right Case
        if (balance < -1 && key.compareTo(node.getLeft().getKey()) > 0) {
            node.setLeft(leftRotate((AVLNode<K, V>) node.getLeft()));
            rightRotate(node);
        }

        // Right Left Case
        if (balance > 1 && key.compareTo(node.getRight().getKey()) < 0) {
            node.setRight(rightRotate((AVLNode<K, V>) node.getRight()));
            leftRotate(node);
        }
    }

}
