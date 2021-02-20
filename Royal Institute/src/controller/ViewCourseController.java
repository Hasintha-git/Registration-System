package controller;

import bo.BoFactory;
import bo.BoType;
import bo.custome.CourseBo;
import dto.CourseDto;
import dto.StudentDto;
import entity.Course;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class ViewCourseController implements Initializable {
    public TableView tblCourse;
    public TableColumn clmCode;
    public TableColumn clmName;
    public TableColumn clmDuration;
    public TableColumn clmFee;
    CourseBo courseBo;

    public ViewCourseController(){
        courseBo = BoFactory.getInstance().getBo(BoType.COURSE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCourses();
        clmCode.setCellValueFactory(new PropertyValueFactory("code"));
        clmName.setCellValueFactory(new PropertyValueFactory("courseName"));
        clmDuration.setCellValueFactory(new PropertyValueFactory("duration"));
        clmFee.setCellValueFactory(new PropertyValueFactory("fee"));
    }
    private void loadCourses() {
        ObservableList observableList = FXCollections.observableArrayList();

        try {
            List<Course> all = courseBo.findAll();
            Iterator<Course> iterator = all.iterator();
            while (iterator.hasNext()){
                Course next = iterator.next();
                observableList.add(new CourseDto(next.getCode(),next.getCourseName(),next.getFee(),next.getDuration()));

            }
            tblCourse.setItems(observableList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void DeleteRow(ActionEvent actionEvent) {
        CourseDto e = (CourseDto) tblCourse.getItems().get(tblCourse.getSelectionModel().getSelectedIndex());
        String code = e.getCode();
        try {
            boolean deleted = courseBo.deleteCourse(code);
            if(deleted){
                NotificationAlert.yesAlert();
                loadCourses();
            }else{
                NotificationAlert.warning();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void CourseClick(MouseEvent mouseEvent) {


        tblCourse.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.UP ||event.getCode()==KeyCode.DOWN){
                CourseDto c = (CourseDto) tblCourse.getItems().get(tblCourse.getSelectionModel().getSelectedIndex());

            }
        });
    }
}
