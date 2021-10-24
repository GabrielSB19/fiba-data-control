package controller;

import java.io.IOException;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.FibaDataCenter;
import model.Player;
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

    @FXML
    private TableView<Player> tblPlayers;

    @FXML
    private TableColumn<String, Player> tblcPlayer;

    @FXML
    private TableColumn<Integer, Player> tblcAge;

    @FXML
    private TableColumn<String, Player> tblcTeam;

    @FXML
    private TableColumn<String, Player> tblcPoints;

    @FXML
    private TableColumn<Integer, Player> tblcAsists;

    @FXML
    private TableColumn<Integer, Player> tblcBlocks;

    @FXML
    private TableColumn<Integer, Player> tblcRebounds;

    @FXML
    private TableColumn<Integer, Player> tblcSteals;

    @FXML
    private TableColumn<Player, String> tblcActions;


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
        onTablePlayers();
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

    public void onTablePlayers(){
        ObservableList<Player> listPlayer = FXCollections.observableList(pFiba.getPlayers());
        tblcPlayer.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblcAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        tblcTeam.setCellValueFactory(new PropertyValueFactory<>("team"));
        tblcPoints.setCellValueFactory(new PropertyValueFactory<>("point"));
        tblcAsists.setCellValueFactory(new PropertyValueFactory<>("assists"));
        tblcRebounds.setCellValueFactory(new PropertyValueFactory<>("bounces"));
        tblcSteals.setCellValueFactory(new PropertyValueFactory<>("steals"));
        tblcBlocks.setCellValueFactory(new PropertyValueFactory<>("blocks"));
        renderShelveActions();
        tblPlayers.setItems(listPlayer);
    }

    private void renderShelveActions() {
        Callback<TableColumn<Player, String>, TableCell<Player, String>> cellFact = (
                TableColumn<Player, String> param) -> {
            final TableCell<Player, String> cell = new TableCell<Player, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button delete = new Button("Delete");
                        delete.setId("delete");
                        Button edit = new Button("Edit");
                        edit.setId("edit");
                        edit.getStylesheets().add(Route.DARK.getRoute());
                        delete.getStylesheets().add(Route.DARK.getRoute());
                        delete.setOnAction((ActionEvent event) -> {
                            Player selectedS = (Player) getTableRow().getItem();
                            /*
                            boolean render;
                            if (render) {
                                GameStoreGUI.getInstance().createAlert("The shelve was removed succesfully!",
                                        Route.SUCCESS);
                            } else {
                                GameStoreGUI.getInstance().createAlert("The shelve has games or references Sorry",
                                        Route.ERROR);
                            }
                            
                             */
                        });
                        HBox managebtn = new HBox(edit, delete);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(delete, new Insets(2, 2, 0, 3));
                        HBox.setMargin(edit, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        tblcActions.setCellFactory(cellFact);
    }
}
