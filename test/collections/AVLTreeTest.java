package collections;

import model.Player;
import collections.avlTree.*;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTest {

    private Player[] players;

    private void getPlayers() {
        Player player1 = new Player("David", 28, "Chicago Bulls", 345, 52, 30, 20, 10);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118, 20, 35, 46, 4);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403, 12, 45, 78, 64);
        Player player4 = null;

        players = new Player[] { player1, player2, player3, player4 };

    }

    public AVLTree<Integer, Player> setUpScenary1() {
        AVLTree<Integer, Player> avlTree = new AVLTree<>();

        getPlayers();

        avlTree.add(players[0].getPoint(), players[0]);
        avlTree.add(players[1].getPoint(), players[1]);
        avlTree.add(players[2].getPoint(), players[2]);

        return avlTree;
    }

    @Test
    public void add() {
        Player player1 = new Player("David", 28, "Chicago Bulls", 3, 52, 30, 20, 10);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 2, 20, 35, 46, 4);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 1, 12, 45, 78, 64);

        AVLTree<Integer, Player> avlTree = setUpScenary1();

        avlTree.add(player1.getPoint(), player1);
        avlTree.add(player2.getPoint(), player2);
        avlTree.add(player3.getPoint(), player3);

        AVLNode<Integer, Player> pE = new AVLNode<>(3, player1);

        int getBalance = avlTree.getBalanceFactor(pE);

        assertEquals(getBalance, 0);
    }

    @Test
    public void delete() {
        Player player1 = new Player("David", 28, "Chicago Bulls", 3, 52, 30, 20, 10);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 2, 20, 35, 46, 4);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 1, 12, 45, 78, 64);
        Player player4 = new Player("Joaquin", 20, "Chicago Bulls", 0, 0, 0, 0, 2);

        AVLTree<Integer, Player> avlTree = setUpScenary1();

        avlTree.add(player1.getPoint(), player1);
        avlTree.add(player2.getPoint(), player2);
        avlTree.add(player3.getPoint(), player3);
        avlTree.add(player4.getPoint(), player3);

        avlTree.delete(2, player2);

        AVLNode<Integer, Player> pE = new AVLNode<>(3, player1);

        int getBalance = avlTree.getBalanceFactor(pE);

        assertEquals(getBalance, 0);
    }

}
