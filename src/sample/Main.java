package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {
    private final String PATH_SAMPLE_XML = "view/sample.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH_SAMPLE_XML));
        Parent root = loader.load();
        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
