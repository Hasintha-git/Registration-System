package controller;

import bo.BoFactory;
import bo.BoType;
import bo.custome.CourseBo;
import bo.custome.StudentBo;
import dto.StudentDto;
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

public class ViewStudentController implements Initializable {
    public TableView tblStudent;
    public TableColumn tblId;
    public TableColumn tblGender;
    public TableColumn tblDate;
    public TableColumn tblContact;
    public TableColumn tblAddress;
    public TableColumn tblName;
    StudentBo studentBo= BoFactory.getInstance().getBo(BoType.STUDENT);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadStudent();
        tblId.setCellValueFactory(new PropertyValueFactory("Id"));
        tblName.setCellValueFactory(new PropertyValueFactory("Name"));
        tblAddress.setCellValueFactory(new PropertyValueFactory("Address"));
        tblContact.setCellValueFactory(new PropertyValueFactory("Contact"));
        tblDate.setCellValueFactory(new PropertyValueFactory("Date"));
        tblGender.setCellValueFactory(new PropertyValueFactory("Gender"));
    }

    private void loadStudent() {
        ObservableList observableList = FXCollections.observableArrayList();

        try {
            List<Student> all = studentBo.findAll();
            Iterator<Student> iterator = all.iterator();
            while (iterator.hasNext()){
                Student next = iterator.next();
                observableList.add(new StudentDto(next.getId(),next.getName(),next.getContact(),next.getAddress(),next.getDate(),next.getGender()));

            }
            tblStudent.setItems(observableList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRow(ActionEvent actionEvent) {
        StudentDto e = (StudentDto) tblStudent.getItems().get(tblStudent.getSelectionModel().getSelectedIndex());
        String id = e.getId();

        try {
            boolean deleted = studentBo.deleteStudent(id);
            if(deleted){
                NotificationAlert.yesAlert();
                loadStudent();
            }else{
                NotificationAlert.warning();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        tblStudent.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.UP ||event.getCode()==KeyCode.DOWN){
                StudentDto c = (StudentDto) tblStudent.getItems().get(tblStudent.getSelectionModel().getSelectedIndex());


            }
        });
    }

    public void StudentClick(MouseEvent mouseEvent) {

    }
}
