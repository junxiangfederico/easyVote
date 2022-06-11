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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.sessione.Partecipante.TipoPartecipante;
import models.voto.Voto;
import models.voto.VotoOrdinale;
import models.voto.VotoSingolo;
import models.sessione.*;

public class voteOrdinaryformController extends Controller{
	
	
	models.utenti.Utente u;
	private int sessionId = 0;

	private String type = "";
	private SessioneDiVoto s;
    @FXML
    private Label msg;
	@FXML 
	private ResourceBundle resources;

	@FXML 
	private URL location;
    @FXML
    private Button btnConfirm;
    @FXML
    private Button Btngoback;

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
    private IDAOVoto VotoDAO = DAOFactory.getFactory().getVotoDAOInstance();
    
    @FXML
  	void goback(ActionEvent event) {
  		changeView("views/selezioneform.fxml",event);
  	}
    /**
     * update della sessione, aggiornamento dei partecipanti: linea 116
     * @param s
     */
    private void updateSessione(SessioneDiVoto s) {
		sessioneDAO.update(s);
		updateColumns(s);
	}
    
    private boolean controll() {
    	String nome1=comboInput1.getSelectionModel().getSelectedItem().toString();
    	String nome2=comboInput2.getSelectionModel().getSelectedItem().toString();
    	String nome3=comboInput3.getSelectionModel().getSelectedItem().toString();
    	String nome4=comboInput4.getSelectionModel().getSelectedItem().toString();
        if(nome1.equals(nome2)||nome1.equals(nome3)||nome1.equals(nome4)||nome2.equals(nome3)||nome2.equals(nome4)||nome3.equals(nome4)) {
        	return false;
        }
        return true;
    }
    
    @FXML
    void handleConfirm(ActionEvent event) throws IOException{
    	boolean result;
    	System.out.println(comboInput1.getSelectionModel().getSelectedItem());
    	System.out.println(s.getCandidati().size());
    	if(controll()==false) {
    		msg.setText("Sono presenti duplicati,modifica le preferenze");
    		return;
    	}
    	switch (s.getCandidati().size()) {
    	case 1:
    		if (comboInput1.getSelectionModel().getSelectedItem() == null) {
    			outputLabel.setVisible(true);
    			outputLabel.setText("Riempi tutte le caselle disponibili");
    			return;
    		}
    		result=castVote(comboInput1.getSelectionModel().getSelectedItem());
    		if(result==false) {
    			msg.setText("Hai gia' espresso il tuo voto per questa sessione");
    			return;
    		}
    		break;
    	case 2:
    		if (comboInput1.getSelectionModel().getSelectedItem() == null 
    		|| comboInput2.getSelectionModel().getSelectedItem() == null ) {

    			outputLabel.setVisible(true);
    			outputLabel.setText("Riempi tutte le caselle disponibili");
    			return;
    		}

    		result=castVote(comboInput1.getSelectionModel().getSelectedItem(), comboInput2.getSelectionModel().getSelectedItem());
    		if(result==false) {
    			msg.setText("Hai gia' espresso il tuo voto per questa sessione");
    			return;
    		}
    		break;
    	case 3:
    		if (comboInput1.getSelectionModel().getSelectedItem() == null 
    		|| comboInput2.getSelectionModel().getSelectedItem() == null 
    		|| comboInput3.getSelectionModel().getSelectedItem() == null) {

    			outputLabel.setVisible(true);
    			outputLabel.setText("Riempi tutte le caselle disponibili");
    			return;
    		}
    		result=castVote(comboInput1.getSelectionModel().getSelectedItem(), comboInput2.getSelectionModel().getSelectedItem(), comboInput2.getSelectionModel().getSelectedItem());
    		if(result==false) {
    			msg.setText("Hai gia' espresso il tuo voto per questa sessione");
    			return;
    		}
    		break;
    	default :
    		if (comboInput1.getSelectionModel().getSelectedItem() == null 
    		|| comboInput2.getSelectionModel().getSelectedItem() == null 
    		|| comboInput3.getSelectionModel().getSelectedItem() == null 
    		|| comboInput4.getSelectionModel().getSelectedItem() == null) {

    			outputLabel.setVisible(true);
    			outputLabel.setText("Riempi tutte le caselle disponibili");
    			return;
    		}
    		result=castVote(comboInput1.getSelectionModel().getSelectedItem(), comboInput2.getSelectionModel().getSelectedItem(), comboInput3.getSelectionModel().getSelectedItem(), comboInput4.getSelectionModel().getSelectedItem());
    		System.out.println(result);
    		if(result==false) {
    			msg.setText("Hai gia' espresso il tuo voto per questa sessione");
    			return;
    			
    		}
    		break;
    	}
    	
    	changeView("views/operationform.fxml",event);
    	
    }
private boolean castVote(CandidatoSemplice selectedItem, CandidatoSemplice selectedItem2,
			CandidatoSemplice selectedItem3, CandidatoSemplice selectedItem4) {
		List<String> c = new ArrayList<>();
		c.add(selectedItem.getidentificativo());
		System.out.println(selectedItem.getidentificativo());
		c.add(selectedItem2.getidentificativo());

		c.add(selectedItem3.getidentificativo());

		c.add(selectedItem4.getidentificativo());
		
		int idUtente = receiveUtente();
		VotoOrdinale v = new VotoOrdinale(s.getNumeroSessione(), idUtente, c);
		
		boolean result=VotoDAO.castOrdinale(v);
		
		/*btnConfirm.setDisable(true);
		outputLabel.setText("Voto castato");
		outputLabel.setVisible(true);
		*/
		return result;
	}


/** 
 * Continue frmo here:
 * @param selectedItem
 * @param selectedItem2
 * @param selectedItem3
 */
private boolean castVote(CandidatoSemplice selectedItem, CandidatoSemplice selectedItem2,
			CandidatoSemplice selectedItem3) {
	List<String> c = new ArrayList<>();
	c.add(selectedItem.getidentificativo());

	c.add(selectedItem2.getidentificativo());

	c.add(selectedItem3.getidentificativo());

	
	int idUtente = receiveId();
	System.out.println("212" + idUtente);
	VotoOrdinale v = new VotoOrdinale(s.getNumeroSessione(), idUtente, c);
	
	boolean result=VotoDAO.castOrdinale(v);

	return result;
		
	}

private boolean castVote(CandidatoSemplice selectedItem, CandidatoSemplice selectedItem2) {

	List<String> c = new ArrayList<>();
	c.add(selectedItem.getidentificativo());

	
	c.add(selectedItem2.getidentificativo());

	
	int idUtente = receiveId();
	VotoOrdinale v = new VotoOrdinale(s.getNumeroSessione(), idUtente, c);
	
	boolean result=VotoDAO.castOrdinale(v);

	return result;
	}

private boolean castVote(CandidatoSemplice selectedItem) {

	List<String> c = new ArrayList<>();
	c.add(selectedItem.getidentificativo());
	
	int idUtente = receiveId();
	VotoOrdinale v = new VotoOrdinale(s.getNumeroSessione(), idUtente, c);
	
	boolean result=VotoDAO.castOrdinale(v);
	return result;
		
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

	public void initialize() {
		tableColumn.setCellValueFactory(new PropertyValueFactory<CandidatoSemplice, String>("identificativo")); 
		IdHolder holder = IdHolder.getInstance();
		sessionId = holder.getid();
		u = utenteDAO.UtentebyId(receiveUtente());
		lblLogged.setText("Utente loggato: " + u.getfirstname() + " " + u.getlastname());
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

