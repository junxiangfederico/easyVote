package dao.utenti;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import database.DatabaseManager;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.utenti.*;

// un prodotto della famiglia UtenteDAO
public class IDAOUtentijdbc implements IDAOUtenti {

	public IDAOUtentijdbc() {}
	public boolean login(String username, String password) throws NoSuchAlgorithmException {		
		String hashedPassword = processPassword(password);
		String q = "SELECT * FROM users WHERE username=? and password=? ;";
		Utente result = null;
		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
			p.setString(1, username);
			p.setString(2, hashedPassword);
			
			ResultSet rs = p.executeQuery();
		
		
		return checkOutcome(rs);
	}
	public boolean checkOutcome(ResultSet rs) throws SQLException {
    	
    	if (rs.next()){
		    return true;
		 }else {
			 return false;	
	    } 
    }

	/*@ requires t != null && pass != null && getId(t) == 0;
	  @ ensures \result == true;
	  @*/
	public boolean registraElettore(Utente t, String pass) {
		boolean result;
		String q = "INSERT INTO `easyVote`.`users` (`name`, `lastname`, "
		   		+ "`birthdate`, `birthplace`, `codicefiscale`, `username`, `password`) "
		   		+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
		try {
			p.setString(1, t.getNome());
			p.setString(2, t.getCognome());
			p.setString(3, t.getCF());
			p.setString(4, t.isGestore() ? "g" : "e");
			p.setString(5, sha1Hashing(pass));
			
			result = p.execute();
		} catch (SQLException e) {
			throw new DatabaseException("Problemi con la base dati, riprovare! Context: registraElettore");
		}
		
		return result;
	}
	private String processDate(DatePicker fieldData) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.US);
		String formattedValue = (fieldData.getValue()).format(formatter);
		
		return formattedValue;
	}

	
	
	/*@
	  @ requires password != null;
	  @ ensures \result != password;
	  @*/

	public boolean verifyPresence(String username) throws SQLException {
   	 String Query = "SELECT * FROM users WHERE username=?";
   	 PreparedStatement preparedStatement =DatabaseManager.getInstance().preparaStatement(Query);
   	 preparedStatement.setString(1, username);
		 ResultSet rs = preparedStatement.executeQuery();
		 if (rs.next()) {
			 return true;
		 }
		return false;
	}
	
	

	private String processPassword(String fieldPassword) throws NoSuchAlgorithmException {
		String hex="";
		try {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        md.update(fieldPassword.getBytes(StandardCharsets.UTF_8));
	        byte[] digest = md.digest();
	        hex = String.format("%064x", new BigInteger(1, digest));
	        
	    }
	    catch (NoSuchAlgorithmException e) {
	        System.err.println("not valid message digest algorithm");
	    }
        
		return hex;
		
	}
}
