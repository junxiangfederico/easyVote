package controllers;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class pagecontroller extends Controller{
    @FXML
    private Button login;

    @FXML
    private Button registration;

    @FXML
    void handlelogin(ActionEvent event)throws IOException {
    	changeView("views/login.fxml",event);
    	
    }

    @FXML
    void handleregistration(ActionEvent event)throws IOException  {
    	changeView("views/registrationform.fxml",event);
    }
    
}
