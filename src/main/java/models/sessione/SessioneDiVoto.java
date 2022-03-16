package models.sessione;

public class SessioneDiVoto {

	private final int numeroSessione;
	private final TipoSessione tipoSessione;
	private Boolean isOpen;
	private final String contenuto;
	
	/**
	 * Classe che va a rappresentare una sessione di voto.
	 * Una sessione di voto è caratterizzata dal numero univoco della sessione,
	 * dal tipo della sessione, da un attributi isOpen che rappresenta se l'inserimento di nuovi voti
	 * è ancora possibile, e dal testo del voto.
	 * @param numeroSessione 	il numero univoco della sessione
	 * @param tipoSessione		il tipo della sessione di voto
	 * @param contenuto			il testo della sessione di voto
	 */
	public SessioneDiVoto(int numeroSessione, TipoSessione tipoSessione, String contenuto ) {
		this.numeroSessione = numeroSessione;
		this.tipoSessione = tipoSessione;
		this.contenuto = contenuto;
		this.isOpen = true;	
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
	
	
	
	
}
