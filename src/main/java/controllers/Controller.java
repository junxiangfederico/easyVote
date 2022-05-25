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
import models.utenti.UtentiHolder;

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
	protected void SendId(int i) {
	IdHolder holder = IdHolder.getInstance();
	holder.setId(i);
	}
	protected int receiveId() {
    	IdHolder holder = IdHolder.getInstance();
		return holder.getid();
    }
	protected void SendUtente(int i) {
		UtentiHolder holder = UtentiHolder.getInstance();
		holder.setId(i);
		}
	protected int receiveUtente() {
    	UtentiHolder holder = UtentiHolder.getInstance();
		return holder.getid();
    }
	
	

}