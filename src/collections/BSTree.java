package collections;

public class BSTree<K extends Comparable<K>, V extends Comparable<V>> implements IBSTree<K, V> {

    private Node<K, V> root;

    @Override
    public void inOrder() {
        if (root != null) {
            inOrder(root);
        }
    }

    private void inOrder(Node<K, V> current) {
        if (current != null) {
            inOrder(current.getLeft());
            System.out.println(current.getValue());
            inOrder(current.getRight());
        }
    }

    @Override
    public V search(K key) {
        if (root != null) {
            return search(root, key).getValue();
        } else {
            return null;
        }
    }

    private Node<K, V> search(Node<K, V> current, K key) {
        if (key == current.getKey()) {
            return current;
        }
        if (key.compareTo(current.getKey()) < 0) {
            return search(current.getLeft(), key);
        } else {
            return search(current.getRight(), key);
        }
    }

    @Override
    public K mininum() {
        Node<K, V> temp = root;
        while (temp.getLeft() != null) {
            temp = temp.getLeft();
        }
        return temp.getKey();
    }

    @Override
    public K maximun() {
        Node<K, V> temp = root;
        while (temp.getRight() != null) {
            temp = temp.getRight();
        }
        return temp.getKey();
    }

    @Override
    public K sucessor(K key) {
        Node<K, V> temp = (root != null) ? search(root, key) : null;
        if (temp.getRight() != null) {
            return mininum();
        }
        Node<K, V> parent = temp.getParent();
        while (parent != null && temp == parent.getRight()) {
            temp = parent;
            parent = parent.getParent();
        }
        return parent.getKey();
    }

    @Override
    public void insertion(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        Node<K, V> aux = null;
        Node<K, V> temp = root;
        while (temp != null) {
            aux = temp;
            if (newNode.getKey().compareTo(temp.getKey()) < 0) {
                temp = temp.getLeft();
            } else {
                temp = temp.getRight();
            }
        }
        newNode.setParent(aux);
        if (aux == null) {
            root = newNode;
        } else if (newNode.getKey().compareTo(aux.getKey()) < 0) {
            aux.setLeft(newNode);
        } else {
            aux.setRight(newNode);
        }
    }

    @Override
    public void delete(K key) {
        Node<K, V> toDelete = (root != null) ? search(root, key) : null;
        Node<K, V> y = null;
        Node<K, V> x = null;
        if (toDelete.getLeft() == null || toDelete.getRight() == null) {
            y = toDelete;
        } else {
            y = (root != null) ? search(root, sucessor(key)) : null;
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
        } else {
            y.getParent().setRight(x);
        }
        if (y != toDelete) {
            toDelete.setKey(y.getKey());
        }
    }
}
