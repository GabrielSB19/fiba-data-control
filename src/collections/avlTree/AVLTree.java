package collections.avlTree;

import collections.bsTree.BSNode;
import collections.bsTree.BSTree;

public class AVLTree<K extends Comparable<K>, V> extends BSTree<K, V> implements IAVLTree<K, V> {

    @Override
    public BSNode<K, V> leftRotate(BSNode<K, V> x) {
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

        return (BSNode<K, V>) y;
    }

    @Override
    public BSNode<K, V> rightRotate(BSNode<K, V> x) {
        BSNode<K, V> y = x.getLeft();

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

        return (BSNode<K, V>) y;
    }

    @Override
    public void avlInsertion(K key, V value) {
        BSNode<K,V> ancester = add(key, value);
        if(ancester!=null){
            rebalance(ancester, key);
        }
    }

    @Override
    public void avlDeletion(K key) {
        BSNode<K,V> ancester = delete(key);
        if(ancester!=null){
            rebalance(ancester, key);
        }
    }

    private int getBalanceFactor(BSNode<K, V> node) {
        if (node == null) {
            return 0;
        }
        return height(node.getRight()) - height(node.getLeft());
    }

    @Override
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
