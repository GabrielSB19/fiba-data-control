package collections.avlTree;

import collections.ITree;
import collections.bsTree.BSNode;
import collections.bsTree.BSTree;

public class AVLTree<K extends Comparable<K>, V> extends BSTree<K, V> {

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private int height(AVLNode<K, V> node) {
        return (node == null) ? 0 : node.getHeight();
    }

    private void updateHeight(AVLNode<K, V> node) {
        node.setHeight(1 + max(height(node.getLeft()), height(node.getRight())));
    }

    public BSNode<K, V> leftRotate(AVLNode<K, V> x) {
        BSNode<K, V> y = x.getRight();

        x.setRight(y.getLeft());
        y.getLeft().setParent(x);
        y.setParent(x.getParent());

        if(x.getParent()==null){
            root=y;
        }else if(x==x.getParent().getLeft()){
            x.getParent().setLeft(y);
        }else{
            x.getParent().setRight(y);
        }

        y.setLeft(x);
        x.setParent(y);
        
        updateHeight(x);
        updateHeight(y);

        return (AVLNode<K, V>) y;
    }

    public BSNode<K, V> rightRotate(AVLNode<K, V> x) {
        AVLNode<K, V> y = (AVLNode<K, V>) x.getLeft();

        x.setLeft(y.getRight());
        y.getRight().setParent(x);
        y.setParent(x.getParent());

        if(x.getParent()==null){
            root=y;
        }else if(x==x.getParent().getLeft()){
            x.getParent().setLeft(y);
        }else{
            x.getParent().setRight(y);
        }

        y.setRight(x);
        x.setParent(y);

        updateHeight(x);
        updateHeight(y);

        return (AVLNode<K, V>) y;
    }

    @Override
    public BSNode<K, V> add(K key, V value) {
        BSNode<K,V> ancester = super.add(key, value);
        updateHeight(ancester);
        if(ancester!=null){
            rebalance(ancester, key);
        }
        return ancester;
    }

    @Override
    public BSNode<K, V> delete(K key) {
        BSNode<K,V> ancester = super.delete(key);
        if(ancester!=null){
            rebalance(ancester, key);
        }
        return ancester;
    }

    private int getBalanceFactor(BSNode<K, V> node) {
        if (node == null) {
            return 0;
        }
        return height(node.getRight()) - height(node.getLeft());
    }

    public void rebalance(BSNode<K, V> node, K key) {
        int balance = getBalanceFactor(node);

        if (balance < -1 && key.compareTo(node.getLeft().getKey()) < 0) {
            rightRotate(node);
        }

        if (balance > 1 && key.compareTo(node.getRight().getKey()) > 0) {
            leftRotate(node);
        }

        // Left Right Case
        if (balance < -1 && key.compareTo(node.getLeft().getKey()) > 0) {
            node.setLeft(leftRotate((BSNode<K, V>) node.getLeft()));
            rightRotate(node);
        }

        // Right Left Case
        if (balance > 1 && key.compareTo(node.getRight().getKey()) < 0) {
            node.setRight(rightRotate((BSNode<K, V>) node.getRight()));
            leftRotate(node);
        }
    }



}
