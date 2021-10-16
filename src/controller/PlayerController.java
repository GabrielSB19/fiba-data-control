package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class PlayerController {

    private Stage modal;


    public Stage getModal() {
        return this.modal;
    }

    public void setModal(Stage modal) {
        this.modal = modal;
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
