package controller;

import java.io.IOException;

import com.jfoenix.controls.JFXToggleButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.FibaDataCenter;
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
    private Text lblPlayers;

    @FXML
    private Text lblTeams;

    @FXML
    private Text lblAge;

    @FXML
    private Pane pnName;

    @FXML
    private Label lblDialogName;

    private Stage modal;
    private int searchType;

    private PlayerController pController;
    private FibaDataCenter pFiba;

    public FibaController() {
        pController = new PlayerController();
        pFiba = new FibaDataCenter();
    }

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

    public Stage loadModal(Route route, Object controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(route.getRoute()));
        fxmlLoader.setController(controller);
        Stage stage = new Stage();
        try {
            Parent modal = fxmlLoader.load();
            Scene scene = new Scene(modal);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.initStyle(StageStyle.TRANSPARENT);
        return stage;
    }

    @FXML
    void exportData(MouseEvent event) {

    }

    @FXML
    void importData(MouseEvent event) throws IOException {
        if(pFiba.importData()){
            System.out.println("Importado correctamente");
        } else {
            System.out.println("Error al importar");
        }
    }

    @FXML
    void newPlayer(ActionEvent event) {
        if (pController.getModal() == null) {
            pController.setModal(loadModal(Route.PLAYER, pController));
            pController.getModal().show();
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        modal.close();
        modal = null;
    }

    private void initDialog(int type, String lbl) {
        searchType = type;
        if (modal == null) {
            modal = loadModal(Route.SEARCH, this);
            modal.show();
        }
        lblDialogName.setText(lbl);
        if (type == 1) {
            pnName.setVisible(true);
        } else {
            pnName.setVisible(false);
        }
    }

    @FXML
    void nameSearch(ActionEvent event) {
        initDialog(1, "Name Searching Type");
    }

    @FXML
    void assistSearch(ActionEvent event) {
        initDialog(2, "Assist Searching Type");
    }

    @FXML
    void blockSearch(ActionEvent event) {
        initDialog(3, "Block Searching Type");
    }

    @FXML
    void reboundSearch(ActionEvent event) {
        initDialog(4, "Rebound Searching Type");
    }

    @FXML
    void stealSearh(ActionEvent event) {
        initDialog(5, "Steal Searching Type");
    }
}
