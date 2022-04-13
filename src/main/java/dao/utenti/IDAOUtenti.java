package dao.utenti;
import java.security.NoSuchAlgorithmException;

import dao.IDAO;
import models.utenti.Utente;
public interface IDAOUtenti extends IDAO<Utente> {
	public boolean login(String username, String password) throws NoSuchAlgorithmException; // login
	public boolean registraElettore(Utente t, String pass);
	public int getId(Utente u);
}
