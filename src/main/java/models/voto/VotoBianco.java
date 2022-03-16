package models.voto;

public class VotoBianco extends Voto {

	Boolean isBianco;
	
	public VotoBianco(int numeroSessione, Boolean isBianco) {
		super(numeroSessione);
		this.isBianco = isBianco;
	}

	
	
	@Override
	public String getTipo() {
		return "Voto Bianco";
	}

}
