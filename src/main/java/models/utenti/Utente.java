package models.utenti;
import easyVoteproject.*;
public abstract class Utente {
    
    private final String firstname;
    private  final String lastname; 
    private /*@ spec_public @*/ final Data datanascita;
    private /*@ spec_public @*/ final String nazioneNascita;
    
    private /*@ spec_public @*/final /*CodiceFiscale*/ String codiceFiscale;
    
    public Utente(String firstname, String lastname, Data datanascita,String nazioneNascita,String codiceFiscale){
        this.firstname = firstname;
        this.lastname = lastname;
        this.datanascita = datanascita;
        this.nazioneNascita = nazioneNascita;
        this.codiceFiscale = codiceFiscale;
       
    }

    
    public String getfirstname() {
        return firstname;
    }

    public String getlastname() {
        return lastname;
    }
    public String getData() {
        return datanascita.toString();
    }
    public String getnazioneNascita() {
        return	nazioneNascita;
    }
    public String getcf() {
        return codiceFiscale;
    }

  

    public abstract boolean isScrutatore();
	
	public abstract boolean isElettore();
}
