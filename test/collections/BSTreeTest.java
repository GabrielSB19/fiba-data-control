package collections;

import model.Player;

import collections.bsTree.BSTree;

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
        Player player3 = new Player("Juan", 28, "Chicago Bulls", 203);

        BSTree<Integer, Player> bsTree = setUpScenary1();

        bsTree.insertion(player1.getPoint(), player1);
        bsTree.insertion(player2.getPoint(), player2);
        bsTree.insertion(player3.getPoint(), player3);

        String expected = bsTree.inOrder();

        assertEquals("118 345 203", expected);

    }

}
