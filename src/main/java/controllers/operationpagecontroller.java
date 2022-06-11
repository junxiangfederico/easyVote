package controllers;

import dao.factory.DAOFactory;
import dao.utenti.IDAOUtenti;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import models.utenti.*;

public class operationpagecontroller extends Controller{
	Utente u;
    @FXML
    private Button create;

    @FXML
    private Button vote;
    @FXML
    private Button Btngoback;
	

    @FXML
    private Button btnResults;

    
    private IDAOUtenti utenteDAO = DAOFactory.getFactory().getUtenteDAOInstance();
    

    @FXML
    void requestResults(ActionEvent event) {
    	changeView("views/selezioneformbyAdmin.fxml",event);
    }
    
    @FXML
    void gobacck(ActionEvent event) {
		changeView("views/login.fxml",event);
    }
    @FXML
    void handlecreate(ActionEvent event) {
    	changeView("views/sessionform.fxml",event);
    }

    @FXML
    void handlevote(ActionEvent event) {
    	changeView("views/selezioneform.fxml",event);
    }
    
    public void initialize() {
    	u=utenteDAO.UtentebyId(receiveUtente());
    	if (u.isScrutatore()==true) {
    		vote.setText("Modifica sessioni");
    		btnResults.setVisible(true);
    		create.setVisible(true);
    	}
    }
    
    
}
