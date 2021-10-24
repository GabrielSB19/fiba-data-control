package collections;

import model.Player;
import collections.bsTree.*;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BSTreeTest {
    /*

    public ITree<Integer, Player> setUpScenary1() {
        ITree<Integer, Player> bsTree = new BSTree<>();

        Player player1 = new Player("David", 28, "Chicago Bulls", 345, 52, 30, 20, 10);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118, 20, 35, 46,
                4);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403, 12, 45 ,78, 64);

        bsTree.add(player1.getPoint(), player1);
        bsTree.add(player2.getPoint(), player2);
        bsTree.add(player3.getPoint(), player3);

        return bsTree;
    }

    public ITree<Integer, Player> setUpScenary2() {
        ITree<Integer, Player> bsTree = new BSTree<>();
        Player player1 = null;
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118, 20, 35, 46,
                4);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403,12, 45 ,78, 64);

        bsTree.add(200, player1);
        bsTree.add(player2.getPoint(), player2);
        bsTree.add(player3.getPoint(), player3);
        return bsTree;
    }

    public ITree<Integer, Player> setUpScenary3() {
        ITree<Integer, Player> bsTree = new BSTree<>();
        return bsTree;
    }

    @Test
    public void inOrder() {
        ITree<Integer, Player> bsTree = setUpScenary1();


        String expected = bsTree.inOrder();
        assertEquals(" 118 345 403", expected);


    }

    @Test
    public void inOrder2() {

        ITree<Integer, Player> bsTree = setUpScenary2();
        String expected = bsTree.inOrder();

        assertEquals(" 118 403", expected);

    }

    @Test
    public void search() {

        ITree<Integer, Player> bsTree = setUpScenary1();

        int playerExpected = bsTree.search(403).getKey();

        assertEquals(playerExpected, 403);

    }

    @Test
    public void search2() {

        ITree<Integer, Player> bsTree = setUpScenary2();

        BSNode<Integer, Player> playerExpected = bsTree.search(200);

        assertEquals(playerExpected, null);

    }

    @Test
    public void minimum() {
        ITree<Integer, Player> bsTree = setUpScenary1();

        int minimum = bsTree.minimum(345);

        assertEquals(118, minimum);

    }

    @Test
    public void minimum2() {
        ITree<Integer, Player> bsTree = setUpScenary2();

        Integer minimum = bsTree.minimum(200);

        assertEquals(null, minimum);

    }

    @Test
    public void maximum() {
        ITree<Integer, Player> bsTree = setUpScenary1();

        int maximum = bsTree.maximum(345);

        assertEquals(403, maximum);

    }

    @Test
    public void maximum2() {
        ITree<Integer, Player> bsTree = setUpScenary2();

        Integer maximum = bsTree.maximum(200);

        assertEquals(null, maximum);

    }

    @Test
    public void succesor() {
        ITree<Integer, Player> bsTree = setUpScenary1();

        int succesor = bsTree.sucessor(345);

        assertEquals(403, succesor);

    }

    @Test
    public void succesor2() {
        ITree<Integer, Player> bsTree = setUpScenary2();

        Integer succesor = bsTree.sucessor(200);

        assertEquals(null, succesor);

    }

    @Test
    public void add() {
        Player player1 = new Player("David", 28, "Chicago Bulls", 345, 52, 30, 20, 10);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118, 20, 35, 46,
                4);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403, 12, 45 ,78, 64);

        ITree<Integer, Player> bsTree = setUpScenary1();

        bsTree.add(player1.getPoint(), player1);
        bsTree.add(player2.getPoint(), player2);
        bsTree.add(player3.getPoint(), player3);

        String pE = bsTree.search(345).getValue().getName();
        String pE2 = bsTree.search(118).getValue().getName();

        assertEquals(pE, player1.getName());
        assertEquals(pE2, player2.getName());

    }

    @Test
    public void add2() {
        Player player1 = new Player("David", 28, "Chicago Bulls", 345, 52, 30, 20, 10);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118, 20, 35, 46,
                4);
        Player player3 = null;

        ITree<Integer, Player> bsTree = setUpScenary2();

        bsTree.add(player1.getPoint(), player1);
        bsTree.add(player2.getPoint(), player2);
        bsTree.add(200, player3);

        /*
        String pE = bsTree.search(345).getValue().getName();

        assertEquals(pE, player1.getName());
        assertEquals(bsTree.search(200), null);

    }

    @Test
    public void deletion() {

        ITree<Integer, Player> bsTree = setUpScenary1();

        int minimum = bsTree.minimum(345);

        assertEquals(118, minimum);

        bsTree.delete(118);

        int minimum2 = bsTree.minimum(345);

        assertEquals(345, minimum2);
    }

    @Test
    public void deletion2() {

        ITree<Integer, Player> bsTree = setUpScenary2();


        String inOrder = bsTree.inOrder();

        bsTree.delete(200);

        assertEquals(inOrder, bsTree.inOrder());


    }

     */

}
