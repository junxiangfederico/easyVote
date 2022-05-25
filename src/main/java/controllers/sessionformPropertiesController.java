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
import models.sessione.Candidato;
import models.sessione.Partecipante;
import models.sessione.Partecipante.TipoPartecipante;
import models.sessione.SessioneDiVoto;
import models.sessione.TipoSessione;
import models.sessione.*;

public class sessionformPropertiesController extends Controller{
	//Utenti u;
	private int sessionId = 0;
	private SessioneDiVoto s;
	
	@FXML 
	private ResourceBundle resources;

	@FXML 
	private URL location;
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnRemove;

    @FXML
    private TextField inputName;

    @FXML
    private Label lblTop;

    @FXML
    private Label outputLabel;

    
    @FXML
    private TableView<CandidatoSemplice> tableCandidates = new TableView<>();


    @FXML
    private TableColumn<CandidatoSemplice, String> tableColumn = new TableColumn<>("Nomi");
    private SessioneIDAO sessioneDAO = DAOFactory.getFactory().getSessioneDAOInstance();
    @FXML
    void handleAdd(ActionEvent event) {
    		if (inputName.getText().isBlank() || inputName.getText().isEmpty()) {
    			System.out.println("returning");
    			return;
    		}else {
        		s.addCandidato(new Candidato(null, inputName.getText()));
        		updateColumns(s);
    		}
    }

    /**
     * update della sessione, aggiornamento dei partecipanti: linea 116
     * @param s
     */
    private void updateSessione(SessioneDiVoto s) {
		sessioneDAO.update(s);
		updateColumns(s);
	}

	@FXML
    void handleConfirm(ActionEvent event) throws IOException {

    	this.s.setIsOpen(true);
    	updateSessione(this.s);
        //FXMLLoader loader= new FXMLLoader(getClass().getClassLoader().getResource("easyVoteproject/resources/voteOrdinaryform.fxml")); 
    	FXMLLoader loader= new FXMLLoader(getClass().getClassLoader().getResource("views/voteOrdinaryform.fxml")); 
    	Parent root = loader.load(); 
        voteOrdinaryformController votoOrdinario =loader.getController();
        votoOrdinario.loadSession(s.getNumeroSessione());
        changeView("easyVoteproject/resources/voteOrdinaryform.fxml",event);
    }

	
	
    @FXML
    void handleRemove(ActionEvent event) {
    	CandidatoSemplice c2 = tableCandidates.getSelectionModel().getSelectedItem();
    	if (c2 == null) return;
    	if (s.removeCandidato(c2.getidentificativo())) {
    		updateSessione(this.s);
    	}else {
    		updateColumns(s);
    		throw new IllegalArgumentException("Utente non rimosso, desync tra UI e backend presente");
    	}
    	
    }
    
    
    public void updateColumns(SessioneDiVoto s){
    	/*tableColumn.setCellValueFactory(
				new PropertyValueFactory<CandidatoSemplice, String>("identificativo")); 
		*/
		ObservableList<CandidatoSemplice> lista = FXCollections.observableArrayList();
    	for (Candidato c: s.getCandidati()) {
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

    
	private SessioneDiVoto loadSession() {
		
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

	/*private int getValue(Connection conn) throws SQLException {
   	 	String Query = "SELECT * FROM session ORDER BY id DESC LIMIT 1;";
   	 	PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(Query);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		type = rs.getString(5);
		return Integer.parseInt(rs.getString(1));
	}*/
	@FXML
	private void receiveData(ActionEvent event) {
	  
	  
	}
	public void initialize() {
		tableColumn.setCellValueFactory(new PropertyValueFactory<CandidatoSemplice, String>("identificativo")); 
		//this.s = loadSession();
		IdHolder holder = IdHolder.getInstance();
		sessionId = holder.getid();
		try {
			this.s =sessioneDAO.getById(sessionId);
		
			//System.out.println(s.getCandidati().isEmpty());
			//List<Candidato>listu=s.getCandidati();
			//System.out.println(listu);
			System.out.println(s.getTipoSessione());
			
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
    	lblTop.setText("Sessione numero: " + s.getNumeroSessione() + " di tipo: " + s.getTipoSessione());
    	System.out.println(sessionId);
		
		updateColumns(s);
	}

}


