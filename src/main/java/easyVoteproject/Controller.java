package easyVoteproject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
public class Controller {
	String url = "jdbc:mysql://localhost/easyvote";
    @FXML
    private Button btnOK;

    @FXML
    private Label lblMessage;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    void handleName(ActionEvent event) {

    }

    @FXML
    void handleOK(ActionEvent event)throws NoSuchAlgorithmException {
    	//System.out.println("Button pressed");
    	//String messaggio = "Button pressed";
    	lblMessage.setVisible(true);
    	
    	String nome = username.getText();
    	String pwd = password.getText();
    	try {
    		   String messaggio;
		       Connection conn = DriverManager.getConnection(url, "prova", "");
			   Statement statement	=  conn.createStatement(); 
			   String Query = "SELECT * FROM users WHERE username = '"+ nome+"';";
			   ResultSet rs =statement.executeQuery(Query);
			   if (rs.next()) {
				   String password = rs.getString("password");
				   
				   MessageDigest md = MessageDigest.getInstance("SHA-256");
				    md.update(pwd.getBytes(StandardCharsets.UTF_8));
				    byte[] digest = md.digest();

				    String hex = String.format("%064x", new BigInteger(1, digest));
				    System.out.println(password);
				    System.out.println(hex);				    
				    if(password.equals(hex)) {
				    	messaggio="Login riuscito! (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧";
						   lblMessage.setText(messaggio);	  
				    }else {
				    	messaggio="Login fallito :'(";
						   lblMessage.setText(messaggio);	
				    }
				      
				 }else {
				  messaggio="Login fallito :'(";
					lblMessage.setText(messaggio);	
				 }
		    } catch (SQLException ex) {
		    	System.out.println("SQLExeption: "+ex.getMessage());
				System.out.println("SQLState: "+ex.getSQLState());
				System.out.println("VendorError: "+ ex.getErrorCode());
		    }
    }

    @FXML
    void handlepwd(ActionEvent event) {

    }
    void initialize() {
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'login.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'login.fxml'.";
        
        lblMessage.setVisible(false);
    }
}

