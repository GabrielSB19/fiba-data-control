package collections;

import collections.rbTree.*;
import model.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Test;

public class RBTreeTest {

    private Player[] players;

    private void getPlayers() {
        Player player1 = new Player("David", 28, "Chicago Bulls", 345, 52, 30, 20, 10);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118, 20, 35, 46, 4);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403, 12, 45, 78, 64);
        Player player4 = null;

        players = new Player[] { player1, player2, player3, player4 };

    }

    public RBTree<Integer, Player> setUpScenary1() {
        RBTree<Integer, Player> rbTree = new RBTree<>();

        getPlayers();

        rbTree.add(players[0].getPoint(), players[0]);
        rbTree.add(players[1].getPoint(), players[1]);
        rbTree.add(players[2].getPoint(), players[2]);

        return rbTree;
    }

    @Test
    public void add() {
        Player player1 = new Player("David", 28, "Chicago Bulls", 3, 52, 30, 20, 10);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 2, 20, 35, 46, 4);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 1, 12, 45, 78, 64);

        RBTree<Integer, Player> rbTree = setUpScenary1();

        rbTree.add(player1.getPoint(), player1);
        rbTree.add(player2.getPoint(), player2);
        rbTree.add(player3.getPoint(), player3);

        RBNode<Integer, Player> playerRoot = new RBNode<>(2, players[1]);
        RBNode<Integer, Player> playerRightSon = new RBNode<>(3, players[0]);
        RBNode<Integer, Player> playerLeftSon = new RBNode<>(1, players[2]);

        boolean red = !rbTree.isRed(playerRoot);
        boolean red1 = rbTree.isRed(playerRightSon);
        boolean red2 = rbTree.isRed(playerLeftSon);

        assertFalse(red);
        assertEquals(red1, true);
        assertEquals(red2, true);

    }

    @Test
    public void delete() {
        Player player1 = new Player("David", 28, "Chicago Bulls", 3, 52, 30, 20, 10);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 2, 20, 35, 46, 4);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 1, 12, 45, 78, 64);
        Player player4 = new Player("Joaquin", 20, "Chicago Bulls", 0, 0, 0, 0, 2);

        RBTree<Integer, Player> rbTree = setUpScenary1();

        rbTree.add(player1.getPoint(), player1);
        rbTree.add(player2.getPoint(), player2);
        rbTree.add(player3.getPoint(), player3);
        rbTree.add(player4.getPoint(), player3);

        rbTree.delete(2, player2);

        RBNode<Integer, Player> playerRoot = new RBNode<>(1, players[3]);

        boolean red = !rbTree.isRed(playerRoot);

        assertFalse(red);

    }

}
