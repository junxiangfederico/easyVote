package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dao.factory.DAOFactory;
import dao.sessione.SessioneIDAO;
import dao.utenti.IDAOUtenti;
import dao.voto.IDAOVoto;
import database.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.sessione.Partecipante.TipoPartecipante;
import models.voto.Voto;
import models.voto.VotoSingolo;
import models.sessione.*;
import models.utenti.*;

public class voteCategoricController extends Controller{
	
	private models.utenti.Utente u;
	
	private int sessionId = 0;

	private SessioneDiVoto s;
	
	@FXML 
	private ResourceBundle resources;

	@FXML 
	private URL location;
    @FXML
    private Button btnConfirm;

    @FXML
    private Label lblTop;
    @FXML
    private Label msg;
    @FXML
    private Label outputLabel;
    
    @FXML
    private Label lblLogged;
    @FXML
    private Button Btngoback;
    
    @FXML
    private TableView<CandidatoSemplice> tableCandidates = new TableView<>();


    @FXML
    private TableColumn<CandidatoSemplice, String> tableColumn = new TableColumn<>("Nomi");
    private SessioneIDAO sessioneDAO = DAOFactory.getFactory().getSessioneDAOInstance();
    private IDAOUtenti utenteDAO = DAOFactory.getFactory().getUtenteDAOInstance();
    private IDAOVoto VotoDAO = DAOFactory.getFactory().getVotoDAOInstance();
    
    /**
     * update della sessione, aggiornamento dei partecipanti: linea 116
     * @param s
     */
    private void updateSessione(SessioneDiVoto s) {
		sessioneDAO.update(s);
		updateColumns(s);
	}
    @FXML
  	void goback(ActionEvent event) {
  		changeView("views/selezioneform.fxml",event);
  	}

	@FXML
    void handleConfirm(ActionEvent event) throws IOException {
    	CandidatoSemplice c2 = tableCandidates.getSelectionModel().getSelectedItem();
    	if (c2 == null) {
    		outputLabel.setText("Seleziona un candidato");
    		return;
    	}
    	int idVotante = receiveUtente();
		VotoSingolo v = new VotoSingolo(sessionId, idVotante, c2.getIdentificativo());
    	boolean result=VotoDAO.castCategorico(v);
    	if(!result) {
    		msg.setText("Hai gia' espresso il tuo voto per questa sessione");
    		return;
    	}
    	btnConfirm.setDisable(true);
		outputLabel.setText("Voto castato per: " + v.getNomeCandidato());
		outputLabel.setVisible(true);
		changeView("views/operationform.fxml",event);
    	
    }


    
    
    public void updateColumns(SessioneDiVoto s){
    	
		ObservableList<CandidatoSemplice> lista = FXCollections.observableArrayList();
    	for (CandidatoSemplice c: s.getCandidati()) {
    		lista.add(new CandidatoSemplice(c.getidentificativo()));
    	}

        tableCandidates.setItems(lista);
    }
    
    /**
     * 
    
    @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     * @FXML
    private TableView<Candidato> tableCandidates;


    @FXML
    private TableColumn<Candidato, String> tableColumn;
     */

    
	SessioneDiVoto loadSession(int sessionId) {
		
			SessioneDiVoto sessione=null;
			try {
				sessione = sessioneDAO.getById(sessionId);
				return sessione;
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
			return sessione;
		
	}

	
	@FXML
	private void receiveData(ActionEvent event) {
	  
	  
	}
	public void initialize() {
		tableColumn.setCellValueFactory(new PropertyValueFactory<CandidatoSemplice, String>("identificativo")); 
		sessionId = receiveId();
		
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
    	lblTop.setText("Sessione numero: " + s.getNumeroSessione() + " 					Tipo: " + s.getTipoSessione());
    	System.out.println(sessionId);
		
		updateColumns(s);
	}

}

