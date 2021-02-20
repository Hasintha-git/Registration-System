package controller;

import animatefx.animation.Shake;
import bo.BoFactory;
import bo.BoType;
import bo.custome.StudentBo;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDto;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDate;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class StudentController implements Initializable {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXComboBox cmbGender;
    public JFXTextField txtSearch;
    public Label lblDate;
    ObservableList<String>list= FXCollections.observableArrayList("Male","Female");
    StudentBo studentBo= BoFactory.getInstance().getBo(BoType.STUDENT);

    public void viewOnAction(ActionEvent actionEvent) {

    }

    public void updateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String date = lblDate.getText();
        String gender = String.valueOf(cmbGender.getValue());

        StudentDto studentDto = new StudentDto(id, name, address, contact, date, gender);
        try {
            boolean updated = studentBo.updateStudent(studentDto);
            if(updated){
                NotificationAlert.yesAlert();
            }else{
                NotificationAlert.warning();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            boolean deleted = studentBo.deleteStudent(id);
            if(deleted){
                NotificationAlert.yesAlert();
            }else{
                NotificationAlert.warning();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String date = lblDate.getText();
        String gender = String.valueOf(cmbGender.getValue());

        StudentDto studentDto = new StudentDto(id, name, address, contact, date, gender);
        try {
            boolean added = studentBo.addStudent(studentDto);
            if(added){
                NotificationAlert.yesAlert();
            }else{
                NotificationAlert.warning();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void searchOnAction(MouseEvent mouseEvent) {
        String searchId = txtSearch.getText();

        try {
            Student student = studentBo.findStudent(searchId);
            if(student!=null){
                txtId.setText(student.getId());
                txtName.setText(student.getName());
                txtAddress.setText(student.getAddress());
                txtContact.setText(student.getContact());
                cmbGender.setValue(student.getGender());
                lblDate.setText(student.getGender());
            }else{
                NotificationAlert.warning();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setGender(){
        cmbGender.setItems(list);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setGender();
        String date = LocalDate.now().toString();
        lblDate.setText(date);
        getLastId();
    }
    private void getLastId() {
        try {
            String lastId = studentBo.getLastId();
            int newId = Integer.parseInt(lastId.substring(1, 4))+1;
            if (newId < 10) {
                txtId.setText("S00"+newId);
            }else if (newId < 100) {
                txtId.setText("S0"+newId);
            }else {
                txtId.setText("S"+newId);
            }
        } catch (Exception e) {
            txtId.setText("S001");
        }
    }

    public void txtId(ActionEvent actionEvent) {
        String id=txtId.getText();
        if(Pattern.compile("^(S00)[0-9]{1,}$").matcher(id).matches()) {
            txtName.requestFocus();
        }else {
            txtId.setFocusColor(Paint.valueOf("Red"));
            txtId.clear();

            new Shake(txtId).setCycleCount(1).setDelay(Duration.valueOf("100ms")).play();
        }
    }

    public void txtName(ActionEvent actionEvent) {
        String name=txtName.getText();
        if(Pattern.compile("^[a-z][0-9]{1,}|[A-Z]{1,}|[a-z]{1,}$").matcher(name).matches()) {
            txtAddress.requestFocus();
        }else {
            txtName.setFocusColor(Paint.valueOf("Red"));
            txtName.clear();

            new Shake(txtName).setCycleCount(1).setDelay(Duration.valueOf("100ms")).play();
        }
    }

    public void txtAddress(ActionEvent actionEvent) {
        String address=txtAddress.getText();
        if(Pattern.compile("^[a-z][0-9]{1,}|[A-Z]{1,}|[a-z]{1,}$").matcher(address).matches()) {
            txtContact.requestFocus();
        }else {
            txtAddress.setFocusColor(Paint.valueOf("Red"));
            txtAddress.clear();

            new Shake(txtAddress).setCycleCount(1).setDelay(Duration.valueOf("100ms")).play();
        }
    }

    public void txtContact(ActionEvent actionEvent) {
        String fee=txtContact.getText().trim();
        if(Pattern.compile("^[0-9]{1,}$").matcher(fee).matches()) {
            cmbGender.requestFocus();
        }else {
            txtContact.setFocusColor(Paint.valueOf("Red"));
            txtContact.clear();

            new Shake(txtContact).setCycleCount(1).setDelay(Duration.valueOf("100ms")).play();
        }
    }

    public void txtSearch(ActionEvent actionEvent) {
        String searchId = txtSearch.getText();

        try {
            Student student = studentBo.findStudent(searchId);
            if(student!=null){
                txtId.setText(student.getId());
                txtName.setText(student.getName());
                txtAddress.setText(student.getAddress());
                txtContact.setText(student.getContact());
                cmbGender.setValue(student.getGender());
                lblDate.setText(student.getGender());
            }else{
                NotificationAlert.warning();
                txtSearch.setFocusColor(Paint.valueOf("Red"));
                txtSearch.clear();
                new Shake(txtSearch).setCycleCount(1).setDelay(Duration.valueOf("100ms")).play();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
