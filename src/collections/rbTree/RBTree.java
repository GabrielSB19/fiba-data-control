package collections.rbTree;

import collections.bsTree.BSTree;

public class RBTree<K extends Comparable<K>, V> extends BSTree<K, V> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private RBNode<K, V> rbRoot;

	private void leftRotate(RBNode<K, V> x) {
		RBNode<K, V> y = (RBNode<K, V>) x.getRight();
		if (y.getLeft() != null) {
			x.setRight(y.getLeft());
			y.getLeft().setParent(x);
		}
		y.setParent(x.getParent());

		if (x.getParent() == null) {
			root = y;
			rbRoot = y;
		} else if (x == x.getParent().getLeft()) {
			x.getParent().setLeft(y);
		} else {
			x.getParent().setRight(y);
		}

		y.setLeft(x);
		x.setParent(y);

	}

	private void rightRotate(RBNode<K, V> x) {
		RBNode<K, V> y = (RBNode<K, V>) x.getLeft();
		if (y.getRight() != null) {
			x.setLeft(y.getRight());
			y.getRight().setParent(x);
		}
		y.setParent(x.getParent());

		if (x.getParent() == null) {
			root = y;
			rbRoot = y;
		} else if (x == x.getParent().getLeft()) {
			x.getParent().setLeft(y);
		} else {
			x.getParent().setRight(y);
		}

		y.setRight(x);
		x.setParent(y);

	}

	@Override
	public RBNode<K, V> add(K key, V value) {
		RBNode<K, V> newNode = new RBNode<K, V>(key, value);
		RBNode<K, V> ancester = (RBNode<K, V>) super.add(newNode);
		if (ancester != null) {
			insertFixUp(newNode);
		} else {
			rbRoot = (RBNode<K, V>) root;
			rbRoot.setColor(BLACK);
		}
		return ancester;
	}

	private void insertFixUp(RBNode<K, V> z) {
		while (!z.equals(rbRoot) && ((RBNode<K, V>) z.getParent()).getColor()) {
			RBNode<K, V> uncle = null;
			RBNode<K, V> parent = (RBNode<K, V>) z.getParent();
			RBNode<K, V> grandParent = (RBNode<K, V>) z.getParent().getParent();
			if (parent.equals(grandParent.getLeft())) {
				uncle = (RBNode<K, V>) grandParent.getRight();
				if (uncle != null && uncle.getColor()) {
					uncle.setColor(BLACK);
					parent.setColor(BLACK);
					grandParent.setColor(RED);
					z = grandParent;
				} else if (z.equals(parent.getRight())) {
					z = parent;
					leftRotate(z);
				} else {
					parent.setColor(BLACK);
					grandParent.setColor(RED);
					rightRotate(grandParent);
				}
			} else {
				uncle = (RBNode<K, V>) grandParent.getLeft();
				if (uncle != null && uncle.getColor()) {
					uncle.setColor(BLACK);
					parent.setColor(BLACK);
					grandParent.setColor(RED);
					z = grandParent;
				} else if (z.equals(parent.getLeft())) {
					z = parent;
					rightRotate(z);
				} else {
					parent.setColor(BLACK);
					grandParent.setColor(RED);
					leftRotate(grandParent);
				}
			}
		}
		rbRoot.setColor(BLACK);
	}

	@Override
	public RBNode<K, V> delete(K key, V value) {
		RBNode<K, V> ancester = (RBNode<K, V>) super.delete(key, value);
		if (ancester != null && ancester.getColor() == BLACK) {
			deleteFixUp(ancester);
		} else {
			rbRoot = null;
		}
		return ancester;
	}

	private void deleteFixUp(RBNode<K, V> z) {
		while (z != rbRoot && ((RBNode<K, V>) z).getColor() == BLACK) {
			RBNode<K, V> brother = null;
			RBNode<K, V> parent = (RBNode<K, V>) z.getParent();
			if (z.equals(parent.getLeft())) {
				brother = (RBNode<K, V>) parent.getRight();
				if (brother.getColor() == RED) {
					brother.setColor(BLACK);
					parent.setColor(RED);
					leftRotate(parent);
					brother = (RBNode<K, V>) z.getParent().getRight();
				}
				if (!((RBNode<K, V>) brother.getLeft()).getColor() && !((RBNode<K, V>) brother.getRight()).getColor()) {
					brother.setColor(RED);
					z = parent;
				} else if (!((RBNode<K, V>) brother.getRight()).getColor()) {
					((RBNode<K, V>) brother.getLeft()).setColor(BLACK);
					brother.setColor(RED);
					rightRotate(brother);
					brother = (RBNode<K, V>) parent.getRight();
				} else {
					brother.setColor(parent.getColor());
					parent.setColor(BLACK);
					((RBNode<K, V>) brother.getRight()).setColor(BLACK);
					leftRotate(parent);
					z = rbRoot;
				}
			} else {
				brother = (RBNode<K, V>) parent.getLeft();
				if (brother.getColor() == RED) {
					brother.setColor(BLACK);
					parent.setColor(RED);
					leftRotate(parent);
					brother = (RBNode<K, V>) z.getParent().getLeft();
				}
				if (!((RBNode<K, V>) brother.getRight()).getColor() && !((RBNode<K, V>) brother.getLeft()).getColor()) {
					brother.setColor(RED);
					z = parent;
				} else if (!((RBNode<K, V>) brother.getLeft()).getColor()) {
					((RBNode<K, V>) brother.getRight()).setColor(BLACK);
					brother.setColor(RED);
					rightRotate(brother);
					brother = (RBNode<K, V>) parent.getLeft();
				} else {
					brother.setColor(parent.getColor());
					parent.setColor(BLACK);
					((RBNode<K, V>) brother.getLeft()).setColor(BLACK);
					leftRotate(parent);
					z = rbRoot;
				}
			}
		}
		z.setColor(BLACK);
	}
}
