package controllers;


import java.App;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Controller  {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void changeView(String view,ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource(view));
	    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}