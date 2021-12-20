package easyVoteproject;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;

public class RegistrationController {

	private final String url = "jdbc:mysql://localhost/easyvote";
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
    void handleOk(ActionEvent event) throws NoSuchAlgorithmException {
    	try {
		       Connection conn = DriverManager.getConnection(url, "prova", "");
			   String Query = "INSERT INTO `easyVote`.`users` (`name`, `lastname`, "
			   		+ "`birthdate`, `birthplace`, `codicefiscale`, `username`, `password`) "
			   		+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			   
			   
			   String data = processDate(fieldData);
			   String password = processPassword(fieldPassword);

			   PreparedStatement preparedStatement = conn.prepareStatement(Query);

			   preparedStatement.setString(1, fieldNome.getText());
			   preparedStatement.setString(2, fieldCognome.getText());
			   preparedStatement.setString(3, data);
			   preparedStatement.setString(4, fieldNazione.getText());
			   preparedStatement.setString(5, fieldCF.getText());
			   preparedStatement.setString(6, fieldUsername.getText());
			   preparedStatement.setString(7, password);
			   
			   preparedStatement.executeUpdate();
			   
		    } catch (SQLException ex) {
		    	System.out.println("SQLExeption: "+ex.getMessage());
				System.out.println("SQLState: "+ex.getSQLState());
				System.out.println("VendorError: "+ ex.getErrorCode());
		    }
    	
    	
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
    	
    	assert fieldNome != null : "fx:id=\"username\" was not injected: check your FXML file 'login.fxml'.";
    	assert fieldCognome != null : "fx:id=\"password\" was not injected: check your FXML file 'login.fxml'.";
    	assert fieldNazione != null : "fx:id=\"password\" was not injected: check your FXML file 'login.fxml'.";
    	assert fieldCF != null : "fx:id=\"username\" was not injected: check your FXML file 'login.fxml'.";
    	assert fieldData != null : "fx:id=\"username\" was not injected: check your FXML file 'login.fxml'.";
    	assert fieldUsername != null : "fx:id=\"username\" was not injected: check your FXML file 'login.fxml'.";

    }

}




