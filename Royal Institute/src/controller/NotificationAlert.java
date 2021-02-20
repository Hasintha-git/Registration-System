package controller;

import javafx.fxml.Initializable;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.net.URL;
import java.util.ResourceBundle;

public  class NotificationAlert implements Initializable {
    public static void yesAlert() {
        String title="Success !";
        String text="Successfully Complete";
        TrayNotification tray=new TrayNotification();
        AnimationType type=AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(text);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));


    }
    public static void noAlert() {
        String title="Failed !";
        String text="Something is Erro !";
        TrayNotification tray=new TrayNotification();
        AnimationType type=AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(text);
        tray.setNotificationType(NotificationType.ERROR);
        tray.showAndDismiss(Duration.millis(3000));


    }
    public static void warning() {
        String title="Warning !";
        String text="!!!";
        TrayNotification tray=new TrayNotification();
        AnimationType type=AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(text);
        tray.setNotificationType(NotificationType.WARNING);
        tray.showAndDismiss(Duration.millis(3000));


    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
