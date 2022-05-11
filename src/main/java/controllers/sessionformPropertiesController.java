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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.sessione.Candidato;
import models.sessione.Partecipante;
import models.sessione.Partecipante.TipoPartecipante;
import models.sessione.SessioneDiVoto;
import models.sessione.TipoSessione;

public class sessionformPropertiesController extends Controller implements Initializable{
	
	private int sessionId = 0;

	private String type = "";
	private final String url = "jdbc:mysql://localhost/easyvote";
	
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
    	Connection conn;
		try {
			conn = DriverManager.getConnection(url, "prova", "");
			String Query = "UPDATE `easyVote`.`session` SET `candidati` = ?, `isOpen` = ? WHERE `id` = ?";
	   	 	PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(Query);
		    preparedStatement.setString(1, s.querygetCandidati());
		    preparedStatement.setInt(2, s.querygetIsOpen());
		    preparedStatement.setString(3, "" + sessionId);
			preparedStatement.execute();
			updateColumns(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@FXML
    void handleConfirm(ActionEvent event) throws IOException {

    	this.s.setIsOpen(true);
    	updateSessione(this.s);
    	
    	
        FXMLLoader loader= new FXMLLoader(getClass().getClassLoader().getResource("easyVoteproject/resources/voteOrdinaryform.fxml")); 
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
    	tableColumn.setCellValueFactory(
				new PropertyValueFactory<CandidatoSemplice, String>("identificativo")); 

		ObservableList<CandidatoSemplice> lista = FXCollections.observableArrayList();
    	for (Candidato c: s.getCandidati()) {
    		lista.add(new CandidatoSemplice(c.getidentificativo()));
    	}

        tableCandidates.setItems(lista);
    }
    
    /**
     * 
    
    @FXML
    private TableView<Candidato> tableCandidates;


    @FXML
    private TableColumn<Candidato, String> tableColumn;
     */
	public void initialize(URL location, ResourceBundle resources) {
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, "prova", "");
	    	int value = getValue(conn);
	    	sessionId = value;
	    	lblTop.setText("Sessione numero: " + value + " di tipo: " + type);
			this.s = loadSession();
			updateColumns(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
    
	private SessioneDiVoto loadSession() {
		SessioneDiVoto sessione = null;
		if (sessionId != 0) {
    		Connection conn;
    	    try {
    	    	conn = DriverManager.getConnection(url, "prova", "");
    	   	 	String Query = "SELECT * FROM session where session.id = "+ sessionId + ";";
    	   	 	PreparedStatement preparedStatement;
    			preparedStatement = conn.prepareStatement(Query);
    			ResultSet rs = preparedStatement.executeQuery();
    			rs.next();
    			
    			List<Candidato> l = new ArrayList<>();
    			TipoSessione t = null;
    			switch (rs.getString(5)) {
    				case "Referendum":
    					t = TipoSessione.Referendum;
    					break;
    				case "OrdinaleCandidati": 
    					t = TipoSessione.OrdinaleCandidati;
    					break;
    				case "OrdinalePartiti":
    					t = TipoSessione.OrdinalePartiti;
    					break;
    				case "CategoricoCandidati":
    					t = TipoSessione.CategoricoPartiti;
    					break;
    				case "CategoricoPreferenze":
    					t = TipoSessione.CategoricoPartiti;
    					break;
    			}
    			boolean b;
    			if (rs.getString(4).equals("1")) {
        			b = true;
    			}else {
    				b = false;
    			}
    			List<Candidato> candidati = new ArrayList<>();
    			String candidates = rs.getString(3);
    			
    			if (candidates == null){
    				sessione = new SessioneDiVoto(Integer.parseInt(rs.getString(1)), t, b, rs.getString(2), candidati);
    				return sessione;
    			}else if (candidates != null){
    				String[] entries = candidates.split(",");   
        			for (String s : entries) {
        				s = s.replace("{", "").stripLeading();
        				s = s.replace("}", "").stripTrailing();
        				String[] a = s.split(":");
        				for (int i = 0; i < a.length-1; i++) {
        					if (a[i].startsWith("\"candidato"));
        					Candidato d = new Candidato(null, a[i+1].replaceAll("\"", "").stripLeading().stripTrailing());
        					candidati.add(d);
        				}
        			}
    			}
    			
    			sessione = new SessioneDiVoto(Integer.parseInt(rs.getString(1)), t, b, rs.getString(2), candidati);
    	    }catch (SQLException e) {
    	    	e.printStackTrace();
    	    }
		}else {
			throw new IllegalArgumentException("Session ID non fornito");
		}
		return sessione;
	}

	private int getValue(Connection conn) throws SQLException {
   	 	String Query = "SELECT * FROM session ORDER BY id DESC LIMIT 1;";
   	 	PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(Query);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		type = rs.getString(5);
		return Integer.parseInt(rs.getString(1));
	}

}


