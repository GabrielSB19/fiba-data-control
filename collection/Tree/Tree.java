package Tree;

import java.util.ArrayList;

public class Tree<T> {

    private Node<T> root;

    public Tree(Node<T> root) {
        this.root = root;
    }

    public Node<T> getRoot() {
        return this.root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private boolean found(Node<T> node, T key) {
        boolean founded = false;

        if (node.getData().equals(node)) {
            return true;
        } else {
            for (Node<T> son : node.getSons()) {
                if (found(son, key)) {
                    founded = true;
                }
            }
        }
        return founded;
    }

    public boolean exist(T key) {
        return found(root, key);
    }

    public int numberNodes() {
        return getNumberDescendents(root) + 1;
    }

    public int getNumberDescendents(Node<T> node) {
        int n = node.getSons().size();
        for (Node<T> son : node.getSons()) {
            n += getNumberDescendents(son);
        }
        return n;

    }

    public Node<T> foundNode(Node<T> node, T key) {
        if (node == null) {
            return null;
        } else if (node.getData().equals(key)) {
            return node;
        } else {
            Node<T> newNode = null;
            for (Node<T> son : node.getSons()) {
                if ((newNode = foundNode(son, key)) != null) {
                    return newNode;
                }
            }
        }
        return null;
    }

    public ArrayList<Node<T>> getPreOrder() {
        ArrayList<Node<T>> preOrder = new ArrayList<Node<T>>();
        buildPreOrder(root, preOrder);
        return preOrder;
    }

    public ArrayList<Node<T>> getPostOrder() {
        ArrayList<Node<T>> postOrder = new ArrayList<Node<T>>();
        buildPostOrder(root, postOrder);
        return postOrder;
    }

    private void buildPreOrder(Node<T> node, ArrayList<Node<T>> preOrder) {
        preOrder.add(node);
        for (Node<T> son : node.getSons()) {
            buildPreOrder(son, preOrder);
        }
    }

    private void buildPostOrder(Node<T> node, ArrayList<Node<T>> postOrder) {
        for (Node<T> son : node.getSons()) {
            buildPostOrder(son, postOrder);
        }
        postOrder.add(node);
    }

    public ArrayList<Node<T>> longWay() {
        ArrayList<Node<T>> way = null;
        int max = 0;
        for (ArrayList<Node<T>> rute : getRamas()) {
            if (rute.size() > max) {
                max = rute.size();
                way = rute;
            }
        }
        return way;
    }

    public int getLongWay() {
        return longWay().size();
    }

    public ArrayList<ArrayList<Node<T>>> getRamas() {
        ArrayList<ArrayList<Node<T>>> rutes = new ArrayList<ArrayList<Node<T>>>();
        ArrayList<Node<T>> way = new ArrayList<Node<T>>();
        getPath(root, way, rutes);
        return rutes;
    }

    private void getPath(Node<T> node, ArrayList<Node<T>> way, ArrayList<ArrayList<Node<T>>> rutes) {
        if (way == null)
            return;
        way.add(node);
        if (node.getSons().size() == 0) {
            rutes.add(clone(way));
        }
        for (Node<T> son : node.getSons())
            getPath(son, way, rutes);
        int index = way.indexOf(node);
        for (int i = index; i < way.size(); i++)
            way.remove(index);
    }

    private ArrayList<Node<T>> clone(ArrayList<Node<T>> list) {
        ArrayList<Node<T>> lista = new ArrayList<Node<T>>();
        for (Node<T> node : list)
            lista.add(new Node<T>(node));
        return lista;
    }

}
