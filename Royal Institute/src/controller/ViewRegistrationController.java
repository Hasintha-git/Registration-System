package controller;

import bo.BoFactory;
import bo.BoType;
import bo.custome.RegistrationBo;
import dto.CourseDto;
import dto.RegistrationDto;
import dto.RegistrationDto2;
import dto.StudentDto;
import entity.Registration;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import view.tm.RegistrationTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class ViewRegistrationController implements Initializable {

    public TableColumn clmNo;
    public TableColumn clmDate;
    public TableColumn clmFee;
    public TableColumn clmSId;
    public TableColumn clmCourseCode;
    public TableView<RegistrationTM> tblRegistration;
    public Label lblTotStudent;
    public Label lblTotIncome;
    public PieChart pieChart;

    RegistrationBo registrationBo= BoFactory.getInstance().getBo(BoType.REGISTRATION);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadRegistration();
        clmNo.setCellValueFactory(new PropertyValueFactory("regNo"));
        clmDate.setCellValueFactory(new PropertyValueFactory("regDate"));
        clmFee.setCellValueFactory(new PropertyValueFactory("regFee"));
        clmSId.setCellValueFactory(new PropertyValueFactory("student"));
        clmCourseCode.setCellValueFactory(new PropertyValueFactory("course"));
        loadAllIncome();

    }

//    private void loadPieChart() {
//        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
//                new PieChart.Data("Cars", 10),
//                new PieChart.Data("van", 20),
//                new PieChart.Data("biikes", 20));
//        pieChart.setData(data);
//    }

    private void loadRegistration() {
        ObservableList<RegistrationTM> observableList = FXCollections.observableArrayList();

        try {
            List<Registration> all = registrationBo.findAll();
            Iterator<Registration> iterator = all.iterator();
            while (iterator.hasNext()){
                Registration next = iterator.next();
                observableList.add(new RegistrationTM(next.getRegNo(),next.getRegDate(),next.getRegFee(),next.getStudent().getId(),next.getCourse().getCode()));

            }

            tblRegistration.setItems(observableList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadAllIncome(){
        double tot=0;
        int totItem=0;
        int count=0;
        int all=100;
        int available=0;

        for (int i=0;i<tblRegistration.getItems().size();i++){
            RegistrationTM tm= (RegistrationTM) tblRegistration.getItems().get(i);
            tot+=tm.getRegFee();

            String regNo = tm.getRegNo();
            if (regNo !=null) {
                count++;
            }
        }
        available=all-count;
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                new PieChart.Data("Student"+" \n"+count, count),
                new PieChart.Data("Available"+"\n "+available, available));
        pieChart.setData(data);
        lblTotIncome.setText(String.valueOf(tot));
        lblTotStudent.setText(String.valueOf(count));
    }
}
