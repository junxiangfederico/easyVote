package models.utenti;

public class Scrutatore extends Utente{

	public Scrutatore (int id,   String firstname, String lastname, String codiceFiscale /*Gender sesso */) {
    	super(id,firstname,lastname,codiceFiscale);
    }
    public boolean isScrutatore() {
    	return true;
    }
    public boolean isElettore() {
    	return false;
    }
}
