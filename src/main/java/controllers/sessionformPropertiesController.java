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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.sessione.Candidato;
import models.sessione.SessioneDiVoto;
import models.sessione.TipoSessione;

public class sessionformPropertiesController implements Initializable{
	
	int sessionId = 0;

	private final String url = "jdbc:mysql://localhost/easyvote";
	
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
    private TableView<?> tableCandidates;

    @FXML
    void handleAdd(ActionEvent event) {
    	
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
    			//System.out.println(rs.getString(3));
    			String[] entries = rs.getString(3).split(",");   
    			List<Candidato> candidati = new ArrayList<>();
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
    			
    			for (Candidato aa : candidati) {
    				System.out.println(aa.getIdentificativo());
    			}
    			SessioneDiVoto s = new SessioneDiVoto(Integer.parseInt(rs.getString(1)), t, b, rs.getString(2), candidati);
    			if (!inputName.getText().equals("")) {
    				Candidato d = new Candidato(null, inputName.getText());
        			SessioneDiVoto.addCandidato(d);
        			updateSessione(s);
    			}else {
    				outputLabel.setText("Candidato da inserire non può avere nome nullo");
    				outputLabel.setVisible(true);
    			}
    			
    			
    	    }catch (SQLException e) {
    	    	e.printStackTrace();
    	    }
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
	    	int value = getValue(conn);
	    	sessionId = value;
	    	lblTop.setText("Session numero: " + value);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@FXML
    void handleConfirm(ActionEvent event) {
    	
    }

    @FXML
    void handleRemove(ActionEvent event) {

    }
    
	public void initialize(URL location, ResourceBundle resources) {
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, "prova", "");
	    	int value = getValue(conn);
	    	sessionId = value;
	    	lblTop.setText("Session numero: " + value);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
    
	private int getValue(Connection conn) throws SQLException {
   	 	String Query = "SELECT * FROM session ORDER BY id DESC LIMIT 1;";
   	 	PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(Query);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		return Integer.parseInt(rs.getString(1));
	}

}