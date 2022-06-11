package models.sessione;

public class CandidatoSemplice {

	public final String identificativo;
	
	public CandidatoSemplice(String identificativo) {
		this.identificativo = identificativo;
	}
	
	public String getidentificativo(){
		return this.identificativo;
	}

	public String identificativo() {
		return this.identificativo;
	}
	
	public String getIdentificativo() {
		return this.identificativo;
	}
	
	@Override
	public String toString() {
		return this.identificativo;
	}
}
