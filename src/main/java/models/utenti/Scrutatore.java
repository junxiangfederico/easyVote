package models.utenti;

public class Scrutatore extends Utente{

	public Scrutatore (String firstname, String lastname, Data datanascita,String nazioneNascita,String codiceFiscale) {
    	super(firstname,lastname,datanascita,nazioneNascita,codiceFiscale );
    }
    public boolean isScrutatore() {
    	return true;
    }
    public boolean isElettore() {
    	return false;
    }
}
