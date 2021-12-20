package easyVoteproject;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private final String url = "jdbc:mysql://localhost/easyvote";
	@FXML
    private Button back;
    @FXML
    private Button enterButton;

    @FXML
    private TextField fieldCF;

    @FXML
    private TextField fieldCognome;

    @FXML
    private DatePicker fieldData;

    @FXML
    private TextField fieldNazione;

    @FXML
    private TextField fieldNome;

    @FXML
    private TextField fieldPassword;

    @FXML
    private TextField fieldUsername;

    @FXML
    private Label lblOutput;
    
    @FXML
    void goback(ActionEvent event) throws IOException{
    	root = FXMLLoader.load(getClass().getClassLoader().getResource("easyVoteproject/resources/pageform.fxml"));
   	 	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   	 	scene = new Scene(root);
   	 	stage.setScene(scene);
   	 	stage.show();
    }
    @FXML
    void handleOk(ActionEvent event) throws NoSuchAlgorithmException {
    	
    	try {
		       Connection conn = DriverManager.getConnection(url, "prova", "");
		       
		       if (verifyPresence(fieldUsername.getText(), conn)) {
				   lblOutput.setText("Utente con username " + fieldUsername.getText() + " già presente, provare con un altro username");	
				   lblOutput.setVisible(true);
		    	   return;
		       }
		       
			   PreparedStatement preparedStatement = prepareStatement(conn);
			   preparedStatement.executeUpdate();
			   
			   lblOutput.setText("Utente " + fieldUsername.getText() + " registrato con successo");	
			   lblOutput.setVisible(true);
			   
		    } catch (SQLException ex) {
		    	System.out.println("SQLExeption: "+ex.getMessage());
				System.out.println("SQLState: "+ex.getSQLState());
				System.out.println("VendorError: "+ ex.getErrorCode());
		    }
    	
    	
    }
    
    private boolean verifyPresence(String username, Connection conn) throws SQLException {
    	 String Query = "SELECT * FROM users WHERE username=?";
    	 PreparedStatement preparedStatement =conn.prepareStatement(Query);
    	 preparedStatement.setString(1, username);
		 ResultSet rs = preparedStatement.executeQuery();
		 if (rs.next()) {
			 return true;
		 }
		return false;
	}

	public PreparedStatement prepareStatement(Connection conn) throws NoSuchAlgorithmException, SQLException{
    	 String Query = "INSERT INTO `easyVote`.`users` (`name`, `lastname`, "
			   		+ "`birthdate`, `birthplace`, `codicefiscale`, `username`, `password`) "
			   		+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
    	 
    	 String data = processDate(fieldData);
    	 String password = processPassword(fieldPassword);
    	 
    	 if (fieldCF.getText().length() != 16) {
    		 lblOutput.setText("Codice fiscale inserito non valido, lunghezza diversa da 16");	
    		 lblOutput.setVisible(true);
    		 throw new IllegalArgumentException("Codice fiscale inserito non valido, lunghezza diversa da 16");
    	 }
    	 
    	 if (fieldNome.getText().length() < 1 || fieldCognome.getText().length() < 1 ||fieldNazione.getText().length() < 1 || fieldUsername.getText().length() < 1 ||
    		 fieldPassword.getText().length() < 1) {
    		 lblOutput.setText("I dati inseriti non sono validi, riprovare");	
    		 lblOutput.setVisible(true);
    		 throw new IllegalArgumentException("I dati inseriti non sono validi, riprovare");
    	 }
    	 
    	 
    	PreparedStatement preparedStatement = conn.prepareStatement(Query);
    	preparedStatement.setString(1, fieldNome.getText());
		preparedStatement.setString(2, fieldCognome.getText());
		preparedStatement.setString(3, data);
		preparedStatement.setString(4, fieldNazione.getText());
		preparedStatement.setString(5, fieldCF.getText().toUpperCase());
		preparedStatement.setString(6, fieldUsername.getText());
		preparedStatement.setString(7, password);
		return preparedStatement;
    }
    
    
    private String processPassword(TextField fieldPassword) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(fieldPassword.getText().getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        String hex = String.format("%064x", new BigInteger(1, digest));
        
		return hex;
	}

	private String processDate(DatePicker fieldData) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.US);
		String formattedValue = (fieldData.getValue()).format(formatter);
		
		return formattedValue;
	}

	
    void initialize() {	
    	
    	assert fieldNome != null : "fx:id=\"fieldNome\" was not injected: check your FXML file 'registrationform.fxml'.";
    	assert fieldCognome != null : "fx:id=\"fieldCognome\" was not injected: check your FXML file 'registrationform.fxml'.";
    	assert fieldNazione != null : "fx:id=\"fieldNazione\" was not injected: check your FXML file 'registrationform.fxml'.";
    	assert fieldCF != null : "fx:id=\"fieldCF\" was not injected: check your FXML file 'registrationform.fxml'.";
    	assert fieldData != null : "fx:id=\"fieldData\" was not injected: check your FXML file 'registrationform.fxml'.";
    	assert fieldUsername != null : "fx:id=\"fieldUsername\" was not injected: check your FXML file 'registrationform.fxml'.";
    	assert fieldPassword != null : "fx:id=\"fieldPassword\" was not injected: check your FXML file 'registrationform.fxml'.";

    }

}




