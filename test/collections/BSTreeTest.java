package collections;

import model.Player;
import collections.bsTree.*;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class BSTreeTest {

    public ITree<Integer, Player> setUpScenary1() {
        ITree<Integer, Player> bsTree = new BSTree<>();

        Player player1 = new Player("David", 28, "Chicago Bulls", 345, 52, 30, 20, 10);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118, 20, 35, 46, 4);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403, 12, 45, 78, 64);

        bsTree.add(player1.getPoint(), player1);
        bsTree.add(player2.getPoint(), player2);
        bsTree.add(player3.getPoint(), player3);

        return bsTree;
    }

    public ITree<Integer, Player> setUpScenary2() {
        ITree<Integer, Player> bsTree = new BSTree<>();
        Player player1 = null;
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118, 20, 35, 46, 4);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403, 12, 45, 78, 64);

        bsTree.add(200, player1);
        bsTree.add(player2.getPoint(), player2);
        bsTree.add(player3.getPoint(), player3);
        return bsTree;
    }

    @Test
    public void inOrder() {
        ITree<Integer, Player> bsTree = setUpScenary1();

        Player player1 = new Player("David", 28, "Chicago Bulls", 345, 52, 30, 20, 10);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118, 20, 35, 46, 4);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403, 12, 45, 78, 64);

        ArrayList<Player> expected = bsTree.inOrder();

        assertEquals(player2, expected.get(0));
        assertEquals(player1, expected.get(1));
        assertEquals(player3, expected.get(2));
    }

    @Test
    public void inOrder2() {

        ITree<Integer, Player> bsTree = setUpScenary2();
        ArrayList<Player> expected = bsTree.inOrder();

        assertEquals(" 118 403", expected);

    }

    @Test
    public void search() {

        ITree<Integer, Player> bsTree = setUpScenary1();

        Player player1 = new Player("David", 28, "Chicago Bulls", 345, 52, 30, 20, 10);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118, 20, 35, 46, 4);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403, 12, 45, 78, 64);

        bsTree.add(player1.getPoint(), player1);
        bsTree.add(player2.getPoint(), player2);
        bsTree.add(player3.getPoint(), player3);

        BSNode<Integer, Player> real = new BSNode<>(player3.getPoint(), player3);
        BSNode<Integer, Player> playerExpected = bsTree.search(403, player3);

        assertEquals(playerExpected, real);

    }

    @Test
    public void search2() {

        ITree<Integer, Player> bsTree = setUpScenary2();

        BSNode<Integer, Player> playerExpected = bsTree.search(200, null);

        assertEquals(playerExpected, null);

    }

    @Test
    public void succesor() {
        ITree<Integer, Player> bsTree = setUpScenary1();

        Player player1 = new Player("David", 28, "Chicago Bulls", 345, 52, 30, 20, 10);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118, 20, 35, 46, 4);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403, 12, 45, 78, 64);

        bsTree.add(player1.getPoint(), player1);
        bsTree.add(player2.getPoint(), player2);
        bsTree.add(player3.getPoint(), player3);

        /*
         * 
         * BSNode<Integer, Player> succesor = bsTree.sucessor(345, player2);
         * 
         * assertEquals(403, succesor);
         */

    }

    @Test
    public void succesor2() {
        ITree<Integer, Player> bsTree = setUpScenary2();

        /*
         * Integer succesor = bsTree.sucessor(200, null);
         * 
         * assertEquals(null, succesor);
         */

    }

    @Test
    public void add() {
        Player player1 = new Player("David", 28, "Chicago Bulls", 345, 52, 30, 20, 10);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118, 20, 35, 46, 4);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403, 12, 45, 78, 64);

        ITree<Integer, Player> bsTree = setUpScenary1();

        bsTree.add(player1.getPoint(), player1);
        bsTree.add(player2.getPoint(), player2);
        bsTree.add(player3.getPoint(), player3);

        BSNode<Integer, Player> pE = bsTree.search(345, player1);
        BSNode<Integer, Player> pE2 = bsTree.search(118, player2);

        assertEquals(pE, player1.getName());
        assertEquals(pE2, player2.getName());

    }

    @Test
    public void add2() {
        Player player1 = new Player("David", 28, "Chicago Bulls", 345, 52, 30, 20, 10);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118, 20, 35, 46, 4);
        Player player3 = null;

        ITree<Integer, Player> bsTree = setUpScenary2();

        bsTree.add(player1.getPoint(), player1);
        bsTree.add(player2.getPoint(), player2);
        bsTree.add(200, player3);

        BSNode<Integer, Player> pE = bsTree.search(345, null);

        assertEquals(pE, player1.getName());
        assertEquals(bsTree.search(200, null), null);

    }

    @Test
    public void deletion() {

        Player player1 = new Player("David", 28, "Chicago Bulls", 345, 52, 30, 20, 10);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118, 20, 35, 46, 4);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403, 12, 45, 78, 64);

        ITree<Integer, Player> bsTree = setUpScenary1();

        bsTree.add(player1.getPoint(), player1);
        bsTree.add(player2.getPoint(), player2);
        bsTree.add(player3.getPoint(), player3);

        ArrayList<Player> inOrder = bsTree.inOrder();

        bsTree.delete(118, player2);

        assertEquals(inOrder, bsTree.inOrder());
    }

    @Test
    public void deletion2() {

        ITree<Integer, Player> bsTree = setUpScenary2();

        ArrayList<Player> inOrder = bsTree.inOrder();

        bsTree.delete(200, null);

        assertEquals(inOrder, bsTree.inOrder());

    }

}
