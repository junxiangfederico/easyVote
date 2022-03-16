package models.voto;

public abstract class Voto {
	private final int sessioneDiVoto;
	
	
	public Voto(int numeroSessione) {
		sessioneDiVoto = numeroSessione;
	}

	
	public int getSessioneDiVoto() {
		return sessioneDiVoto;
	}
	
	public abstract String getTipo();
	
}
