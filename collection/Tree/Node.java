package Tree;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {

    private T data;
    private List<Node<T>> sons;
    private Node<T> father;

    public Node(T data) {
        this.data = data;
        this.sons = new ArrayList<>();
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Node<T>> getSons() {
        return this.sons;
    }

    public void setSons(List<Node<T>> sons) {
        for (Node<T> son : sons) {
            son.setFather(this);
        }
        this.sons = sons;
    }

    public Node<T> getFather() {
        return this.father;
    }

    public void setFather(Node<T> father) {
        this.father = father;
    }

    public Node(Node<T> node) {
        this.data = (T) node.getData();
        sons = new ArrayList<>();
    }

    public void addSon(Node<T> son) {
        son.setFather(this);
        sons.add(son);
    }

    public void addSonIn(int position, Node<T> son) {
        son.setFather(this);
        sons.add(position, son);
    }

    public void deleteSons() {
        sons.clear();
    }

    public void deleteSon(Node<T> sonToDelete) {
        List<Node<T>> list = getSons();
        list.remove(sonToDelete);
    }

    public Node<T> getSonIn(int position) {
        return sons.get(position);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Node) {
            if (((Node<?>) obj).getData().equals(this.data)) {
                return true;
            }

        }
        return false;

    }

    @Override
    public String toString() {
        return data.toString();
    }

}
