package collections;

import model.Player;
import collections.bsTree.*;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class BSTreeTest {

    private Player[] players;

    private void getPlayers() {
        Player player1 = new Player("David", 28, "Chicago Bulls", 345, 52, 30, 20, 10);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118, 20, 35, 46, 4);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403, 12, 45, 78, 64);
        Player player4 = null;

        players = new Player[] { player1, player2, player3, player4 };

    }

    public ITree<Integer, Player> setUpScenary1() {
        ITree<Integer, Player> bsTree = new BSTree<>();

        getPlayers();

        bsTree.add(players[0].getPoint(), players[0]);
        bsTree.add(players[1].getPoint(), players[1]);
        bsTree.add(players[2].getPoint(), players[2]);

        return bsTree;
    }

    public ITree<Integer, Player> setUpScenary2() {
        ITree<Integer, Player> bsTree = new BSTree<>();

        getPlayers();

        bsTree.add(200, players[3]);
        bsTree.add(players[1].getPoint(), players[1]);
        bsTree.add(players[2].getPoint(), players[2]);
        return bsTree;
    }

    @Test
    public void inOrder() {
        ITree<Integer, Player> bsTree = setUpScenary1();

        ArrayList<Player> expected = bsTree.inOrder();

        assertEquals(players[1].getPoint(), expected.get(0).getPoint());
        assertEquals(players[0].getPoint(), expected.get(1).getPoint());
        assertEquals(players[2].getPoint(), expected.get(2).getPoint());
    }

    @Test
    public void inOrder2() {

        ITree<Integer, Player> bsTree = setUpScenary2();

        ArrayList<Player> expected = bsTree.inOrder();

        assertEquals(players[1].getPoint(), expected.get(0).getPoint());
        assertEquals(players[2].getPoint(), expected.get(1).getPoint());
    }

    @Test
    public void search() {

        ITree<Integer, Player> bsTree = setUpScenary1();

        BSNode<Integer, Player> playerExpected = bsTree.search(403, players[2]);

        assertEquals(playerExpected.getValue().getPoint(), players[2].getPoint());

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

        BSNode<Integer, Player> toSearch = new BSNode<>(345, players[0]);

        BSNode<Integer, Player> succesor = bsTree.sucessor(toSearch);

        assertEquals(toSearch, succesor);

    }

    @Test
    public void succesor2() {
        ITree<Integer, Player> bsTree = setUpScenary2();

        BSNode<Integer, Player> toSearch = new BSNode<>(345, players[0]);

        BSNode<Integer, Player> succesor = bsTree.sucessor(toSearch);

        assertEquals(toSearch, succesor);

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

        assertEquals(pE, bsTree.add(player1.getPoint(), player1));
        assertEquals(pE2, bsTree.add(player2.getPoint(), player2));

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

        BSNode<Integer, Player> pE = bsTree.search(200, null);

        assertEquals(pE, null);

    }

    @Test
    public void deletion() {

        ITree<Integer, Player> bsTree = setUpScenary1();

        bsTree.delete(118, players[1]);

        ArrayList<Player> inOrderAfter = bsTree.inOrder();

        assertEquals(inOrderAfter.get(0).getPoint(), 345);
        assertEquals(inOrderAfter.get(1).getPoint(), 403);
    }

    @Test
    public void deletion2() {

        ITree<Integer, Player> bsTree = setUpScenary2();

        ArrayList<Player> inOrder = bsTree.inOrder();

        bsTree.delete(200, null);

        assertEquals(inOrder, bsTree.inOrder());

    }

}
