package controller;

import bo.BoFactory;
import bo.BoType;
import bo.custome.CourseBo;
import bo.custome.RegistrationBo;
import bo.custome.StudentBo;
import com.jfoenix.controls.JFXTextField;
import dto.CourseDto;
import dto.RegistrationDto;
import dto.StudentDto;
import entity.Course;
import entity.Registration;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {
    public Label lblDate;
    public Label lblTime;
    public Label lblRNo;
    public TableView tblStudent;
    public TableColumn tblId;
    public TableColumn tblName;
    public Label lblId;
    public TableView tblCourse;
    public TableColumn tblCode;
    public TableColumn tblFee;
    public TableColumn tblCourseName;
    public TableColumn tblDura;
    public Label lblFee;
    public Label lblCode;
    public Label lblPay;
    public JFXTextField txtCash;
    public Label lblBal;
    public AnchorPane root1;

    CourseBo courseBo;
    StudentBo studentBo;
    RegistrationBo registrationBo;

    public RegistrationController(){
        courseBo= BoFactory.getInstance().getBo(BoType.COURSE);
        studentBo=BoFactory.getInstance().getBo(BoType.STUDENT);
        registrationBo=BoFactory.getInstance().getBo(BoType.REGISTRATION);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadStudent();
        tblId.setCellValueFactory(new PropertyValueFactory("Id"));
        tblName.setCellValueFactory(new PropertyValueFactory("Name"));
        loadCourse();
        tblCode.setCellValueFactory(new PropertyValueFactory("code"));
        tblCourseName.setCellValueFactory(new PropertyValueFactory("courseName"));
        tblDura.setCellValueFactory(new PropertyValueFactory("duration"));
        tblFee.setCellValueFactory(new PropertyValueFactory("fee"));

        String date = LocalDate.now().toString();
        lblDate.setText(date);
        String Time= LocalTime.now().toString();
        lblTime.setText(Time);
        getLastId();
    }

    private void getLastId() {
        try {
            String lastId = registrationBo.getLastId();
            int newId = Integer.parseInt(lastId.substring(1, 4))+1;
            if (newId < 10) {
                lblRNo.setText("R00"+newId);
            }else if (newId < 100) {
                lblRNo.setText("R0"+newId);
            }else {
                lblRNo.setText("R"+newId);
            }
        } catch (Exception e) {
            lblRNo.setText("R001");
        }
    }

    public void btnAddCourse(ActionEvent actionEvent) throws IOException {
        root1.getChildren().clear();
        root1.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/CourseForm.fxml")));

    }

    public void btnAddStudent(ActionEvent actionEvent) throws IOException {
        root1.getChildren().clear();
        root1.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/StudentForm.fxml")));
    }

    public void btnPrintBill(ActionEvent actionEvent) {
    }

    public void btnPurchaseOrder(ActionEvent actionEvent) {
        String id = lblId.getText();
        String code = lblCode.getText();
        double fee = Double.parseDouble(lblFee.getText());
        String regNo = lblRNo.getText();
        String date = lblDate.getText();
        Student student = null;
        Course course = null;
        try {
             course = courseBo.findCourse(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
             student = studentBo.findStudent(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RegistrationDto registrationDto = new RegistrationDto(regNo, date, fee, student, course);
        try {
            boolean added = registrationBo.addRegistration(registrationDto);
            if(added){
                NotificationAlert.yesAlert();
            }else {
                NotificationAlert.warning();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CashOnAtion(ActionEvent actionEvent) {
        double cash = Double.parseDouble(txtCash.getText());
        double payPrice = Double.parseDouble(lblPay.getText());
        double balance;
        if(cash>=payPrice){
            balance=cash-payPrice;
            lblBal.setText(String.valueOf(balance));
        }
    }


    public void CourseClick(MouseEvent mouseEvent) {
        CourseDto e = (CourseDto) tblCourse.getItems().get(tblCourse.getSelectionModel().getSelectedIndex());
        lblCode.setText(e.getCode());
        lblFee.setText(String.valueOf(e.getFee()));
        lblPay.setText(String.valueOf(e.getFee()));


        tblCourse.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.UP ||event.getCode()==KeyCode.DOWN){
                CourseDto c = (CourseDto) tblCourse.getItems().get(tblCourse.getSelectionModel().getSelectedIndex());
                lblCode.setText(e.getCode());
                lblFee.setText(String.valueOf(e.getFee()));
                lblPay.setText(String.valueOf(e.getFee()));
            }
        });
    }

    public void StudentClick(MouseEvent mouseEvent) {
        StudentDto e = (StudentDto) tblStudent.getItems().get(tblStudent.getSelectionModel().getSelectedIndex());
        lblId.setText(e.getId());


        tblStudent.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.UP ||event.getCode()==KeyCode.DOWN){
                StudentDto c = (StudentDto) tblStudent.getItems().get(tblStudent.getSelectionModel().getSelectedIndex());
                lblId.setText(e.getId());

            }
        });
    }

    public void loadStudent(){
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
    public void loadCourse(){
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


}
