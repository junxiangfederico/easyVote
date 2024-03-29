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
    private Button Btngoback;

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
        		s.addCandidato(new CandidatoSemplice( inputName.getText()));
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
	void goback(ActionEvent event) {
		changeView("views/selezioneform.fxml",event);
	}
	@FXML
    void handleConfirm(ActionEvent event) throws IOException {


    	updateSessione(this.s);
        changeView("views/operationform.fxml",event);
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

    
	public void initialize() {
		tableColumn.setCellValueFactory(new PropertyValueFactory<CandidatoSemplice, String>("identificativo")); 
		IdHolder holder = IdHolder.getInstance();
		sessionId = holder.getid();
		try {
			this.s =sessioneDAO.getById(sessionId);
		
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


