package models.voto;

public class VotoBianco extends Voto {

	private final Boolean isBianco;
	private final Tipo tipo = Tipo.Bianco;
	
	/**
	 * Istanzia un elemento di tipo VotoBianco
	 * Un voto bianco è un voto il cui contenuto è nullo
	 * 
	 * @param numeroSessione 	il numero della sessione di voto
	 * @param isBianco			Booleano che indica se il voto è bianco o meno
	 */
	public VotoBianco(int numeroSessione, int idVotante, Boolean isBianco) {
		super(numeroSessione, idVotante);
		this.isBianco = isBianco;
		
	}
	
	@Override
	public String getTipo() {
		return this.tipo.toString();
	}

}
