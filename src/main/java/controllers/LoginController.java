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
public class LoginController extends Controller	{
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
    
    public void checkOutcome(ResultSet rs) throws SQLException, NoSuchAlgorithmException{
    	
    	if (rs.next()) {
		    String password = rs.getString("password");
		    
		    if (password.equals(pwd)) {
				   lblMessage.setText("Login riuscito! 	(锞夆棔銉棔)锞?:锝ワ緹鉁?")	 ;
		    } else {
				   lblMessage.setText("Login fallito (銉巁<銆?");	
		    }
		 } else {
			lblMessage.setText("Username non esistente 	(锟锟?銉汇兓銉?");	
		 }
    }

    
    @FXML
    void handlegoback(ActionEvent event) throws IOException {
    	changeView("views/pageform.fxml",event);
    }
	void initialize() {
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'login.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'login.fxml'.";
        
        lblMessage.setVisible(false);
    }
}


