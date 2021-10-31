package model;

import org.junit.Test;

import collections.ITree;
import collections.bsTree.BSTree;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class FibaDataCenterTest {

    private Player[] players;

    private FibaDataCenter fibaDataCenter = new FibaDataCenter();

    private void getPlayers() {
        Player player1 = new Player("David", 28, "Chicago Bulls", 345, 52, 30, 20, 10);
        Player player2 = new Player("Jacobo", 20, "Chicago Bulls", 118, 20, 35, 46, 4);
        Player player3 = new Player("Juan", 24, "Chicago Bulls", 403, 12, 45, 78, 64);

        players = new Player[] { player1, player2, player3 };

    }

    public ITree<Integer, Player> setUpScenary1() {
        ITree<Integer, Player> bsTree = new BSTree<>();

        getPlayers();

        bsTree.add(players[0].getPoint(), players[0]);
        bsTree.add(players[1].getPoint(), players[1]);
        bsTree.add(players[2].getPoint(), players[2]);

        return bsTree;
    }

    @Test
    public void filterData() {

        ITree<Integer, Player> bsTree = setUpScenary1();

        ITree<Integer, Player>[] array = fibaDataCenter.getTrees();

        array[0] = bsTree;

        ArrayList<Player> playersArray = fibaDataCenter.filterData(1, 100, 350);

        assertEquals(playersArray.get(0), players[1]);
        assertEquals(playersArray.get(1), players[0]);
    }

    @Test
    public void updateIndicators() {

        ITree<Integer, Player> bsTree = setUpScenary1();

        int[] data = fibaDataCenter.updateIndicators(bsTree.inOrder());

        assertEquals(data[0], 0);
        assertEquals(data[1], 1);
        assertEquals(data[2], 24);

    }

}
