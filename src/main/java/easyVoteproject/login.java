package easyVoteproject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
 
 
public class login extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,424,242);
            Pane addPane = (Pane)FXMLLoader.load(getClass().getResource("login.fxml"));
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