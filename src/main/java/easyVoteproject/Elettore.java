package easyVoteproject;


import java.util.Calendar;


public class Elettore extends Utente{

    /**
     * regione e classi sono mutabili in quanto un utente puÃ² trasferirsi/cambiare residenza
     */
    private String regione;
    private String residenza;
    private /*@ spec_public @*/ boolean Vote;
    //@requires sesso=="m"||sesso=="f";
    //@requires firstname!="" && lastname!="";
    //@requires age(datanascita)>=0;
    //@requires CodiceFiscale.checkValidity(codiceFiscale,lastname,firstname,datanascita,Gender.fromString(sesso),nazioneNascita)==true;
    //@ensures nazioneNascita=="ITA"==>comuneNascita!="";
    public Elettore(int id, String firstname,  String lastname, Data datanascita, String comuneNascita, String sesso,
            String nazioneNascita, CodiceFiscale codiceFiscale) {
        super(id, firstname, lastname, datanascita, comuneNascita, sesso, nazioneNascita, codiceFiscale);
        this.Vote=false;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    public String getRegione() {
        if (regione != null) return regione;
        else return "La regione non Ã¨ ancora stata impostata";
    }

    public String getResidenza() {
        if (residenza != null) return residenza;
        else return "La regione non Ã¨ ancora stata impostata";
    }
    public boolean getVote() {
        return Vote;
    }
    public static /*@ pure @*/ int getAge(Data nascita) {
    	Calendar date = Calendar.getInstance();
    	int a =date.get(Calendar.YEAR) - nascita.getAnno()  ;
    	if ((nascita.getMese()> date.get(Calendar.MONTH)) || 
    			(((nascita.getMese()== date.get(Calendar.MONTH))) && (nascita.getGiorno()> date.get(Calendar.DAY_OF_MONTH)))) {
    	        a--;
    	}
    	return a;

    }

    public boolean checkMaggiorenne(){
    	int age=getAge(this.getDatanascita());
    	if (age>=18)
    		return true;
    	else 
    		return false;
    }
    //@requires age(this.getDatanascita())>=18;
    //@requires this.Vote==false;

    public boolean castVote() {
    		this.Vote=true;
    		return true;
    		
    }     
        
}
