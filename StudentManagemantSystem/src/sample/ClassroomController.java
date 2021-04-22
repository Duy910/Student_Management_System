package sample;

import com.mysql.cj.protocol.Resultset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ClassroomController implements Initializable {

    @FXML
    private ImageView classroomlogoImageView;

    @FXML
    private ImageView backImageView;

    @FXML
    private Button generateButton;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> courseNameCombobox;

    @FXML
    private TableView<Student> classroomTableView;

    @FXML
    private TableColumn<Student, String> idTableColumn;

    @FXML
    private TableColumn<Student, String> lastnameTableColumn;

    @FXML
    private TableColumn<Student, String> firstnameTableColumn;

    @FXML
    private TableColumn<Student, String> dobTableColumn;

    @FXML
    private TableColumn<Student, String> sexTableColumn;

    @FXML
    private TableColumn<Student, String> courseTableColumn;

    @FXML
    private TableColumn<Student, String> examTypeTableColumn;

    @FXML
    private TableColumn<Student, String> scoreTableColumn;

    @FXML
    private TableColumn<Student, String> teacherTableColumn;


    Connection connection = null;
    DatabaseConnection databaseConnection = new DatabaseConnection();
    String query = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultset = null;
    Student student = null;

    final ObservableList options = FXCollections.observableArrayList();
    ObservableList<Student> StudentList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File fileClassroom = new File("Images/classes.png");
        Image imageClassroom = new Image(fileClassroom.toURI().toString());
        classroomlogoImageView.setImage(imageClassroom);

        File fileBack = new File("Images/back_icon.png");
        Image imageBack = new Image(fileBack.toURI().toString());
        backImageView.setImage(imageBack);

        fillCombobox();
        courseNameCombobox.setItems(options);
    }

    public void loadClassroom() {
        connection = databaseConnection.getConnection();

        refreshClassroomTableView();

        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("idclass"));
        lastnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        firstnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        dobTableColumn.setCellValueFactory(new PropertyValueFactory<>("dob"));
        sexTableColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        courseTableColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        examTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("exam_type"));
        scoreTableColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        teacherTableColumn.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        classroomTableView.setItems(StudentList);
    }

    public void refreshClassroomTableView() {

        try {
            StudentList.clear();
            connection = databaseConnection.getConnection();
            query = "SELECT student_db.class.classname, student_db.student.lastname, student_db.student.firstname, " +
                    "student_db.student.date_of_birth, student_db.student.sex, student_db.course.coursename," +
                    " student_db.exam_type.name_exam, student_db.exam_result.score, student_db.teacher.firstname\n" +
                    "FROM student_db.student, student_db.course, student_db.exam_type, student_db.exam_result, student_db.teacher, student_db.class\n" +
                    "WHERE student.student_id = course.course_id = teacher.teacher_id = class.class_id;";
            preparedStatement = connection.prepareStatement(query);
            resultset = preparedStatement.executeQuery();

            while (resultset.next()) {
                StudentList.add(new Student(
                        resultset.getString("classname"),
                        resultset.getString("lastname"),
                        resultset.getString("firstname"),
                        resultset.getDate("date_of_birth"),
                        resultset.getString("sex"),
                        resultset.getString("coursename"),
                        resultset.getString("name_exam"),
                        resultset.getInt("score"),
                        resultset.getString("teacher.firstname")
                ));
                classroomTableView.setItems(StudentList);
            }

            preparedStatement.close();
            resultset.close();


        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getMessage();
        }

    }

    public void generateClassroom(ActionEvent event) {
        loadClassroom();
    }

    public void backHomeForm(ActionEvent event) {
        Stage stageBack = (Stage) backButton.getScene().getWindow();
        stageBack.toBack();
    }

    public void fillCombobox() {
        try {
            connection = databaseConnection.getConnection();
            query = "SELECT classname FROM student_db.class;";
            preparedStatement = connection.prepareStatement(query);
            resultset = preparedStatement.executeQuery();

            while (resultset.next()) {
                options.add(resultset.getString("classname"));
            }
            preparedStatement.close();
            resultset.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getMessage();
        }
    }
}
