package model;

import collections.*;
import collections.avlTree.AVLTree;
import collections.bsTree.BSTree;
import collections.rbTree.RBTree;
import javafx.stage.FileChooser;

import java.io.*;

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
            String line;
            line = br.readLine();
            while (line != null){
                String parts[] = line.split(",");
                System.out.println(parts.length);
                //Player py = new Player(parts[0], parts[1], parts[2], parts[3], parts[4]);
                //trees[0].add(parts[4], )
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }
    /*
    BufferedReader br = new BufferedReader(new FileReader(fileChooser()));
            String line = br.readLine();
            line = br.readLine();
     */


}
