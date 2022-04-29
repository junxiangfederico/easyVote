package models.sessione;

public abstract class Partecipante {
	private final TipoPartecipante tipo;
	
	public Partecipante(TipoPartecipante tipo) {
		this.tipo = tipo;
	}

	
	public String getTipo(){
		return this.tipo.toString();
	}
	
	public enum TipoPartecipante{
		Partito{
			@Override
			public String toString(){
				return "Partito";
			}
			
		},
		Indipendente{
			@Override
			public String toString(){
				return "Indipendente";
			}
		},
		Persona{
			@Override
			public String toString(){
				return "Persona";
			}
		}
	}
}
