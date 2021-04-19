package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AddStudentController implements Initializable {

    @FXML
    private ImageView studentImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File fileStudent = new File("Images/student.png");
        Image imageStudent = new Image(fileStudent.toURI().toString());
        studentImageView.setImage(imageStudent);
    }
}
