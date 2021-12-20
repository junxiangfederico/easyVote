package easyVoteproject;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class pagecontroller {
	private Stage stage;
	private Scene scene;
	private Parent root;
    @FXML
    private Button login;

    @FXML
    private Button registration;

    @FXML
    void handlelogin(ActionEvent event)throws IOException {
    	 root = FXMLLoader.load(getClass().getClassLoader().getResource("easyVoteproject/resources/login.fxml"));
    	 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	 scene = new Scene(root);
    	 stage.setScene(scene);
    	 stage.show();
    }

    @FXML
    void handleregistration(ActionEvent event)throws IOException  {
    	root = FXMLLoader.load(getClass().getClassLoader().getResource("easyVoteproject/resources/registrationform.fxml"));
   	 	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   	 	scene = new Scene(root);
   	 	stage.setScene(scene);
   	 	stage.show();
    }

}
