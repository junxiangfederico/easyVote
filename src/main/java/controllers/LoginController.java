package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.*;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.sessione.IdHolder;
import dao.utenti.*;
import dao.factory.*;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
public class LoginController extends Controller	{
    @FXML
    private Button btnOK;
    @FXML
    private Button back;
    @FXML
    private Label lblMessage;

    @FXML
    private TextField password;

    @FXML
    private TextField username;
    private IDAOUtenti utenteDAO = DAOFactory.getFactory().getUtenteDAOInstance();

    @FXML
    void handleOK(ActionEvent event)throws NoSuchAlgorithmException, SQLException {
    	lblMessage.setVisible(true);
    	boolean result=utenteDAO.login(username.getText(), password.getText());
    	if (result==true) {
    		   lblMessage.setText("Login riuscito!");
    	}else {
    		 lblMessage.setText("Login fallito...");
    	}
    	
    }
    
    public void getinfo(String id) {
    	System.out.print(id);
    }
    
    @FXML
    void handlegoback(ActionEvent event) throws IOException {
    	changeView("views/pageform.fxml",event);
    }
    @FXML
    private int receiveData() {
    	IdHolder holder = IdHolder.getInstance();
		return holder.getid();
    }
	void initialize() {
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'login.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'login.fxml'.";
        receiveData();
		System.out.println("hello:  "+receiveData());
        lblMessage.setVisible(false);
    }
}


