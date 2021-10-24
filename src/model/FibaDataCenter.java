package model;

import collections.*;
import collections.avlTree.AVLTree;
import collections.bsTree.BSTree;
import collections.rbTree.RBTree;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;

public class FibaDataCenter {

    private ITree<Integer, Player> trees[];

    public FibaDataCenter(){
        trees = new ITree[5];
        initTree();
    }

    /*
    0 - Points
    1 - Assists
    2 - Blocks
    3 - Rebounds
    4 - Steals
     */
    private void initTree (){
        trees[0] = new BSTree<>();
        trees[1] = new AVLTree<>();
        trees[2] = new AVLTree<>();
        trees[3] = new AVLTree<>();
        trees[4] = new RBTree<>();
    }

    private File fileChooser() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open data base");
        File file = fc.showOpenDialog(null);
        return file;
    }

    public boolean importData () throws IOException {
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileChooser()));
            String line = br.readLine();
            line = br.readLine();
            long start = System.currentTimeMillis();
            int count = 0;
            while (line != null){
                String parts[] = line.split(",");
                Player py = new Player(parts[0], Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]),
                        Integer.parseInt(parts[5]), Integer.parseInt(parts[6]), Integer.parseInt(parts[7]));
                trees[0].add(py.getPoint(), py);
                trees[1].add(py.getAssists(), py);
                trees[2].add(py.getBlocks(), py);
                trees[3].add(py.getBounces(), py);
                //trees[4].add(py.getSteals(), py);
                line = br.readLine();
            }
            long end = System.currentTimeMillis();
            System.out.println(end - start);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public ArrayList<Player> getPlayers(){
        return trees[1].inOrder();
    }
    /*
    BufferedReader br = new BufferedReader(new FileReader(fileChooser()));
            String line = br.readLine();
            line = br.readLine();
     */


}
