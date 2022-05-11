package models.utenti;

import easyVoteproject.Data;
import easyVoteproject.Gender;

public class Elettore extends Utente{

	 public Elettore(String firstname, String lastname, Data datanascita,String nazioneNascita,String codiceFiscale) {
    	super(firstname,lastname,datanascita,nazioneNascita,codiceFiscale );
    }
    public boolean isScrutatore() {
    	return false;
    }
    public boolean isElettore() {
    	return true;
    }
        
}
