package lk.ijse.thogakade;

/*
    @author DanujaV
    @created 3/13/23 - 9:35 AM   
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
        stage.setScene(new Scene(root));

        stage.show();
    }
}
