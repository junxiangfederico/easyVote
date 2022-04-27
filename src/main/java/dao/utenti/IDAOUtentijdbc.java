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


public class IDAOUtentijdbc implements IDAOUtenti {

	public IDAOUtentijdbc() {}
	public boolean login(String username, String password) throws NoSuchAlgorithmException, SQLException {		
		String hashedPassword = processPassword(password);
		String q = "SELECT * FROM users WHERE username=? and password=? ;";
		//Utente result = null;
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

	public boolean registraElettore(Utente t,String username, String pass) throws NoSuchAlgorithmException {
		boolean result=false;
		String q = "INSERT INTO `easyVote`.`users` (`name`, `lastname`, "
		   		+ "`birthdate`, `birthplace`, `codicefiscale`, `username`, `password`) "
		   		+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
		try {
			p.setString(1, t.getfirstname());
			p.setString(2, t.getlastname());
			p.setString(3, t.getData());
			p.setString(4, t.getnazioneNascita());
			p.setString(5, t.getcf());
			p.setString(6, username);
			p.setString(7, processPassword(pass));
			result=p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
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
	
	

	private String processPassword(String Password) throws NoSuchAlgorithmException {
		String hex="";
		try {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        md.update(Password.getBytes(StandardCharsets.UTF_8));
	        byte[] digest = md.digest();
	        hex = String.format("%064x", new BigInteger(1, digest));
	        
	    }
	    catch (NoSuchAlgorithmException e) {
	        System.err.println("not valid message digest algorithm");
	    }
        
		return hex;
		
	}
	@Override
	public Utente get(String id) {
		// non usato
		return null;
	}

	@Override
	public List<Utente> getAll() {
		// non usato
		return null;
	}

	@Override
	public void save(Utente t) {
		// non usato
		
	}

	@Override
	public void update(Utente  t, Utente u) {
		// non usato
	}

	@Override
	public void delete(Utente t) {
		// non usato
	}
}
