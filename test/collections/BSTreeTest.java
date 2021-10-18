package collections;

import model.Player;

import collections.bsTree.BSTree;
import collections.bsTree.Node;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BSTreeTest {

    public BSTree<Integer, Player> setUpScenary1() {
        BSTree<Integer, Player> bsTree = new BSTree<>();
        return bsTree;
    }

    public BSTree<Integer, Player> setUpScenary2() {
        BSTree<Integer, Player> bsTree = new BSTree<>();
        return bsTree;
    }

    @Test
    public void inOrder() {
        Player player1 = new Player("David", 28, "Chicago Bulls", 345);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403);

        BSTree<Integer, Player> bsTree = setUpScenary1();

        bsTree.insertion(player1.getPoint(), player1);
        bsTree.insertion(player2.getPoint(), player2);
        bsTree.insertion(player3.getPoint(), player3);

        int points = bsTree.search(player2.getPoint()).getKey();

        String expected = bsTree.inOrder();

        assertEquals(" 118 345 403", expected);
        assertEquals(118, points);

    }

    @Test
    public void inOrder2() {
        Player player1 = new Player("David", 28, "Chicago Bulls", 345);
        Player player2 = null;
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403);

        BSTree<Integer, Player> bsTree = setUpScenary2();

        bsTree.insertion(player1.getPoint(), player1);
        bsTree.insertion(player3.getPoint(), player3);

        String expected = bsTree.inOrder();

        assertEquals(" 345 403", expected);

    }

    @Test
    public void search() {
        Player player1 = new Player("David", 28, "Chicago Bulls", 345);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403);

        BSTree<Integer, Player> bsTree = setUpScenary1();

        bsTree.insertion(player1.getPoint(), player1);
        bsTree.insertion(player2.getPoint(), player2);
        bsTree.insertion(player3.getPoint(), player3);

        Node<Integer, Player> playerExpected = bsTree.search(118);

        assertEquals(playerExpected, player2);

    }

    @Test
    public void search2() {
        Player player1 = new Player("David", 28, "Chicago Bulls", 345);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118);
        Player player3 = null;

        BSTree<Integer, Player> bsTree = setUpScenary1();

        bsTree.insertion(player1.getPoint(), player1);
        bsTree.insertion(player2.getPoint(), player2);
        bsTree.insertion(null, player3);

        Node<Integer, Player> playerExpected = bsTree.search(null);

        assertEquals(playerExpected, player3);

    }

    @Test
    public void minimum() {
        Player player1 = new Player("David", 28, "Chicago Bulls", 345);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403);

        BSTree<Integer, Player> bsTree = setUpScenary1();

        bsTree.insertion(player1.getPoint(), player1);
        bsTree.insertion(player2.getPoint(), player2);
        bsTree.insertion(player3.getPoint(), player3);

        int minimum = bsTree.minimum(345);

        assertEquals(player2.getPoint(), minimum);

    }

    @Test
    public void minimum2() {
        Player player1 = new Player("David", 28, "Chicago Bulls", 345);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403);

        BSTree<Integer, Player> bsTree = setUpScenary1();

        bsTree.insertion(player1.getPoint(), player1);
        bsTree.insertion(player2.getPoint(), player2);
        bsTree.insertion(player3.getPoint(), player3);

        int minimum = bsTree.minimum(345);

        assertEquals(118, minimum);

    }

}
