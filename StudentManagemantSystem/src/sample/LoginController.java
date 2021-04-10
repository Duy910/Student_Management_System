package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private ImageView brandingImageView;

    @FXML
    private ImageView lockImageView;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("Images/branded.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File("Images/lock_login.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);

    }

    public void loginButtonOnAction(ActionEvent event) {
        if (usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
            validateLogin();
        } else  {
            loginMessageLabel.setText("Please enter username and password!");
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin() {
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectionDB = connectionNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM student_db.user_account where username = '" + usernameTextField.getText()
                + "' and password = '" + passwordPasswordField.getText() + "';";

        try {
            Statement statement = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()) {
                    if (queryResult.getInt(1) == 1) {
                        createHomeManagementForm();
                    } else {
                        loginMessageLabel.setText("Invalid login. Please try again.");
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
            e.getCause();
        }
    }

    public void createHomeManagementForm() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
            Stage stageHomeManagement = new Stage();
            stageHomeManagement.initStyle(StageStyle.UNDECORATED);
            stageHomeManagement.setScene(new Scene( root,1024, 615));
            stageHomeManagement.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
