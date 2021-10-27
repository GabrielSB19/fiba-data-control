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

    private void leftRotate(AVLNode<K, V> x) {
        AVLNode<K, V> y = (AVLNode<K, V>) x.getRight();

        if (y.getLeft() != null) {
            y.getLeft().setParent(x);
        }
        x.setRight(y.getLeft());
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

    }

    private void rightRotate(AVLNode<K, V> x) {
        AVLNode<K, V> y = (AVLNode<K, V>) x.getLeft();

        if (y.getRight() != null) {
            y.getRight().setParent(x);
        }
        x.setLeft(y.getRight());
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

    }

    @Override
    public AVLNode<K, V> add(K key, V value) {
        AVLNode<K, V> newNode = new AVLNode<>(key, value);
        AVLNode<K, V> ancester = (AVLNode<K, V>) super.add(newNode);
        if (ancester != null) {
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

        return height(castR) - height(castL);
    }

    public void rebalance(AVLNode<K, V> node, K key) {
        while (node != null) {
            updateHeight(node);
            int balance = getBalanceFactor(node);
            int rSonBalance = getBalanceFactor((AVLNode<K, V>) node.getRight());
            int lSonBalance = getBalanceFactor((AVLNode<K, V>) node.getLeft());
            // A-B
            if (balance > 1 && (rSonBalance == 0 || rSonBalance == 1)) {
                leftRotate(node);
            }

            // C
            if (balance > 1 && rSonBalance == -1) {
                rightRotate((AVLNode<K, V>) node.getRight());
                leftRotate(node);
            }

            // D-E
            if (balance < -1 && (lSonBalance == 0 || lSonBalance == -1)) {
                rightRotate(node);
            }

            // F
            if (balance < -1 && lSonBalance == 1) {
                leftRotate((AVLNode<K, V>) node.getLeft());
                rightRotate(node);
            }
            node = (AVLNode<K, V>) node.getParent();
        }

    }

}
