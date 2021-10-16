package controller;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    void editPlayer(ActionEvent event) {

    }

    @FXML
    void savePlayer(ActionEvent event) {

    }
}
