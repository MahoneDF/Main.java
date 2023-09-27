package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.SplittableRandom;


public class StartMenu extends Application {
    public static Stage stage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        StartMenu.stage = stage;
        URL url = getClass().getResource("/FXML/StartMenu.fxml");
        BorderPane startMenu = FXMLLoader.load(url);
        Background background = new Background(setBackGround());
        startMenu.setBackground(background);
        Scene scene = new Scene(startMenu);
        stage.setScene(scene);
        stage.show();
    }

    public void goToRegisterMenu(MouseEvent mouseEvent) throws Exception {
        RegisterMenu.registerMenuControllerGraphics = new RegisterMenuControllerGraphics();
        new RegisterMenu().start(StartMenu.stage);
    }

    public void goToLoginMenu(MouseEvent mouseEvent) throws Exception {
        LoginMenu.loginMenuControllerGraphics = new LoginMenuControllerGraphics();
        new LoginMenu().start(StartMenu.stage);
    }

    public void exit(MouseEvent mouseEvent) {
        Platform.exit();
    }
     private BackgroundImage setBackGround() {
        Image image = new Image(StartMenu.class.getResource("/images/blue.jpg").toExternalForm(), 800 ,600, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return backgroundImage;
    }
}