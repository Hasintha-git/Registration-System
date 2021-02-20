package controller;

import animatefx.animation.Shake;
import bo.BoFactory;
import bo.BoType;
import bo.custome.LogInBo;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dto.LogInDto;
import entity.LogIn;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.regex.Pattern;

public class LogInFormController {
    public AnchorPane mainRoot;
    public JFXTextField txtMail;
    public JFXPasswordField txtPass;
    public Label lblForget;
    public JFXTextField upTxtmail;
    public JFXPasswordField uptxtPass;
    public JFXPasswordField upTxtPass2;
    public VBox root;
    public Label showPass;
    LogInBo logInBo;

    public LogInFormController(){
        logInBo= BoFactory.getInstance().getBo(BoType.LOGIN);
    }

    public void haveAccount(MouseEvent mouseEvent) {
        TranslateTransition translate=new TranslateTransition(Duration.seconds(0.5),root);
        translate.setToX(root.getLayoutX());
        translate.play();
    }

    public void createAccount(MouseEvent mouseEvent) {
        TranslateTransition translate=new TranslateTransition(Duration.seconds(0.5),root);
        translate.setToX(root.getLayoutX() + (mainRoot.getPrefWidth()-root.getPrefWidth()));
        translate.play();
    }

    public void signUp(ActionEvent actionEvent) {
        String mail = upTxtmail.getText();
        String pass1 = uptxtPass.getText();
        String pass2 = upTxtPass2.getText();

        if (uptxtPass.getText().equals(upTxtPass2.getText())) {
            LogInDto logInDto = new LogInDto(mail, pass1);
            try {
                boolean added = logInBo.addUser(logInDto);
                if(added){
                    NotificationAlert.yesAlert();
                }else{
                    NotificationAlert.noAlert();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            NotificationAlert.warning();
        }
    }


    public void signIn(ActionEvent actionEvent) {
        String mail = txtMail.getText();
        String pass = txtPass.getText();

        try {
            LogIn user = logInBo.findUser(mail);

            if(user.getPassword().equals(txtPass.getText())){
                NotificationAlert.yesAlert();
                Stage window = (Stage) this.root.getScene().getWindow();
                try {
                    window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                window.centerOnScreen();
            }else{
                NotificationAlert.warning();
                txtPass.clear();
                txtPass.setFocusColor(Paint.valueOf("Red"));
                new Shake(txtPass).setCycleCount(1).setDelay(Duration.valueOf("100ms")).play();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eyeClick(MouseEvent mouseEvent) {
       showPass.setText(txtPass.getText());
       txtPass.setVisible(true);
    }

    public void passwordOnAction(ActionEvent actionEvent) {
        signIn(actionEvent);
    }

    public void gmailOnAction(ActionEvent actionEvent) {
        String mail = txtMail.getText();
        if(Pattern.compile("^[a-z]{1,}(@)(gmail).(com)|[a-z](@)(gmail).(com)|[a-z]{5,}[0-9]{1,}(@)(gmail).(com)$").matcher(mail).matches()) {
            txtPass.requestFocus();
        }else {
            txtMail.setFocusColor(Paint.valueOf("Red"));
            txtMail.clear();

            new Shake(txtMail).setCycleCount(1).setDelay(Duration.valueOf("100ms")).play();
        }

    }
}
