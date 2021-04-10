package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private ImageView homeImageView;

    @FXML
    private ImageView bookImageView;

    @FXML
    private ImageView studentImageView;

    @FXML
    private ImageView classesImageView;

    @FXML
    private ImageView updateImageView;

    @FXML
    private ImageView settingImageView;

    @FXML
    private Button closeButton;

    @FXML
    private ImageView closeImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File bookFile = new File("Images/book_logo.png");
        Image bookImage = new Image(bookFile.toURI().toString());
        bookImageView.setImage(bookImage);

        File homeFile = new File("Images/home.png");
        Image homeImage = new Image(homeFile.toURI().toString());
        homeImageView.setImage(homeImage);

        File studentFile = new File("Images/student.png");
        Image studentImage = new Image(studentFile.toURI().toString());
        studentImageView.setImage(studentImage);

        File classesFile = new File("Images/classes.png");
        Image classesImage = new Image(classesFile.toURI().toString());
        classesImageView.setImage(classesImage);

        File updateFile = new File("Images/update.png");
        Image updateImage = new Image(updateFile.toURI().toString());
        updateImageView.setImage(updateImage);

        File settingFile = new File("Images/settings.png");
        Image settingImage = new Image(settingFile.toURI().toString());
        settingImageView.setImage(settingImage);

        File closeFile = new File("Images/close.png");
        Image closeImage = new Image(closeFile.toURI().toString());
        closeImageView.setImage(closeImage);

    }


    public void closeHomeOnAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
}
