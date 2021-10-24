package collections.rbTree;

import collections.bsTree.BSNode;
import collections.bsTree.BSTree;

public class RBTree<K extends Comparable<K>, V> extends BSTree<K,V>  {

	private static final boolean RED = true;
	private static final boolean BLACK = false;


	// girar a la izquierda
	// node x
	// / \ Girar a la izquierda / \
	// T1 x ---------> node T3
	// / \ / \
	// T2 T3 T1 T2
	private RBNode<K, V> leftRotate(RBNode<K, V> node) {
		RBNode<K, V> x = node.getRight();// definición
		// empezar a rotar
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
		RBNode<K, V> x = node.getLeft();// definición
		// rotar
		node.setLeft(x.getRight());
		x.setRight(node);
		// mantener el color
		x.setColor(node.getColor());
		node.setColor(RED);
		;// Almacenado temporalmente como 4 nodos rojo significa fusión
		return x;// devuelve el nuevo nodo raíz
	}

	// Cambio de color es agregar elementos a la derecha de los 3 nodos
	private void flipColors(RBNode<K, V> node) {
		node.setColor(RED);// Es posible que el nodo raíz deba fusionarse para que sea rojo
		node.getLeft().setColor(BLACK);
		node.getRight().setColor(BLACK);
	}

	// Determine si el nodo raíz del árbol rojo-negro es rojo
	private boolean isRed(RBNode<K, V> node) {
		if (node == null)// Naturaleza del árbol rojo-negro Los nodos vacíos son negros por defecto
			return BLACK;
		return node.getColor();
	}

	// Agrega un nuevo elemento (clave, valor) al árbol
	public void s (K key, V value) {
		root = addI(root, key, value);
		root.setColor(BLACK);// Mantenga el nodo raíz en negro después de agregar
	}

	// Insertar elementos (clave, valor) en el árbol rojo-negro enraizado en el
	// nodo, algoritmo recursivo
	// Devuelve la raíz del árbol rojo-negro después de insertar un nuevo nodo
	private RBNode<K, V> addI(RBNode<K, V> node, K key, V value) {

		if (node == null) {
			size++;
			return new RBNode<K, V>(key, value);// nodo rojo
		}

		if (key.compareTo(node.getKey()) < 0)
			node.setLeft(add(node.getLeft(), key, value));
		else if (key.compareTo(node.getKey()) > 0)
			node.setRight(add(node.getRight(), key, value));
		else // key.compareTo(node.key) == 0
			node.setValue(value);

		// Gira a la izquierda
		if (isRed(node.getRight()) && !isRed(node.getLeft()))// El hijo derecho es rojo, el hijo izquierdo no es rojo
			node = leftRotate(node);
		// Gira a la derecha
		if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft()))// El hijo izquierdo es rojo, el hijo izquierdo del																		// hijo izquierdo también es rojo
			node = rightRotate(node);
		// Cambio de color
		if (isRed(node.getLeft()) && isRed(node.getRight()))// Tanto el niño izquierdo como el derecho son rojos
			flipColors(node);
		return node;
	}



	// Elimina el nodo más pequeño en el árbol de búsqueda binario con raíz en el
	// nodo
	// Devuelve la raíz del nuevo árbol de búsqueda binaria después de eliminar el
	// nodo
	private RBNode<K, V> removeMin(RBNode<K, V> node) {
		if (node.getLeft() == null) {
			RBNode<K, V> rightNode = node.getRight();
			node.setRight(null);
			size--;
			return rightNode;
		}
		node.setLeft(removeMin(node.getLeft()));
		return node;
	}

	// Elimina el nodo clave del árbol de búsqueda binaria
	public V remove(K key) {
		RBNode<K, V> node = getNode(root, key);
		if (node != null) {
			root = remove(root, key);
			return node.getValue();
		}
		return null;
	}

	private RBNode<K, V> remove(RBNode<K, V> node, K key) {

		if (node == null)
			return null;

		if (key.compareTo(node.getKey()) < 0) {
			node.setLeft(remove(node.getLeft(), key));
			return node;
		} else if (key.compareTo(node.getKey()) > 0) {
			node.setRight(remove(node.getRight(), key));
			return node;
		} else { // key.compareTo(node.key) == 0
			// El subárbol izquierdo del nodo a eliminar está vacío
			if (node.getLeft() == null) {
				RBNode<K, V> rightNode = node.getRight();
				node.setRight(null);
				size--;
				return rightNode;
			}

			// El caso en el que el subárbol derecho del nodo a eliminar está vacío
			if (node.getRight() == null) {
				RBNode<K, V> leftNode = node.getLeft();
				node.setLeft(null);
				size--;
				return leftNode;
			}

			// Cuando los subárboles izquierdo y derecho del nodo a eliminar no están vacíos

			// Encuentra el sucesor o presusesor
			RBNode<K, V> successor = minimum(node.getRight());
			successor.setRight(removeMin(node.getRight()));
			successor.setLeft(node.getLeft());

			node.setLeft(null);
			node.setRight(null);

			return successor;
		}
	}

	

	@Override
	public void rbInsertion(K key, V value) {
		BSNode<K,V> newNode = new RBNode<K,V>(key, value);
		super.add(newNode);
		
	}

	private void insertFixUp(RBNode<K,V> z){
		while (((RBNode<K, V>) z.getParent()).getColor()) {
			RBNode<K,V> uncle = null;
			RBNode<K,V> parent = (RBNode<K, V>) z.getParent();
			RBNode<K,V> grandParent = (RBNode<K, V>) z.getParent().getParent();
			if(parent.equals(grandParent.getLeft())){
				uncle = (RBNode<K, V>) z.getParent().getParent().getRight();
				if(uncle.getColor()){
					uncle.setColor(BLACK);
					parent.setColor(BLACK);
					grandParent.setColor(RED);
					z = grandParent;
				}else if(z.equals(parent.getRight())){
					z = parent;
					leftRotate(z);
				}else{
					parent.setColor(BLACK);
					grandParent.setColor(RED);
					rightRotate(grandParent);
				}
			}else{

			}
			
		}
	}

	@Override
	public void rbDeletion(K key) {
		// TODO Auto-generated method stub
		
	}
}
