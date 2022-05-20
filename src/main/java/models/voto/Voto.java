package models.voto;

public abstract class Voto {
	private final int sessioneDiVoto;
	private final int idVotante;
	
	public Voto(int numeroSessione, int idVotante) {
		sessioneDiVoto = numeroSessione;
		this.idVotante = idVotante;
	}

	public int getIdVotante(){
		return this.idVotante;
	}
	
	public int getSessioneDiVoto() {
		return sessioneDiVoto;
	}
	
	public abstract String getTipo();
	
	public abstract String getSelection();
	
}
