package collections.rbTree;

import collections.bsTree.BSNode;
import collections.bsTree.BSTree;

public class RBTree<K extends Comparable<K>, V> extends BSTree<K, V> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private RBNode<K, V> rbRoot;

	private RBNode<K, V> leftRotate(RBNode<K, V> x) {
		RBNode<K, V> y = (RBNode<K, V>) x.getRight();
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

		y.setColor(x.getColor());
		x.setColor(RED);
		return y;
	}

	private RBNode<K, V> rightRotate(RBNode<K, V> x) {
		RBNode<K, V> y = (RBNode<K, V>) x.getLeft();

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

		y.setColor(x.getColor());
		x.setColor(RED);
		return y;
	}

	private void flipColors(RBNode<K, V> node) {
		node.setColor(RED);
		((RBNode<K, V>) node.getLeft()).setColor(BLACK);
		((RBNode<K, V>) node.getRight()).setColor(BLACK);
	}

	private boolean isRed(BSNode<K, V> node) {
		if (node == null)// Null nodes are null by default
			return BLACK;
		return ((RBNode<K, V>) node).getColor();
	}

	@Override
	public RBNode<K, V> add(K key, V value) {
		rbRoot = add(rbRoot, key, value);

		((RBNode<K, V>) rbRoot).setColor(BLACK);

		root = rbRoot;
		return rbRoot;
	}

	private RBNode<K, V> add(RBNode<K, V> node, K key, V value) {
		if (node == null) {
			return new RBNode<K, V>(key, value);
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

		return node;

	}

	@Override
	public RBNode<K, V> delete(K key, V value) {

		rbRoot = (RBNode<K, V>) super.delete(key, value);

		return rbRoot;

	}

}
