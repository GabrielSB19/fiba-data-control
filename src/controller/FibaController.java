package controller;

import com.jfoenix.controls.JFXToggleButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import routes.Route;

public class FibaController {
    @FXML
    private Pane mainPane;

    @FXML
    private Circle btnCloseLogin;

    @FXML
    private Circle btnMinimize;

    @FXML
    private JFXToggleButton palette;

    @FXML
    void handleMouseClick(MouseEvent event) {
        if (event.getSource() == btnCloseLogin) {
            System.exit(0);
        } else if (event.getSource() == btnMinimize) {
            getWindow().setIconified(true);
        }
    }

    private Stage getWindow() {
        return (Stage) mainPane.getScene().getWindow();
    }

    @FXML
    public void changePalette(ActionEvent event) {
        mainPane.getStylesheets().clear();
        if (palette.isSelected()) {
            mainPane.getStylesheets().add(Route.LIGHT.getRoute());
        } else {
            mainPane.getStylesheets().add(Route.DARK.getRoute());
        }
    }
}
