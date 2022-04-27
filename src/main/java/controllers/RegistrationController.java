package controllers;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import dao.factory.DAOFactory;
import dao.utenti.*;
import easyVoteproject.Data;
import models.utenti.*;
public class RegistrationController extends Controller {
	@FXML
    private Button back;
    @FXML
    private Button enterButton;

    @FXML
    private TextField fieldCF;

    @FXML
    private TextField fieldCognome;

    @FXML
    private DatePicker fieldData;

    @FXML
    private TextField fieldNazione;

    @FXML
    private TextField fieldNome;

    @FXML
    private TextField fieldPassword;

    @FXML
    private TextField fieldUsername;

    @FXML
    private Label lblOutput;
    private IDAOUtenti utenteDAO = DAOFactory.getFactory().getUtenteDAOInstance();
    @FXML
    void goback(ActionEvent event) throws IOException{
    	changeView("views/pageform.fxml",event);
    }
    @FXML
    void handleOk(ActionEvent event) throws NoSuchAlgorithmException, SQLException {
		       
		       if (utenteDAO.verifyPresence(fieldUsername.getText())) {
				   lblOutput.setText("Utente con username " + fieldUsername.getText() + " gia presente, provare con un altro username");	
				   lblOutput.setVisible(true);
		    	   return;
		       }
		       if (fieldCF.getText().length() != 16) {
		    		 lblOutput.setText("Codice fiscale inserito non valido, lunghezza diversa da 16");	
		    		 lblOutput.setVisible(true);
		    		 throw new IllegalArgumentException("Codice fiscale inserito non valido, lunghezza diversa da 16");
		    	 }
		       if (fieldNome.getText().length() < 1 || fieldCognome.getText().length() < 1 ||fieldNazione.getText().length() < 1 || fieldUsername.getText().length() < 1 ||
		      		 fieldPassword.getText().length() < 1) {
		      		 lblOutput.setText("I dati inseriti non sono validi, riprovare");	
		      		 lblOutput.setVisible(true);
		      		 throw new IllegalArgumentException("I dati inseriti non sono validi, riprovare");
		      	 }
		       Utente utente=new Elettore(fieldNome.getText(),fieldCognome.getText(),new Data(Data.processDate(fieldData)),fieldNazione.getText(),fieldCF.getText().toUpperCase());
		       boolean esito= utenteDAO.registraElettore(utente, fieldUsername.getText(), fieldPassword.getText());
		       if (esito==true) {
		    	   lblOutput.setText("Utente " + fieldUsername.getText() + " registrato con successo");	
		       }else {
		    	   lblOutput.setText("Errore di registrazione utente,riprovare...");	
		       }
			  
			   lblOutput.setVisible(true);
			   
		    
    	
    	
    }
    	
    void initialize() {	
    	
    	assert fieldNome != null : "fx:id=\"fieldNome\" was not injected: check your FXML file 'registrationform.fxml'.";
    	assert fieldCognome != null : "fx:id=\"fieldCognome\" was not injected: check your FXML file 'registrationform.fxml'.";
    	assert fieldNazione != null : "fx:id=\"fieldNazione\" was not injected: check your FXML file 'registrationform.fxml'.";
    	assert fieldCF != null : "fx:id=\"fieldCF\" was not injected: check your FXML file 'registrationform.fxml'.";
    	assert fieldData != null : "fx:id=\"fieldData\" was not injected: check your FXML file 'registrationform.fxml'.";
    	assert fieldUsername != null : "fx:id=\"fieldUsername\" was not injected: check your FXML file 'registrationform.fxml'.";
    	assert fieldPassword != null : "fx:id=\"fieldPassword\" was not injected: check your FXML file 'registrationform.fxml'.";

    }

}




