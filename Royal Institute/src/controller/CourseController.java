package controller;

import animatefx.animation.Shake;
import bo.BoFactory;
import bo.BoType;
import bo.SuperBo;
import bo.custome.CourseBo;
import com.jfoenix.controls.JFXTextField;
import dto.CourseDto;
import dto.RegistrationDto;
import entity.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CourseController implements Initializable {

    public JFXTextField txtSearch;
    public Label lblDate;
    public JFXTextField txtCode;
    public JFXTextField txtCorseName;
    public JFXTextField txtFee;
    public JFXTextField txtDuration;
    public PieChart pieChart;
    CourseBo courseBo;


    public CourseController(){
        courseBo = BoFactory.getInstance().getBo(BoType.COURSE);
    }


    public void saveOnAction(ActionEvent actionEvent) {
        String code = txtCode.getText();
        String name = txtCorseName.getText();
        String duration = txtDuration.getText();
        double fee = Double.parseDouble(txtFee.getText());
        CourseDto courseDto = new CourseDto(code, name, fee,duration);
        try {
            boolean add = courseBo.addCourse(courseDto);
            if(add){
                NotificationAlert.yesAlert();
            }else{
                NotificationAlert.warning();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String code = txtCode.getText();
        try {
            boolean deleted = courseBo.deleteCourse(code);
            if(deleted){
                NotificationAlert.yesAlert();
            }else{
                NotificationAlert.noAlert();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String code = txtCode.getText();
        String name = txtCorseName.getText();
        String duration = txtDuration.getText();
        double fee = Double.parseDouble(txtFee.getText());

        CourseDto courseDto = new CourseDto(code, name,  fee,duration);
        try {
            boolean updated = courseBo.updateCourse(courseDto);
            if(updated){
                NotificationAlert.yesAlert();
            }else{
                NotificationAlert.warning();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewOnAction(ActionEvent actionEvent) {

    }

    public void searchOnAction(MouseEvent mouseEvent) {
        String searchid = txtSearch.getText();

        try {
            Course course = courseBo.findCourse(searchid);
            if(course!=null){
                txtCode.setText(course.getCode());
                txtCorseName.setText(course.getCourseName());
                txtDuration.setText(course.getDuration());
                txtFee.setText(String.valueOf(course.getFee()));
            }else{
                NotificationAlert.warning();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String date = LocalDate.now().toString();
        lblDate.setText(date);
        getLastId();

        //        loadPieChart();
    }
    private void getLastId() {
        try {
            String lastId = courseBo.getLastId();
            int newId = Integer.parseInt(lastId.substring(1, 4))+1;
            if (newId < 10) {
                txtCode.setText("C00"+newId);
            }else if (newId < 100) {
                txtCode.setText("C0"+newId);
            }else {
                txtCode.setText("C"+newId);
            }
        } catch (Exception e) {
            txtCode.setText("C001");
        }

    }

    public void txtDuration(ActionEvent actionEvent) {
        String name=txtDuration.getText().trim();
        if(Pattern.compile("^[a-z][0-9]{1,}|[0-9][a-z][A-Z]{1,}|[0-9][a-z]{1,}$").matcher(name).matches()) {
            txtFee.requestFocus();
        }else {
            txtFee.setFocusColor(Paint.valueOf("Red"));
            txtFee.clear();

            new Shake(txtFee).setCycleCount(1).setDelay(Duration.valueOf("100ms")).play();
        }
    }

    public void txtFee(ActionEvent actionEvent) {
        String fee=txtFee.getText().trim();
        if(Pattern.compile("^[0-9]{1,}|[0-9]{1,}.(00)$").matcher(fee).matches()) {
           saveOnAction(actionEvent);
        }else {
            txtFee.setFocusColor(Paint.valueOf("Red"));
            txtFee.clear();

            new Shake(txtFee).setCycleCount(1).setDelay(Duration.valueOf("100ms")).play();
        }
    }

    public void txtName(ActionEvent actionEvent) {
        String name=txtCorseName.getText();
        if(Pattern.compile("^[a-z][0-9]{1,}|[A-Z]{1,}|[a-z]{1,}$").matcher(name).matches()) {
            txtDuration.requestFocus();
        }else {
            txtCorseName.setFocusColor(Paint.valueOf("Red"));
            txtCorseName.clear();

            new Shake(txtCorseName).setCycleCount(1).setDelay(Duration.valueOf("100ms")).play();
        }
    }

    public void txtCode(ActionEvent actionEvent) {
        String id=txtCode.getText();
        if(Pattern.compile("^(C00)[0-9]{1,}$").matcher(id).matches()) {
            txtCorseName.requestFocus();
        }else {
            txtCode.setFocusColor(Paint.valueOf("Red"));
            txtCode.clear();

            new Shake(txtCode).setCycleCount(1).setDelay(Duration.valueOf("100ms")).play();
        }
    }

    public void txtSearch(ActionEvent actionEvent) {
        String searchid = txtSearch.getText();

        try {
            Course course = courseBo.findCourse(searchid);
            if(course!=null){
                txtCode.setText(course.getCode());
                txtCorseName.setText(course.getCourseName());
                txtDuration.setText(course.getDuration());
                txtFee.setText(String.valueOf(course.getFee()));
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
//
//    private void loadPieChart() {
//        String courseName;
//        double price;
//
//        for (int i=0;i<tblCourse.getItems().size();i++){
//            CourseDto tm= (CourseDto) tblCourse.getItems().get(i);
//             courseName = tm.getCourseName();
//             price=tm.getFee();
//            ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
//                    new PieChart.Data("Student"+" \n"+courseName, price));
//            pieChart.setData(data);
//        }
//
//    }


}
