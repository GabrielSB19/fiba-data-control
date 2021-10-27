package collections.rbTree;

import java.util.ArrayList;

import collections.bsTree.BSNode;
import collections.bsTree.BSTree;

public class RBTree<K extends Comparable<K>, V> extends BSTree<K, V> {

	/*
	 * private static final boolean RED = true; private static final boolean BLACK =
	 * false;
	 * 
	 * private void leftRotate(RBNode<K, V> x) { RBNode<K, V> y = (RBNode<K, V>)
	 * x.getRight(); if (y.getLeft() != null) { x.setRight(y.getLeft());
	 * y.getLeft().setParent(x); } y.setParent(x.getParent());
	 * 
	 * if (x.getParent() == null) { root = y; rbRoot = y; } else if (x ==
	 * x.getParent().getLeft()) { x.getParent().setLeft(y); } else {
	 * x.getParent().setRight(y); }
	 * 
	 * y.setLeft(x); x.setParent(y);
	 * 
	 * }
	 * 
	 * private void rightRotate(RBNode<K, V> x) { RBNode<K, V> y = (RBNode<K, V>)
	 * x.getLeft(); if (y.getRight() != null) { x.setLeft(y.getRight());
	 * y.getRight().setParent(x); } y.setParent(x.getParent());
	 * 
	 * if (x.getParent() == null) { root = y; rbRoot = y; } else if (x ==
	 * x.getParent().getLeft()) { x.getParent().setLeft(y); } else {
	 * x.getParent().setRight(y); }
	 * 
	 * y.setRight(x); x.setParent(y);
	 * 
	 * }
	 * 
	 * @Override public RBNode<K, V> add(K key, V value) { RBNode<K, V> newNode =
	 * new RBNode<K, V>(key, value); RBNode<K, V> ancester = (RBNode<K, V>)
	 * super.add(newNode); if (ancester != null) { insertFixUp(newNode); } else {
	 * rbRoot = (RBNode<K, V>) root; rbRoot.setColor(BLACK); } return ancester; }
	 * 
	 * private void insertFixUp(RBNode<K, V> z) { while (!z.equals(rbRoot) &&
	 * ((RBNode<K, V>) z.getParent()).getColor()) { RBNode<K, V> uncle = null;
	 * RBNode<K, V> parent = (RBNode<K, V>) z.getParent(); RBNode<K, V> grandParent
	 * = (RBNode<K, V>) z.getParent().getParent(); if
	 * (parent.equals(grandParent.getLeft())) { uncle = (RBNode<K, V>)
	 * grandParent.getRight(); if (uncle != null && uncle.getColor()) {
	 * uncle.setColor(BLACK); parent.setColor(BLACK); grandParent.setColor(RED); z =
	 * grandParent; } else if (z.equals(parent.getRight())) { z = parent;
	 * leftRotate(z); } else { parent.setColor(BLACK); grandParent.setColor(RED);
	 * rightRotate(grandParent); } } else { uncle = (RBNode<K, V>)
	 * grandParent.getLeft(); if (uncle != null && uncle.getColor()) {
	 * uncle.setColor(BLACK); parent.setColor(BLACK); grandParent.setColor(RED); z =
	 * grandParent; } else if (z.equals(parent.getLeft())) { z = parent;
	 * rightRotate(z); } else { parent.setColor(BLACK); grandParent.setColor(RED);
	 * leftRotate(grandParent); } } } rbRoot.setColor(BLACK); }
	 * 
	 * @Override public RBNode<K, V> delete(K key, V value) { RBNode<K, V> ancester
	 * = (RBNode<K, V>) super.delete(key, value); if (ancester != null &&
	 * ancester.getColor() == BLACK) { deleteFixUp(ancester); } else { rbRoot =
	 * null; } return ancester; }
	 * 
	 * private void deleteFixUp(RBNode<K, V> z) { while (z != rbRoot && ((RBNode<K,
	 * V>) z).getColor() == BLACK) { RBNode<K, V> brother = null; RBNode<K, V>
	 * parent = (RBNode<K, V>) z.getParent(); if (z.equals(parent.getLeft())) {
	 * brother = (RBNode<K, V>) parent.getRight(); if (brother.getColor() == RED) {
	 * brother.setColor(BLACK); parent.setColor(RED); leftRotate(parent); brother =
	 * (RBNode<K, V>) z.getParent().getRight(); } if (!((RBNode<K, V>)
	 * brother.getLeft()).getColor() && !((RBNode<K, V>)
	 * brother.getRight()).getColor()) { brother.setColor(RED); z = parent; } else
	 * if (!((RBNode<K, V>) brother.getRight()).getColor()) { ((RBNode<K, V>)
	 * brother.getLeft()).setColor(BLACK); brother.setColor(RED);
	 * rightRotate(brother); brother = (RBNode<K, V>) parent.getRight(); } else {
	 * brother.setColor(parent.getColor()); parent.setColor(BLACK); ((RBNode<K, V>)
	 * brother.getRight()).setColor(BLACK); leftRotate(parent); z = rbRoot; } } else
	 * { brother = (RBNode<K, V>) parent.getLeft(); if (brother.getColor() == RED) {
	 * brother.setColor(BLACK); parent.setColor(RED); leftRotate(parent); brother =
	 * (RBNode<K, V>) z.getParent().getLeft(); } if (!((RBNode<K, V>)
	 * brother.getRight()).getColor() && !((RBNode<K, V>)
	 * brother.getLeft()).getColor()) { brother.setColor(RED); z = parent; } else if
	 * (!((RBNode<K, V>) brother.getLeft()).getColor()) { ((RBNode<K, V>)
	 * brother.getRight()).setColor(BLACK); brother.setColor(RED);
	 * rightRotate(brother); brother = (RBNode<K, V>) parent.getLeft(); } else {
	 * brother.setColor(parent.getColor()); parent.setColor(BLACK); ((RBNode<K, V>)
	 * brother.getLeft()).setColor(BLACK); leftRotate(parent); z = rbRoot; } } }
	 * z.setColor(BLACK); } }
	 */

	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private RBNode<K, V> rbRoot;

	// girar a la izquierda
	// node x
	// / \ Girar a la izquierda / \
	// T1 x ---------> node T3
	// / \ / \
	// T2 T3 T1 T2
	private RBNode<K, V> leftRotate(RBNode<K, V> x) {
		RBNode<K, V> y = (RBNode<K, V>) x.getRight();// definición
		if (y.getLeft() != null) {
			y.getLeft().setParent(x);
		}
		x.setRight(y.getLeft());
		y.setParent(x.getParent());

		if (x.getParent() == null) {
			rbRoot = y;
		} else if (x == x.getParent().getLeft()) {
			x.getParent().setLeft(y);
		} else {
			x.getParent().setRight(y);
		}

		y.setLeft(x);
		x.setParent(y);

		// color
		y.setColor(x.getColor());
		x.setColor(RED);// nodo nodo yx forman 3 nodos
		return x;
	}

	// Gira a la derecha
	// node x
	// / \ Gira a la derecha / \
	// x T2 -------> y node
	// / \ / \
	// y T1 T1 T2
	private RBNode<K, V> rightRotate(RBNode<K, V> x) {
		RBNode<K, V> y = (RBNode<K, V>) x.getLeft();// definición
		// rotar
		if (y.getRight() != null) {
			y.getRight().setParent(x);
		}
		x.setLeft(y.getRight());
		y.setParent(x.getParent());

		if (x.getParent() == null) {
			rbRoot = y;
		} else if (x == x.getParent().getLeft()) {
			x.getParent().setLeft(y);
		} else {
			x.getParent().setRight(y);
		}

		y.setRight(x);
		x.setParent(y);
		// mantener el color
		y.setColor(x.getColor());
		x.setColor(RED);// Almacenado temporalmente como 4 nodos rojo significa fusión
		return x;// devuelve el nuevo nodo raíz
	}

	// Cambio de color es agregar elementos a la derecha de los 3 nodos
	private void flipColors(RBNode<K, V> node) {
		// Agregar elementos a 3 nodos se convertirá en 3 2 nodos
		node.setColor(RED);// Es posible que el nodo raíz deba fusionarse para que sea rojo
		((RBNode<K, V>) node.getLeft()).setColor(BLACK);
		((RBNode<K, V>) node.getRight()).setColor(BLACK);
	}

	// Determine si el nodo raíz del árbol rojo-negro es rojo
	private boolean isRed(BSNode<K, V> node) {
		if (node == null)// Naturaleza del árbol rojo-negro Los nodos vacíos son negros por defecto
			return BLACK;
		return ((RBNode<K, V>) node).getColor();
	}

	// Agrega un nuevo elemento (clave, valor) al árbol
	@Override
	public RBNode<K, V> add(K key, V value) {
		rbRoot = add(rbRoot, key, value);

		((RBNode<K, V>) rbRoot).setColor(BLACK);// Mantenga el nodo raíz en negro después de agregar

		root = rbRoot;
		return rbRoot;
	}

	// Insertar elementos (clave, valor) en el árbol rojo-negro enraizado en el
	// nodo, algoritmo recursivo
	// Devuelve la raíz del árbol rojo-negro después de insertar un nuevo nodo
	public RBNode<K, V> add(RBNode<K, V> node, K key, V value) {

		if (node == null) {

			return new RBNode<K, V>(key, value);// nodo rojo
		}

		if (key.compareTo(node.getKey()) <= 0) {

			((RBNode<K, V>) node).setLeft((add((RBNode<K, V>) node.getLeft(), key, value)));
		} else if (key.compareTo(node.getKey()) > 0) {
			((RBNode<K, V>) node).setRight((add((RBNode<K, V>) node.getRight(), key, value)));

		}

		// girar a la izquierda
		if (isRed(node.getRight()) && !isRed(node.getLeft()))// El niño derecho es rojo, el niño izquierdo no es rojo
			node = leftRotate(node);
		// Gira a la derecha
		if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft()))// El hijo izquierdo es rojo, el hijo izquierdo del
																		// // hijo izquierdo
			// también es rojo
			node = rightRotate(node);
		// cambio de color
		if (isRed(node.getLeft()) && isRed(node.getRight()))// Tanto el niño izquierdo como el derecho son rojos
			flipColors(node);

		((RBNode<K, V>) rbRoot).setColor(BLACK);// Mantenga el nodo raíz en negro después de agregar
		rbRoot = (RBNode<K, V>) rbRoot;

		return node;

	}

	@Override
	public ArrayList<V> inOrder() {
		values = new ArrayList<>();
		if (rbRoot != null) {
			inOrder(rbRoot);
			return values;
		} else {
			return values;
		}
	}

	protected void inOrder(BSNode<K, V> current) {
		if (current != null) {
			inOrder(current.getLeft());
			System.out.println(current.getKey());
			inOrder(current.getRight());
		}
	}

	// Elimina el nodo clave del árbol de búsqueda binaria
	@Override
	public RBNode<K, V> delete(K key, V value) {

		RBNode<K, V> node = (RBNode<K, V>) super.delete(key, value);

		rbRoot.setColor(BLACK);

		root = rbRoot;

		return node;

	}

}
