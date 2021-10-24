package model;

import collections.*;
import collections.avlTree.AVLTree;
import collections.bsTree.BSTree;
import collections.rbTree.RBTree;
import java.util.ArrayList;

public class FibaDataCenter {

    private ITree<Integer, Player>[] trees;
    private ArrayList<Player> players = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public FibaDataCenter() {
        trees = new ITree[5];
        initTree();
    }

    /*
     * 0 - Points 1 - Assists 2 - Blocks 3 - Rebounds 4 - Steals
     */
    private void initTree() {
        trees[0] = new BSTree<>();
        trees[1] = new AVLTree<>();
        trees[2] = new AVLTree<>();
        trees[3] = new AVLTree<>();
        trees[4] = new RBTree<>();
    }

    public void filterData(int searchType, int since, int until) {
        switch (searchType) {
        case 1:
            for (int i = since; i <= until; i++) {
                System.out.println(i);
                trees[searchType - 1].filter(players, i);
                
            }

            break;

        case 2:
            for (int i = since; i <= until; i++) {
                trees[searchType - 1].filter(players, i);
            }
            break;

        case 3:
            for (int i = since; i <= until; i++) {
                trees[searchType - 1].filter(players, i);
            }
            break;

        case 4:
            for (int i = since; i <= until; i++) {
                trees[searchType - 1].filter(players, i);
            }
            break;

        case 5:

            break;

        default:
            break;
        }
    }

    public int[] updateIndicators(ArrayList<Player> data) {
        int ageAverage = 0;
        ArrayList<String> teams = new ArrayList<>();
        for (Player player : data) {
            ageAverage += player.getAge();
            if (!teams.contains(player.getTeam())) {
                teams.add(player.getTeam());
            }
        }
        ageAverage /= data.size();
        return new int[] { data.size(), teams.size(), ageAverage };
    }

    public ArrayList<Player> getPlayers(int type) {
        if (type == 1) {
            return trees[1].inOrder();
        } else {
            return players;
        }
    }

    public void deletePlayer(Player py) {
        trees[0].delete(py.getPoint(), py);
        trees[1].delete(py.getAssists(), py);
        trees[2].delete(py.getBlocks(), py);
        trees[3].delete(py.getBounces(), py);
        // trees[4].add(py.getSteals(), py);
    }

    public void addPlayer(Player py) {
        trees[0].add(py.getPoint(), py);
        trees[1].add(py.getAssists(), py);
        trees[2].add(py.getBlocks(), py);
        trees[3].add(py.getBounces(), py);
        // trees[4].add(py.getSteals(), py);
    }
}
