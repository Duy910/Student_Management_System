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
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditStudentController extends StudentController implements Initializable {
    @FXML
    private ImageView studentImageView;

    @FXML
    private TextField firstNameEditField;

    @FXML
    private TextField lastNameEditField;

    @FXML
    private DatePicker dateofbirthEditPicker;

    @FXML
    private TextField sexEditField;

    @FXML
    private TextField emailEditField;

    @FXML
    private TextField addressEditField;

    @FXML
    private TextField phoneEditField;

    @FXML
    private TextField classIdEditField;

    @FXML
    private Button updateButton;

    String query = null;
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = null;
    Resultset resultset = null;
    PreparedStatement preparedStatement = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File fileStudent = new File("Images/student.png");
        Image imageStudent = new Image(fileStudent.toURI().toString());
        studentImageView.setImage(imageStudent);
    }

    void setTextFieldStudent(String lastName, String firstName, LocalDate toLocalDate, String sex, String email, String address, String phone) {
        lastNameEditField.setText(lastName);
        firstNameEditField.setText(firstName);
        dateofbirthEditPicker.setValue(toLocalDate);
        sexEditField.setText(sex);
        emailEditField.setText(email);
        addressEditField.setText(address);
        phoneEditField.setText(phone);
    }

    public void tableAllFillAndUpdate(ActionEvent event) {
        connection = databaseConnection.getConnection();

        String firstName = firstNameEditField.getText();
        String lastName = lastNameEditField.getText();
        String dob = String.valueOf(dateofbirthEditPicker.getValue());
        String sex = sexEditField.getText();
        String email = emailEditField.getText();
        String address = addressEditField.getText();
        String phone = phoneEditField.getText();
        String classId = classIdEditField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || dob.isEmpty()
                || sex.isEmpty() || email.isEmpty() || address.isEmpty()
                || phone.isEmpty() || classId.isEmpty()) {
            Alert alertStudent = new Alert(Alert.AlertType.ERROR);
            alertStudent.setHeaderText("Save Student!!!");
            alertStudent.setContentText("Please fill all data!");
            alertStudent.showAndWait();
        } else {
            updateStudent();
        }
    }

    public void updateStudent() {
        try {

            connection = databaseConnection.getConnection();
            String lastname = lastNameEditField.getText();
            String firstname = firstNameEditField.getText();
            String dob = String.valueOf(dateofbirthEditPicker.getValue());
            String sex = sexEditField.getText();
            String email = emailEditField.getText();
            String address = addressEditField.getText();
            String phone = phoneEditField.getText();
            String classId = classIdEditField.getText();
            String query = "UPDATE student_db.student SET "
                    + "lastname = '" + lastname
                    + "', firstname = '" + firstname
                    + "', date_of_birth = '" + dob
                    + "', sex = '" + sex
                    + "', email = '" + email
                    + "', address = '" + address
                    + "', phonenumber = '" + phone
                    + "', class_id = '" + classId
                    + "' WHERE student_id = '" + studentId + "';";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();

            Alert alertSucces = new Alert(Alert.AlertType.CONFIRMATION);
            alertSucces.setHeaderText("Save Information Student");
            alertSucces.setContentText("Done!");

            ButtonType buttonTypeOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alertSucces.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
            Optional<ButtonType> resultAlert = alertSucces.showAndWait();

            Stage stageStudent = (Stage) updateButton.getScene().getWindow();
            if (resultAlert.get() == buttonTypeOK) {
                stageStudent.close();
            } else {
                stageStudent.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cleanStudent(ActionEvent event){
        firstNameEditField.setText(null);
        lastNameEditField.setText(null);
        dateofbirthEditPicker.setValue(null);
        sexEditField.setText(null);
        emailEditField.setText(null);
        addressEditField.setText(null);
        phoneEditField.setText(null);
        classIdEditField.setText(null);
    }
}
