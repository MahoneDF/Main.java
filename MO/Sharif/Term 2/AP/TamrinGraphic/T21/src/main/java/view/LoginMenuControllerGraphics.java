package view;

import controller.LoginController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginMenuControllerGraphics {

    public TextField username;

    @FXML
    private PasswordField password;
    public void goToMainMenu(MouseEvent mouseEvent) throws Exception {
        String userName = username.getText();
        String passWord = password.getText();
        Messages message = LoginController.checkLogin(userName, passWord);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("login error");
        switch (message){
            case INCORRECT_PASSWORD:
                alert.setContentText("password is incorrect!");
                alert.showAndWait();
                break;
            case NOT_EXIST_USERNAME:
                alert.setContentText("username is incorrect!");
                alert.showAndWait();
                break;
            case LOGIN_SUCESS:
                new MainMenu().start(StartMenu.stage);
                break;
        }

    }

    public void resetBlank(MouseEvent mouseEvent) {
        username.setText("");
        password.setText("");
    }

    public void backToStartMenu(MouseEvent mouseEvent) throws Exception {
        new StartMenu().start(StartMenu.stage);
    }
}
