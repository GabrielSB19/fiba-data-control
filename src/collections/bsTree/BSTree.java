package collections.bsTree;

import collections.ITree;

import java.util.ArrayList;

public class BSTree<K extends Comparable<K>, V> implements ITree<K, V> {

    protected BSNode<K, V> root;
    protected ArrayList<V> values;

    @Override
    public ArrayList<V> inOrder() {
        values = new ArrayList<>();
        if (root != null) {
            inOrder(root);
            return values;
        } else {
            return values;
        }
    }

    protected void inOrder(BSNode<K, V> current) {
        if (current != null) {
            inOrder(current.getLeft());
            values.add(current.getValue());
            inOrder(current.getRight());
        }
    }

    @Override
    public BSNode<K, V> search(K key, V value) {
        if (root != null) {
            return search(root, key, value);
        } else {
            return null;
        }
    }

    protected BSNode<K, V> search(BSNode<K, V> current, K key, V value) {
        if (value != null) {
            if (current == null || (key.compareTo(current.getKey()) == 0 && value.equals(current.getValue()))) {
                return current;
            }
        } else {
            if (current == null || (key.compareTo(current.getKey()) == 0)) {
                return current;
            }
        }
        if (key.compareTo(current.getKey()) <= 0) {
            return search(current.getLeft(), key, value);
        } else {
            return search(current.getRight(), key, value);
        }
    }

    @Override
    public void filter(ArrayList<V> data, K key) {
        if (root != null) {
            filter(data, search(root, key, null), key);
        }
    }

    protected void filter(ArrayList<V> data, BSNode<K, V> current, K key) {
        while (current != null) {
            if(current.getKey().compareTo(key) == 0){
                data.add(current.getValue());
                current = current.getLeft();
            }else{
                current = current.getRight();
            }
        }
    }

    @Override
    public BSNode<K, V> minimum(K key, V value) {
        BSNode<K, V> temp = search(key, value);
        if (temp != null) {
            while (temp.getLeft() != null) {
                temp = temp.getLeft();
            }
            return temp;
        } else {
            return null;
        }
    }

    @Override
    public K maximum(K key, V value) {
        BSNode<K, V> temp = search(key, value);
        if (temp != null) {
            while (temp.getRight() != null) {
                temp = temp.getRight();
            }
            return temp.getKey();
        } else {
            return null;
        }
    }

    @Override
    public BSNode<K, V> sucessor(K key, V value) {
        BSNode<K, V> temp = search(key, value);
        if (temp != null) {
            if (temp.getRight() != null) {
                return minimum(temp.getRight().getKey(), temp.getRight().getValue());
            }
            BSNode<K, V> parent = temp.getParent();
            while (parent != null && temp.equals(parent.getRight())) {
                temp = parent;
                parent = parent.getParent();
            }
            return (parent == null) ? null : parent;
        } else {
            return null;
        }
    }

    @Override
    public BSNode<K, V> add(K key, V value) {
        if (value != null) {
            BSNode<K, V> newNode = new BSNode<>(key, value);
            return add(newNode);
        } else {
            return null;
        }
    }

    protected BSNode<K, V> add(BSNode<K, V> newNode) {
        BSNode<K, V> aux = null;
        BSNode<K, V> temp = root;

        while (temp != null) {
            // Ancestor
            aux = temp;
            if (newNode.getKey().compareTo(temp.getKey()) <= 0) {
                temp = temp.getLeft();
            } else {
                temp = temp.getRight();
            }
        }
        newNode.setParent(aux);
        if (aux == null) {
            root = newNode;
        } else if (newNode.getKey().compareTo(aux.getKey()) <= 0) {
            aux.setLeft(newNode);
        } else {
            aux.setRight(newNode);
        }
        return aux;
    }

    @Override
    public BSNode<K, V> delete(K key, V value) {
        BSNode<K, V> toDelete = search(key, value);
        if (toDelete != null) {
            BSNode<K, V> y = null;
            BSNode<K, V> x = null;
            if (toDelete.getLeft() == null || toDelete.getRight() == null) {
                y = toDelete;
            } else {
                y = sucessor(key, value);
            }
            if (y.getLeft() != null) {
                x = y.getLeft();
            } else {
                x = y.getRight();
            }
            if (x != null) {
                x.setParent(y.getParent());
            }
            if (y.getParent() == null) {
                root = x;
            } else if (y == y.getParent().getLeft()) {
                y.getParent().setLeft(x);
                return y.getParent();
            } else {
                y.getParent().setRight(x);
                return y.getParent();
            }
            if (y != toDelete) {
                toDelete.setKey(y.getKey());
                return toDelete;
            }
            return null;
        } else {
            return null;
        }
    }

}
