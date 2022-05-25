package dao.utenti;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import dao.IDAO;
import models.utenti.Utente;
public interface IDAOUtenti extends IDAO<Utente> {
	public int login(String username, String password) throws NoSuchAlgorithmException,SQLException; // login
	public boolean registraElettore(Utente t, String username,String pass) throws NoSuchAlgorithmException;
	//public int getId(Utente u);
	public boolean verifyPresence(String username) throws SQLException;
	public Utente UtentebyId(int i);
}
