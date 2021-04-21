package sample;

import com.mysql.cj.protocol.Resultset;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddStudentController implements Initializable {

    @FXML
    private ImageView studentImageView;

    @FXML
    public TextField firstNameField;

    @FXML
    public TextField lastNameField;

    @FXML
    public DatePicker dateofbirthPicker;

    @FXML
    public TextField sexField;

    @FXML
    public TextField emailField;

    @FXML
    public TextField addressField;

    @FXML
    public TextField phoneField;

    @FXML
    public TextField classIdField;

    @FXML
    private Button addButton;

    String query = null;
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = null;
    Resultset resultset = null;
    PreparedStatement preparedStatement = null;
    private boolean update;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File fileStudent = new File("Images/student.png");
        Image imageStudent = new Image(fileStudent.toURI().toString());
        studentImageView.setImage(imageStudent);
    }

    public void saveStudent(ActionEvent event) {
        connection = databaseConnection.getConnection();

        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String dob = String.valueOf(dateofbirthPicker.getValue());
        String sex = sexField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        String classId = classIdField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || dob.isEmpty()
                || sex.isEmpty() || email.isEmpty() || address.isEmpty()
                || phone.isEmpty() || classId.isEmpty()) {
            Alert alertStudent = new Alert(Alert.AlertType.ERROR);
            alertStudent.setHeaderText("Save Student!!!");
            alertStudent.setContentText("Please fill all data!");
            alertStudent.showAndWait();
        } else {
            getQuery();
            insert();
        }
    }

    public void cleanStudent(ActionEvent event){
        firstNameField.setText(null);
        lastNameField.setText(null);
        dateofbirthPicker.setValue(null);
        sexField.setText(null);
        emailField.setText(null);
        addressField.setText(null);
        phoneField.setText(null);
        classIdField.setText(null);
    }

    public void insert() {
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, lastNameField.getText());
            preparedStatement.setString(2, firstNameField.getText());
            preparedStatement.setString(3, String.valueOf(dateofbirthPicker.getValue()));
            preparedStatement.setString(4, sexField.getText());
            preparedStatement.setString(5, emailField.getText());
            preparedStatement.setString(6, addressField.getText());
            preparedStatement.setString(7, phoneField.getText());
            preparedStatement.setString(8, classIdField.getText());
            preparedStatement.execute();


            Alert alertSucces = new Alert(Alert.AlertType.CONFIRMATION);
            alertSucces.setHeaderText("Save Information Student");
            alertSucces.setContentText("Done!");

            ButtonType buttonTypeOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alertSucces.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
            Optional<ButtonType> resultAlert = alertSucces.showAndWait();

            Stage stageStudent = (Stage) addButton.getScene().getWindow();
            if (resultAlert.get() == buttonTypeOK) {
                stageStudent.close();
            } else {
                stageStudent.show();
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            exception.getMessage();
        }

    }

    private void getQuery() {
        query = "INSERT INTO student_db.student (lastname, firstname, date_of_birth, sex, email, address, phonenumber, class_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    }

}
