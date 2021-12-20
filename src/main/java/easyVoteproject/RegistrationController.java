package easyVoteproject;

import java.util.Set;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;

public class RegistrationController {
	
    @FXML
    private Button enterButton;

    @FXML
    private TextField fieldCF;

    @FXML
    private TextField fieldCognome;

    @FXML
    private TextField fieldComune;

    @FXML
    private DatePicker fieldData;

    @FXML
    private TextField fieldNazione;

    @FXML
    private TextField fieldNome;

    @FXML
    private Label lblOutput;

    @FXML
    private ChoiceBox<String> sex;
    
    
    @FXML
    void handleOk(ActionEvent event) {
    	lblOutput.setVisible(true);
    	String nome = fieldNome.getText();
    	lblOutput.setText(nome);
    }
    @FXML
    void handlesex(ActionEvent event) {

    }
    
    void initialize() {	
    	sex.getItems().addAll("M","F");
    	assert fieldNome != null : "fx:id=\"username\" was not injected: check your FXML file 'login.fxml'.";
    	assert fieldCognome != null : "fx:id=\"password\" was not injected: check your FXML file 'login.fxml'.";
    	assert fieldCF != null : "fx:id=\"username\" was not injected: check your FXML file 'login.fxml'.";
    	assert fieldComune != null : "fx:id=\"username\" was not injected: check your FXML file 'login.fxml'.";
    	assert fieldData != null : "fx:id=\"username\" was not injected: check your FXML file 'login.fxml'.";
    	lblOutput.setVisible(false);
       	
 


    }

}




