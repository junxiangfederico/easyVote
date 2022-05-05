package controllers;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.sessione.Candidato;
import models.sessione.SessioneDiVoto;
import models.sessione.TipoSessione;


/**
 * Session info loaded: 	- load tableview
 * 							- selection tableview
 * 							- process selection
 * @author nyaaaa
 *
 */
public class voteOrdinaryformController extends Controller implements Initializable{

	@FXML 
	private ResourceBundle resources;

	@FXML 
	private URL location;
	
	private int sessionId = 0;

	private final String url = "jdbc:mysql://localhost/easyvote";
	
	private SessioneDiVoto s;
	
    @FXML
    private Button btnConfirm;

    @FXML
    private Label lblTop;

    @FXML
    private TableColumn<CandidatoSemplice, String> tableColumn  = new TableColumn<>("Nomi");

    @FXML
    private TableView<CandidatoSemplice> tableCandidates = new TableView<>();

    @FXML
    void handleVote(ActionEvent event) {
    	
    }
    
    public SessioneDiVoto loadSession(int sessionId) {
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
    			this.s = sessione;
    			updateColumns(s);
    	    }catch (SQLException e) {
    	    	e.printStackTrace();
    	    }
		}else {
			throw new IllegalArgumentException("Session Id non fornito");
		}
		return sessione;
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
    
    
    public void initialize(URL location, ResourceBundle resources) {
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, "prova", "");
			//updateColumns(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

}
