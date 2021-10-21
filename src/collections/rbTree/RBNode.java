package collections.rbTree;

import collections.bsTree.BSNode;

public class RBNode<K,V> extends BSNode<K,V>{

    private boolean color;

    public RBNode(K key, V value) {
        super(key, value);
        color = true;// El nuevo nodo predeterminado es rojo, es decir, el rojo representa el nodo
                    // que debe fusionarse
    }

    public boolean getColor() {
        return this.color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

}
