package collections.rbTree;

import collections.bsTree.BSTree;

public class RBTree<K extends Comparable<K>, V> extends BSTree<K, V> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private RBNode<K, V> rbRoot = (RBNode<K, V>) root;

	// girar a la izquierda
	// node x
	// / \ Girar a la izquierda / \
	// T1 x ---------> node T3
	// / \ / \
	// T2 T3 T1 T2
	private RBNode<K, V> leftRotate(RBNode<K, V> node) {
		RBNode<K, V> x = (RBNode<K, V>) node.getRight();// definición empezar a rotar
		node.setRight(x.getLeft());
		x.setLeft(node);
		// color
		x.setColor(node.getColor());
		node.setColor(RED);// nodo yx forman 3 nodos
		return x;
	}

	// Gira a la derecha
	// node x
	// / \ Gira a la derecha / \
	// x T2 -------> y node
	// / \ / \
	// y T1 T1 T2
	private RBNode<K, V> rightRotate(RBNode<K, V> node) {
		RBNode<K, V> x = (RBNode<K, V>) node.getLeft();// definición
		// rotar
		node.setLeft(x.getRight());
		x.setRight(node);
		// mantener el color
		x.setColor(node.getColor());
		node.setColor(RED);
		;// Almacenado temporalmente como 4 nodos rojo significa fusión
		return x;// devuelve el nuevo nodo raíz
	}

	// Determine si el nodo raíz del árbol rojo-negro es rojo
	private boolean isRed(RBNode<K, V> node) {
		if (node == null)// Naturaleza del árbol rojo-negro Los nodos vacíos son negros por defecto
			return BLACK;
		return node.getColor();
	}

	@Override
	public RBNode<K, V> add(K key, V value) {
		RBNode<K, V> newNode = new RBNode<K, V>(key, value);
		RBNode<K, V> ancester = (RBNode<K, V>) super.add(newNode);
		insertFixUp(newNode);
		return ancester;
	}

	private void insertFixUp(RBNode<K, V> z) {
		while (((RBNode<K, V>) z.getParent()).getColor()) {
			RBNode<K, V> uncle = null;
			RBNode<K, V> parent = (RBNode<K, V>) z.getParent();
			RBNode<K, V> grandParent = (RBNode<K, V>) z.getParent().getParent();
			if (parent.equals(grandParent.getLeft())) {
				uncle = (RBNode<K, V>) z.getParent().getParent().getRight();
				if (uncle.getColor()) {
					uncle.setColor(BLACK);
					parent.setColor(BLACK);
					grandParent.setColor(RED);
					z = grandParent;
				} else if (z.equals(parent.getRight())) {
					z = parent;
					leftRotate(z);
				}
				RBNode<K, V> newP = (RBNode<K, V>) z.getParent();
				RBNode<K, V> newG = (RBNode<K, V>) z.getParent().getParent();
				newP.setColor(BLACK);
				newG.setColor(RED);
				rightRotate(newG);
			} else {

			}
			rbRoot.setColor(BLACK);
		}
	}

	@Override
	public RBNode<K, V> delete(K key) {
		return null;

	}
}
