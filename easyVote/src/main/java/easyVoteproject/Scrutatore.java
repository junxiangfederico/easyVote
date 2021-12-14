package easyVoteproject;
import java.util.ArrayList;
import java.util.List;

public class Scrutatore extends Utente{

	public Scrutatore(int id, String firstname, String lastname, Data datanascita, String comuneNascita, String sesso,
			String nazioneNascita, CodiceFiscale codiceFiscale) {
		super(id, firstname, lastname, datanascita, comuneNascita, sesso, nazioneNascita, codiceFiscale);
		idElezione = new ArrayList<Integer>();
	}

	private List<Integer> idElezione;


	private void addElezione(int idElezione){
		this.idElezione.add(idElezione);
	}

	public List<Integer> getElezioni() {
		return idElezione;
	}
}
