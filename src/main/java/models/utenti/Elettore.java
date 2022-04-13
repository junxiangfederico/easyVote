package models.utenti;



public class Elettore extends Utente{

    public Elettore (int id,   String firstname, String lastname, String codiceFiscale /*Gender sesso */) {
    	super(id,firstname,lastname,codiceFiscale);
    }
    public boolean isScrutatore() {
    	return false;
    }
    public boolean isElettore() {
    	return true;
    }
        
}
