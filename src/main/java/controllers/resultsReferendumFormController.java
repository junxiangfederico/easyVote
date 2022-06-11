package controllers;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dao.factory.DAOFactory;
import dao.sessione.SessioneIDAO;
import dao.utenti.IDAOUtenti;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.sessione.IdHolder;
import models.sessione.SessioneDiVoto;
import dao.voto.*;
public class resultsReferendumFormController extends Controller{

	models.utenti.Utente u;

	private SessioneDiVoto s;
	
	private int sessionId = 0;
	@FXML
    private Button Btngoback;

	
    @FXML
    private Label lblOutput;

    @FXML
    private Label lblLogged;

    
    @FXML
    private Label lblOutputReferendum;
    

    private SessioneIDAO sessioneDAO = DAOFactory.getFactory().getSessioneDAOInstance();
    private IDAOUtenti utenteDAO = DAOFactory.getFactory().getUtenteDAOInstance();
    private IDAOVoto VotoDAO = DAOFactory.getFactory().getVotoDAOInstance();

    
    @FXML
	void goback(ActionEvent event) {
		changeView("views/selezioneformbyadmin.fxml",event);
	}
    
    public void initialize() {

    	sessionId=receiveId();
		System.out.println(sessionId);		
		u = utenteDAO.UtentebyId(receiveUtente());
		lblLogged.setText("Utente loggato: " 
		+ u.getfirstname() + " "
				+ u.getlastname());
		
		try {
			this.s =sessioneDAO.getById(sessionId);
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	lblOutput.setText(s.getNumeroSessione() + " di tipo: " + s.getTipoSessione());
    	
    	lblOutputReferendum.setText(VotoDAO.getReferendumResults(sessionId));

	}

    
}


