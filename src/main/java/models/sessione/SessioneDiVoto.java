package models.sessione;

import java.util.Collections;
import java.util.List;

public class SessioneDiVoto {

	private final int numeroSessione;
	private final TipoSessione tipoSessione;
	private Boolean isOpen;
	private final String contenuto;
	private static List<Candidato> candidati;
	/**
	 * Classe che va a rappresentare una sessione di voto.
	 * Una sessione di voto è caratterizzata dal numero univoco della sessione,
	 * dal tipo della sessione, da un attributi isOpen che rappresenta se l'inserimento di nuovi voti
	 * è ancora possibile, e dal testo del voto.
	 * @param numeroSessione 	il numero univoco della sessione
	 * @param tipoSessione		il tipo della sessione di voto
	 * @param contenuto			il testo della sessione di voto
	 */
	public SessioneDiVoto(int numeroSessione, TipoSessione tipoSessione, boolean isOpen, String contenuto, List<Candidato> candidati) {
		this.numeroSessione = numeroSessione;
		this.tipoSessione = tipoSessione;
		this.contenuto = contenuto;
		this.isOpen = isOpen;
		this.candidati = candidati;	
	}


	
	
	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}

	public int getNumeroSessione() {
		return numeroSessione;
	}

	public TipoSessione getTipoSessione() {
		return tipoSessione;
	}

	public String getContenuto() {
		return contenuto;
	}


	public static void addCandidato(Candidato candidato) {
			candidati.add(candidato);
	}






	public int querygetIsOpen() {
		return isOpen ? 0 : 1;
	}






	/**
	 * '{"candidato1": "fede", "candidato2": "angelo", "candidato3": "marco"}'
	 * @return
	 */
	public String querygetCandidati() {
		if (candidati.size() == 0) {
			return "{}";
		}
		
		
		StringBuilder s = new StringBuilder("{");
		for (int i = 1; i < candidati.size() + 1; i++) {
			s.append("\"candidato" + i + "\": \"");
			s.append(candidati.get(i-1).getIdentificativo());
			s.append("\", ");
		}
		s.deleteCharAt(s.lastIndexOf(","));
		s.append("}");
		return s.toString();
		
	}

	public Boolean removeCandidato(String s){
		for (Candidato c : candidati) {
			if (c.getidentificativo().equals(s)) {
				candidati.remove(c);
				return true;
			}
		}
		return false;
	}
	
	
	
	public List<Candidato> getCandidati() {
		return Collections.unmodifiableList(this.candidati);
	}
	
	
	
}
