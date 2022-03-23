
package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

public class sessionformController implements Initializable{
	
	@FXML 
	private ResourceBundle resources;

	@FXML 
	private URL location;
	
    @FXML
    private ChoiceBox<String> choicebox;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		choicebox.setValue("Referendum");
		
	}
}
