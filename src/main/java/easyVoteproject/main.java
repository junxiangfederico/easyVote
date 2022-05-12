package easyVoteproject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
 
 
public class main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            //Pane addPane = (Pane)FXMLLoader.load(getClass().getClassLoader().getResource("views/sessionformProperties.fxml"));
            Pane addPane = (Pane)FXMLLoader.load(getClass().getClassLoader().getResource("views/sessionform.fxml"));
            Scene myScene = new Scene(addPane);
            primaryStage.setScene(myScene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
     
    public static void main(String[] args) {
        launch(args);
    }
}