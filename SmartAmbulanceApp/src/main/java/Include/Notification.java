/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Include;

import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author rachid dev
 */
public class Notification {
    public String MessageSucces;
    String SubMsg;
    public String MessageFiled;
    public void SuccessNotification(String MessageSucces , String subMsg){
         String title = MessageSucces;
            String message = SubMsg;
            NotificationType notifyType = NotificationType.SUCCESS;
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notifyType);
            tray.setAnimationType(AnimationType.FADE);
            tray.showAndDismiss(new Duration(3));
        
        
    }
           
    public void Failednotification(String MessageFiled ,String subMsg){
       String title = MessageFiled;
            String message = SubMsg;
            NotificationType notifyType = NotificationType.WARNING;
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notifyType);
            tray.setAnimationType(AnimationType.FADE);
            tray.showAndDismiss(new Duration(3));
        
    }
}
