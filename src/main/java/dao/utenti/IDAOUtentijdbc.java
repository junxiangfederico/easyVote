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
import models.utenti.*;


public class IDAOUtentijdbc implements IDAOUtenti {

	public IDAOUtentijdbc() {}
	public int login(String username, String password) throws NoSuchAlgorithmException, SQLException {		
		String hashedPassword = processPassword(password);
		String q = "SELECT iduser FROM users WHERE username=? and password=? ;";
		//Utente result = null;
		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
			p.setString(1, username);
			p.setString(2, hashedPassword);
			
			ResultSet rs = p.executeQuery();
		
		
		return checkOutcome(rs);
	}
	public int checkOutcome(ResultSet rs) throws SQLException {
    	
    	if (rs.next()){
		    return Integer.parseInt(rs.getString(1));
		 }else {
			 return -1;	
	    } 
    }

	public boolean registraElettore(Utente t,String username, String pass) {
		boolean result = false;
		String q = "INSERT INTO `easyVote`.`users` (`name`, `lastname`, "
		   		+ "`birthdate`, `birthplace`, `codicefiscale`, `username`, `password`,`isadmin`) "
		   		+ "VALUES (?, ?, ?, ?, ?, ?, ?,0)";
		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
		try {
			p.setString(1, t.getfirstname());
			p.setString(2, t.getlastname());
			p.setString(3, t.getData());
			p.setString(4, t.getnazioneNascita());
			p.setString(5, t.getcf());
			p.setString(6, username);
			p.setString(7, processPassword(pass));
			
			int i = p.executeUpdate();
			if(i>0) {
				result=true;
			}else {
				result=false;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
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
	@Override
	public Utente UtentebyId(int i) {
		Utente u=null;
		String q = "SELECT * FROM users WHERE iduser=? ;";
		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
		try {
			p.setInt(1,i);
			ResultSet rs = p.executeQuery();
			if(rs.next()) {
				u=getUtente(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	private Utente getUtente(ResultSet res) {
		Utente result = null;
		String n, c, cf,nn,d;
		int ia;
	
		
		try {
			n = res.getString(2);
			c = res.getString(3);
			cf = res.getString(6);
			nn = res.getString(5);
			d=res.getString(4);
			ia=Integer.parseInt(res.getString(9));
			// vedi il tipo d'utente
			if (ia==0)
				result = new Elettore(n,c,new Data(d),nn,cf);
			else
				result = new Scrutatore(n,c,new Data(d),nn,cf);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	private String processPassword(String Password) {
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
	@Override
	public void update(Utente t) {
		// TODO Auto-generated method stub
		
	}
	
}
