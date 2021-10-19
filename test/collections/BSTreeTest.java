package collections;

import model.Player;
import collections.avlTree.AVLTree;
import collections.avlTree.IAVLTree;
import collections.bsTree.*;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BSTreeTest {

    public IBSTree<Integer, Player> setUpScenary1() {
        IBSTree<Integer, Player> bsTree = new BSTree<>();
        Player player1 = new Player("David", 28, "Chicago Bulls", 345);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403);

        bsTree.add(player1.getPoint(), player1);
        bsTree.add(player2.getPoint(), player2);
        bsTree.add(player3.getPoint(), player3);

        return bsTree;
    }

    public IBSTree<Integer, Player> setUpScenary2() {
        IBSTree<Integer, Player> bsTree = new BSTree<>();
        IAVLTree<Integer, Player> aTree = new AVLTree<>();
        aTree.add(2, null);
        return bsTree;
    }

    @Test
    public void inOrder() {
        IBSTree<Integer, Player> bsTree = setUpScenary1();

        String expected = bsTree.inOrder();
        assertEquals(" 118 345 403", expected);
    }

    @Test
    public void inOrder2() {
        Player player1 = new Player("David", 28, "Chicago Bulls", 345);
        Player player2 = null;
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403);

        IBSTree<Integer, Player> bsTree = setUpScenary2();

        bsTree.add(player1.getPoint(), player1);
        bsTree.add(player3.getPoint(), player3);

        String expected = bsTree.inOrder();

        assertEquals(" 345 403", expected);

    }

    @Test
    public void search() {

        IBSTree<Integer, Player> bsTree = setUpScenary1();

        int playerExpected = bsTree.search(403).getKey();

        assertEquals(playerExpected, 403);

    }

    @Test
    public void search2() {
        Player player1 = new Player("David", 28, "Chicago Bulls", 345);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118);
        Player player3 = null;

        IBSTree<Integer, Player> bsTree = setUpScenary2();

        bsTree.add(player1.getPoint(), player1);
        bsTree.add(player2.getPoint(), player2);
        bsTree.add(null, player3); // No se pueden insertar keys nulas

        BSNode<Integer, Player> playerExpected = bsTree.search(null);

        assertEquals(playerExpected, player3);

    }

    @Test
    public void minimum() {
        IBSTree<Integer, Player> bsTree = setUpScenary1();

        int minimum = bsTree.minimum(345);

        assertEquals(118, minimum);

    }

    @Test
    public void minimum2() {

        IBSTree<Integer, Player> bsTree = setUpScenary1();

        int minimum = bsTree.minimum(403);

        assertEquals(403, minimum);

    }

}
