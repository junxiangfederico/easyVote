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
import database.DatabaseManager;
import easyVoteproject.Utente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.sessione.Candidato;
import models.sessione.Partecipante;
import models.sessione.Partecipante.TipoPartecipante;
import models.sessione.SessioneDiVoto;
import models.sessione.TipoSessione;
import models.voto.Voto;
import models.voto.VotoOrdinale;
import models.voto.VotoSingolo;
import models.sessione.*;

public class votePreferentialformController extends Controller{
	
	
	models.utenti.Utente u;
	private int sessionId = 0;

	private String type = "";
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
    private Label outputLabel;

    @FXML
    private ComboBox<CandidatoSemplice> comboInput1;

    @FXML
    private ComboBox<CandidatoSemplice> comboInput2;

    @FXML
    private ComboBox<CandidatoSemplice> comboInput3;

    @FXML
    private ComboBox<CandidatoSemplice> comboInput4;

    
    @FXML
    private TableView<CandidatoSemplice> tableCandidates = new TableView<>();

    
    @FXML
    private Label lblLogged;

    @FXML
    private TableColumn<CandidatoSemplice, String> tableColumn = new TableColumn<>("Nomi");
    private SessioneIDAO sessioneDAO = DAOFactory.getFactory().getSessioneDAOInstance();
    private IDAOUtenti utenteDAO = DAOFactory.getFactory().getUtenteDAOInstance();

    /**
     * update della sessione, aggiornamento dei partecipanti: linea 116
     * @param s
     */
    private void updateSessione(SessioneDiVoto s) {
		sessioneDAO.update(s);
		updateColumns(s);
	}
    
    @FXML
    void handleConfirm(ActionEvent event) throws IOException{
    	
    	switch (s.getCandidati().size()) {
    	case 1:
    		if (comboInput1.getSelectionModel().getSelectedItem() == null) {
    			outputLabel.setVisible(true);
    			outputLabel.setText("Riempi tutte le caselle disponibili");
    			return;
    		}
    		castVote(comboInput1.getSelectionModel().getSelectedItem());
    		break;
    	case 2:
    		if (comboInput1.getSelectionModel().getSelectedItem() == null 
    		|| comboInput2.getSelectionModel().getSelectedItem() == null ) {

    			outputLabel.setVisible(true);
    			outputLabel.setText("Riempi tutte le caselle disponibili");
    			return;
    		}

    		castVote(comboInput1.getSelectionModel().getSelectedItem(), comboInput2.getSelectionModel().getSelectedItem());
    		break;
    	case 3:
    		if (comboInput1.getSelectionModel().getSelectedItem() == null 
    		|| comboInput2.getSelectionModel().getSelectedItem() == null 
    		|| comboInput3.getSelectionModel().getSelectedItem() == null) {

    			outputLabel.setVisible(true);
    			outputLabel.setText("Riempi tutte le caselle disponibili");
    			return;
    		}
    		castVote(comboInput1.getSelectionModel().getSelectedItem(), comboInput2.getSelectionModel().getSelectedItem(), comboInput2.getSelectionModel().getSelectedItem());
    		break;
    	case 4:
    		if (comboInput1.getSelectionModel().getSelectedItem() == null 
    		|| comboInput2.getSelectionModel().getSelectedItem() == null 
    		|| comboInput3.getSelectionModel().getSelectedItem() == null 
    		|| comboInput4.getSelectionModel().getSelectedItem() == null) {

    			outputLabel.setVisible(true);
    			outputLabel.setText("Riempi tutte le caselle disponibili");
    			return;
    		}
    		castVote(comboInput1.getSelectionModel().getSelectedItem(), comboInput2.getSelectionModel().getSelectedItem(), comboInput3.getSelectionModel().getSelectedItem(), comboInput4.getSelectionModel().getSelectedItem());
    		break;
    	}
    	
    }
private void castVote(CandidatoSemplice selectedItem, CandidatoSemplice selectedItem2,
			CandidatoSemplice selectedItem3, CandidatoSemplice selectedItem4) {
		List<String> c = new ArrayList<>();
		c.add(selectedItem.getidentificativo());

		c.add(selectedItem2.getidentificativo());

		c.add(selectedItem3.getidentificativo());

		c.add(selectedItem4.getidentificativo());
		
		int idUtente = receiveUtente();
		VotoOrdinale v = new VotoOrdinale(s.getNumeroSessione(), idUtente, c);
		
		DAO(v);
	}

/**
 * to be moved to DAO
 * @param v
 */
private void DAO(VotoOrdinale v) {
	String q = "INSERT INTO `easyVote`.`voto` (`idSession`, `idUser`, `selection`) VALUES (?, ?, ?);";
	PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);

	System.out.println(v.getSessioneDiVoto() + " " + v.getIdVotante() + v.getSelection());
	try {	
		p.setInt(1, v.getSessioneDiVoto());
		p.setInt(2, v.getIdVotante());
		p.setString(3, v.getSelection());
		p.execute();
	} catch (SQLException e) {

        e.printStackTrace();
	}
	btnConfirm.setDisable(true);
	outputLabel.setText("Voto castato");
	outputLabel.setVisible(true);
}

/** 
 * Continue frmo here:
 * @param selectedItem
 * @param selectedItem2
 * @param selectedItem3
 */
private void castVote(CandidatoSemplice selectedItem, CandidatoSemplice selectedItem2,
			CandidatoSemplice selectedItem3) {

	List<String> c = new ArrayList<>();
	c.add(selectedItem.getidentificativo());

	c.add(selectedItem2.getidentificativo());

	c.add(selectedItem3.getidentificativo());

	
	int idUtente = receiveId();
	System.out.println("212" + idUtente);
	VotoOrdinale v = new VotoOrdinale(s.getNumeroSessione(), idUtente, c);
	
	DAO(v);
		
	}

private void castVote(CandidatoSemplice selectedItem, CandidatoSemplice selectedItem2) {

	List<String> c = new ArrayList<>();
	c.add(selectedItem.getidentificativo());

	
	c.add(selectedItem2.getidentificativo());

	
	int idUtente = receiveId();
	VotoOrdinale v = new VotoOrdinale(s.getNumeroSessione(), idUtente, c);
	
	DAO(v);
		
	}

private void castVote(CandidatoSemplice selectedItem) {

	List<String> c = new ArrayList<>();
	c.add(selectedItem.getidentificativo());
	
	int idUtente = receiveId();
	VotoOrdinale v = new VotoOrdinale(s.getNumeroSessione(), idUtente, c);
	
	DAO(v);
		
	}

    
    public void updateColumns(SessioneDiVoto s){
		ObservableList<CandidatoSemplice> lista = FXCollections.observableArrayList();
		comboInput1.setItems(lista);
		comboInput2.setItems(lista);
		comboInput3.setItems(lista);
		comboInput4.setItems(lista);
    	for (Candidato c: s.getCandidati()) {
    		lista.add(new CandidatoSemplice(c.getidentificativo()));
    	}

        tableCandidates.setItems(lista);
    }
    

    
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
		IdHolder holder = IdHolder.getInstance();
		sessionId = holder.getid();
		u = utenteDAO.UtentebyId(receiveUtente());
		lblLogged.setText("Utente loggato: " + u.getfirstname() + " " + u.getlastname());
		try {
			this.s =sessioneDAO.getById(sessionId);
		
			//System.out.println(s.getCandidati().isEmpty());
			//List<Candidato>listu=s.getCandidati();
			//System.out.println(listu);
			//System.out.println(s.getTipoSessione());
			
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
    	//System.out.println(sessionId);
		
		updateColumns(s);
		updateComboBoxes(s);
	}

	private void updateComboBoxes(SessioneDiVoto s2) {
		switch (s.getCandidati().size()) {
		case 1:
			comboInput2.setDisable(true);
			comboInput3.setDisable(true);
			comboInput4.setDisable(true);
			break;
		case 2:
			comboInput3.setDisable(true);
			comboInput4.setDisable(true);
			break;
		case 3:
			comboInput4.setDisable(true);
			break;

		}
		
	}

}

