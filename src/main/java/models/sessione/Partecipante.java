package models.sessione;

public abstract class Partecipante {
	private final TipoPartecipante tipo;
	
	public Partecipante(TipoPartecipante tipo) {
		this.tipo = tipo;
	}

	public enum TipoPartecipante{
		Partito{},
		Indipendente{},
		Persona{}
	}
}
