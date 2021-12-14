package easyVoteproject;
public abstract class Utente {
    
    private final int id;
    private final String firstname;
    private  final String lastname; 
    private /*@ spec_public @*/ final Data datanascita;
    private /*@ spec_public @*/ final String comuneNascita;
    private /*@ spec_public @*/final Gender sesso;
    private /*@ spec_public @*/ final String nazioneNascita;
    private /*@ spec_public @*/final CodiceFiscale codiceFiscale;
    
    public Utente(int id,   String firstname, String lastname,Data datanascita, String comuneNascita, String sesso, String nazioneNascita, CodiceFiscale codiceFiscale){
    	this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.datanascita = datanascita;
        this.comuneNascita = comuneNascita;
        this.sesso = Gender.fromString(sesso);
        this.nazioneNascita = nazioneNascita;
        this.codiceFiscale = codiceFiscale;
       
    }
    
    public int getId() {
        return id;
    }
    
    public String getfirstname() {
        return firstname;
    }

    public String getlastname() {
        return lastname;
    }
   
    public CodiceFiscale getCodiceFiscale() {
        return codiceFiscale;
    }

    public String getComuneNascita() {
        return comuneNascita;
    }

    public String getNazioneNascita() {
        return nazioneNascita;
    }

    public Gender getSesso() {
        return sesso;
    }

    public /*@ pure @*/ Data getDatanascita() {
        return datanascita;
    }
}
