package structure;

public class BSTree <K extends Comparable<K>, V extends Comparable<V>> implements IBSTree<K, V>{

    private Node<K, V> root;

    @Override
    public void inOrder() {
        if(root != null){
            inOrder(root);
        }
    }

    private void inOrder(Node<K, V> current){
        if(current != null){
            inOrder(current.getLeft());
            System.out.println(current.getValue());
            inOrder(current.getRight());
        }
    }

    @Override
    public V search(K key) {
        if(root != null){
            return search(root, key);
        } else {
            return null;
        }
    }

    private V search(Node<K, V> current, K key){
        if(key == current.getKey()){
            return current.getValue();
        }
        if(key.compareTo(current.getKey()) < 0){
            return search(current.getLeft(), key);
        } else {
            return search(current.getRight(), key);
        }
    }

    @Override
    public V mininum() {
        Node<K, V> temp = root;
        while (temp.getLeft() != null){
            temp = temp.getLeft();
        }
         return temp.getValue();
    }

    @Override
    public V maximun() {
        Node<K, V> temp = root;
        while (temp.getRight() != null){
            temp = temp.getRight();
        }
        return temp.getValue();
    }

    @Override
    public V sucessor() {
        Node<K, V> temp = root;
        if(temp.getRight() == null){
            return mininum();
        }
        Node<K, V> parent = temp.getParent();
        while(parent != null && temp == parent.getRight()){
            temp = parent;
            parent = parent.getParent();

        }
        return parent.getValue();
    }

    @Override
    public void insertion(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        Node<K, V> aux = null;
        Node<K, V> temp = root;
        while (temp != null){
            aux = temp;
            if(newNode.getKey().compareTo(temp.getKey()) < 0){
                temp = temp.getLeft();
            } else {
                temp = temp.getRight();
            }
        }
        newNode.setParent(aux);
        if(aux == null){
            root = newNode;
        } else if (newNode.getKey().compareTo(aux.getKey()) < 0) {
            aux.setLeft(newNode);
        } else {
            aux.setRight(newNode);
        }
    }

    @Override
    public boolean delete(K key) {
    Node<K,V> del = searchForDelete(root,key);
    Node<K,V> aux = null;
    if(del.getLeft()==null || del.getRight()==null){
        aux = del;
    } else {
        aux = sucessor(key);
    }
    if()
        return false;
    }
    private Node<K,V> searchForDelete(Node<K, V> current, K key){
        if(key == current.getKey()){
            return current;
        }
        if(key.compareTo(current.getKey()) < 0){
            return searchForDelete(current.getLeft(), key);
        } else {
            return searchForDelete(current.getRight(), key);
        }
    }
}

