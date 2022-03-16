package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.sql.*;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.math.BigInteger;
public class LoginController {
	String url = "jdbc:mysql://localhost/easyvote";
	private Stage stage;
	private Scene scene;
	private Parent root;
    @FXML
    private Button btnOK;
    @FXML
    private Button back;
    @FXML
    private Label lblMessage;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    void handleOK(ActionEvent event)throws NoSuchAlgorithmException {
    	lblMessage.setVisible(true);
    	try {
    		   Connection conn = DriverManager.getConnection(url, "prova", "");

			   PreparedStatement preparedStatement = prepareStatement(conn);
			   ResultSet rs = preparedStatement.executeQuery();
			   
			   checkOutcome(rs);
			   	
		    } catch (SQLException ex) {
		    	System.out.println("SQLExeption: "+ex.getMessage());
				System.out.println("SQLState: "+ex.getSQLState());
				System.out.println("VendorError: "+ ex.getErrorCode());
		    }
    }

    
    
    private PreparedStatement prepareStatement(Connection conn) throws SQLException {
		
    	String Query = "SELECT * FROM users WHERE username=?";
		PreparedStatement preparedStatement =conn.prepareStatement(Query);
		preparedStatement.setString(1, username.getText());
		   
		return preparedStatement;
	}
    
    private String processPassword(TextField fieldPassword) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(fieldPassword.getText().getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        String hex = String.format("%064x", new BigInteger(1, digest));
        
		return hex;
	}

    
    public void checkOutcome(ResultSet rs) throws SQLException, NoSuchAlgorithmException{
    	String pwd = processPassword(password);
    	
    	if (rs.next()) {
		    String password = rs.getString("password");
		    
		    if (password.equals(pwd)) {
				   lblMessage.setText("Login riuscito! 	(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧");	  
		    } else {
				   lblMessage.setText("Login fallito (ノ_<。)");	
		    }
		 } else {
			lblMessage.setText("Username non esistente 	(￣_￣)・・・ ");	
		 }
    }


    @FXML
    void handlegoback(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getClassLoader().getResource("easyVoteproject/resources/pageform.fxml"));
   	 	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   	 	scene = new Scene(root);
   	 	stage.setScene(scene);
   	 	stage.show();
    }
	void initialize() {
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'login.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'login.fxml'.";
        
        lblMessage.setVisible(false);
    }
}


