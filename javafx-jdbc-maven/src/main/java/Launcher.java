/*
    @author DanujaV
    @created 3/8/23 - 4:50 PM   
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL resource = Launcher.class.getResource("view/customer_mange_form.fxml");
        Parent load = FXMLLoader.load(resource);

        stage.setScene(new Scene(load));
        stage.setTitle("Customer Manage");
        stage.centerOnScreen();

        stage.show();
    }
}
