package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.FibaDataCenter;
import model.Player;
import routes.Route;
import thread.Observer;

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

    @FXML
    private Label lblTime;

    @FXML
    public JFXProgressBar pBar;

    // Player Modal
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

    private Stage modal;
    private int searchType;

    private FibaDataCenter pFiba;
    private Player selected;

    public FibaController() {
        pFiba = new FibaDataCenter();
    }

    @FXML
    public void handleMouseClick(MouseEvent event) {
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

    public Stage loadModal(Route route) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(route.getRoute()));
        fxmlLoader.setController(this);
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
    public void exportData(MouseEvent event) {

    }

    private File fileChooser() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open data base");
        File file = fc.showOpenDialog(null);
        return file;
    }

    @FXML
    public void importData(MouseEvent event) throws IOException {
        File f = fileChooser();
        if (f != null) {
            BufferedReader br = new BufferedReader(new FileReader(f));
            Observer obs = new Observer(this, pFiba, br);
            obs.init();
        }
    }

    @FXML
    public void newPlayer(ActionEvent event) {
        if (modal == null) {
            modal = loadModal(Route.PLAYER);
            edit.setVisible(false);
            save.setVisible(true);
            modal.show();
        }
    }

    @FXML
    public void cancel(ActionEvent event) {
        modal.close();
        modal = null;
    }

    private void initDialog(int type, String lbl) {
        searchType = type;
        if (modal == null) {
            modal = loadModal(Route.SEARCH);
            modal.show();
        }
        lblDialogName.setText(lbl);
    }

    @FXML
    public void pointSearch(ActionEvent event) {
        initDialog(1, "Point Searching Type");
    }

    @FXML
    public void assistSearch(ActionEvent event) {
        initDialog(2, "Assist Searching Type");
    }

    @FXML
    public void blockSearch(ActionEvent event) {
        initDialog(3, "Block Searching Type");
    }

    @FXML
    public void reboundSearch(ActionEvent event) {
        initDialog(4, "Rebound Searching Type");
    }

    @FXML
    public void stealSearh(ActionEvent event) {
        initDialog(5, "Steal Searching Type");
    }

    @FXML
    public void search(ActionEvent event) {
        pFiba.filterData(searchType);
    }

    public void onTablePlayers() {
        ArrayList<Player> data = pFiba.getPlayers();
        ObservableList<Player> listPlayer = FXCollections.observableList(data);
        int[] indicators = pFiba.updateIndicators(data);
        lblPlayers.setText(indicators[0] + "");
        lblTeams.setText(indicators[1] + "");
        lblAge.setText(indicators[2] + "");
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
                        edit.getStylesheets().add(Route.LIGHT.getRoute());
                        delete.getStylesheets().add(Route.DARK.getRoute());
                        delete.setOnAction((ActionEvent event) -> {
                            selected = (Player) getTableRow().getItem();
                            pFiba.deletePlayer(selected);
                            onTablePlayers();
                        });
                        edit.setOnAction((ActionEvent event) -> {
                            selected = (Player) getTableRow().getItem();
                            modal = loadModal(Route.PLAYER);
                            modal.show();
                            preparePlayerEdition();
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

    @FXML
    public void refresh(MouseEvent event) {
        updateLabels();
    }

    private void updateLabels() {
        lblAssits.setText((int) assists.getValue() + " %");
        lblBlocks.setText((int) blocks.getValue() + " %");
        lblRebounds.setText((int) rebounds.getValue() + " %");
        lblSteals.setText((int) steals.getValue() + " %");
    }

    @FXML
    public void editPlayer(ActionEvent event) {
        pFiba.deletePlayer(selected);
        savePlayer(event);
    }

    @FXML
    public void savePlayer(ActionEvent event) {
        Player py = new Player(txtName.getText(), Integer.parseInt(txtAge.getText()), txtTeam.getText(),
                Integer.parseInt(txtPoints.getText()), (int) assists.getValue(), (int) blocks.getValue(),
                (int) rebounds.getValue(), (int) steals.getValue());
        pFiba.addPlayer(py);
        onTablePlayers();
        cancel(event);
    }

    private void preparePlayerEdition() {
        txtName.setText(String.valueOf(selected.getName()));
        txtAge.setText(selected.getAge() + "");
        txtTeam.setText(selected.getTeam());
        txtPoints.setText(selected.getPoint() + "");
        assists.setValue(selected.getAssists());
        blocks.setValue(selected.getBlocks());
        rebounds.setValue(selected.getBounces());
        steals.setValue(selected.getSteals());
        updateLabels();
        edit.setVisible(true);
        save.setVisible(false);
    }
}
