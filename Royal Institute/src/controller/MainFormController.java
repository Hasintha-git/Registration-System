package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    public AnchorPane mainRoot;
    public Pane root;

    public void SelectStudent(ActionEvent actionEvent) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/StudentForm.fxml")));
    }

    public void SelectRegistration(ActionEvent actionEvent) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/RegistrationForm.fxml")));
    }

    public void SelectCourse(ActionEvent actionEvent) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/CourseForm.fxml")));
    }

    public void viewCourse(ActionEvent actionEvent) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/ViewCourse.fxml")));

    }

    public void viewStudent(ActionEvent actionEvent) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/viewStudent.fxml")));

    }

    public void viewRegistration(ActionEvent actionEvent) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/viewRegistration.fxml")));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.getChildren().clear();
        try {
            root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/viewRegistration.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
