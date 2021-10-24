package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.FibaDataCenter;
import model.Player;

public class PlayerController {

    private Stage modal;

    @FXML
    private JFXSlider assists;

    @FXML
    private JFXSlider blocks;

    @FXML
    private JFXSlider rebounds;

    @FXML
    private JFXSlider steals;
    
    @FXML
    private Label lblAssits;

    @FXML
    private Label lblBlocks;

    @FXML
    private Label lblRebounds;

    @FXML
    private Label lblSteals;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtTeam;

    @FXML
    private JFXTextField txtAge;

    @FXML
    private JFXTextField txtPoints;

    @FXML
    private JFXButton cancel;

    @FXML
    private JFXButton save;

    @FXML
    private JFXButton edit;

    private Player current;
    private FibaDataCenter pFiba;
    private FibaController pController;

    public PlayerController(FibaDataCenter pFiba, FibaController pController){
        this.pFiba = pFiba;
        this.pController = pController;
    }

    public Stage getModal() {
        return this.modal;
    }

    public void setModal(Stage modal) {
        this.modal = modal;
    }

    @FXML
    void refresh(MouseEvent event) {
        Object e = event.getSource();
        if (e.equals(assists)) {
            lblAssits.setText((int)assists.getValue() + " %");
        } else if (e.equals(blocks)) {
            lblBlocks.setText((int)blocks.getValue() + " %");
        } else if (e.equals(rebounds)) {
            lblRebounds.setText((int)rebounds.getValue() + " %");
        } else {
            lblSteals.setText((int)steals.getValue() + " %");
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        modal.close();
        modal = null;
    }

    @FXML
    public void editPlayer(ActionEvent event) {
        pFiba.deletePlayer(current);
        current.setName(txtName.getText());
        current.setAge(Integer.parseInt(txtAge.getText()));
        current.setTeam(txtTeam.getText());
        current.setPoint(Integer.parseInt(txtPoints.getText()));
        current.setAssists((int) assists.getValue());
        current.setBlocks((int) blocks.getValue());
        current.setBounces((int) rebounds.getValue());
        current.setSteals((int) steals.getValue());
        savePlayer(event);
    }

    @FXML
    void savePlayer(ActionEvent event) {
        if(current == null){
            Player py = new Player(txtName.getText(), Integer.parseInt(txtAge.getText()), txtTeam.getText(), Integer.parseInt(txtPoints.getText()),
                    (int) assists.getValue(),(int) blocks.getValue(), (int) rebounds.getValue(), (int) steals.getValue());
            pFiba.addPlayer(py);
        } else {
            pFiba.addPlayer(current);
            current = null;
        }
        pController.onTablePlayers();
    }

    public void preparePlayerEdition(Player selected) {
        current = selected;
        txtName.setText(String.valueOf(selected.getName()));
        txtAge.setText(selected.getAge()+"");
        txtTeam.setText(selected.getTeam());
        txtPoints.setText(selected.getPoint()+"");
        assists.setValue(selected.getAssists());
        blocks.setValue(selected.getBlocks());
        rebounds.setValue(selected.getBounces());
        steals.setValue(selected.getSteals());
        lblAssits.setText(assists.getValue()+"%");
        lblBlocks.setText(blocks.getValue()+"%");
        lblRebounds.setText(rebounds.getValue()+"%");
        lblSteals.setText(steals.getValue()+"%");
        edit.setVisible(true);
        save.setVisible(false);
    }
}
