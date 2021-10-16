package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import java.io.IOException;

import com.sun.javafx.application.LauncherImpl;

import controller.FibaController;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import routes.Route;

public class Main extends Application {
    private final int RENDER_COMPONENTES = 4000;
    private FibaController fibaController;

    public Main() {
        fibaController = new FibaController();
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(Main.class, Splash.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(Route.MAINPANE.getRoute()));
        fxmlloader.setController(fibaController);
        Parent root = fxmlloader.load();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    @Override
    public void init() throws IOException, InterruptedException {
        Thread.sleep(RENDER_COMPONENTES);
    }
}