package models.sessione;

import java.util.List;

public class SessioneDiVoto {

	private final int numerosessione;
	private final TipoSessione tiposessione;
	private final String contenuto;
	private Boolean isOpen;
	
	public SessioneDiVoto(int numeroSessione, TipoSessione tipoSessione, String contenuto) {
		this.numerosessione = numeroSessione;
		this.tiposessione = tipoSessione;
		this.contenuto = contenuto;
		this.isOpen = true;
	}

	
	public Boolean getIsOpen() {
		return isOpen;
	}
	public int getIntIsOpen() {
		if(isOpen) {
			return 1;
		}else {
			return 0;
		}
	}
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = true;
	}

	

	public String getContenuto() {
		return contenuto;
	}


	public int getNumerosessione() {
		return numerosessione;
	}


	public TipoSessione getTiposessione() {
		return tiposessione;
	}
	
	
	
	
}
