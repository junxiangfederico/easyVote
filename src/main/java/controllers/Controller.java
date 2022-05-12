package controllers;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.sessione.*;

public abstract class Controller  {

	private Stage stage;
	private Scene scene;
	private Parent root;
	/***
	 * Cambia la schermata corrente.
	 * @param view La vista a cui si vuole passare.
	 * @param parameter Parametri da passare alla schermata {@code view}. 
	 */
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
	public void SendId(String view,ActionEvent event,int i) {
		try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource(view));
	    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	IdHolder holder = IdHolder.getInstance();
	        holder.setId(i);
	    	scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}