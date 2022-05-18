package models.voto;


/**
 * Classe che va a rappresentare i vari tipi che i Voti possono essere
 * Un voto in una elezione può essere Bianco, Ordinale, Referendum
 * @author nyaaaa
 *
 */
public enum Tipo {
	  Bianco{
		  @Override
		  public String toString() {
			  return "Bianco";
		  }
	  },
	  Ordinale{
		  @Override
		  public String toString() {
			  return "Ordinale";
		  }
	  },
	  Referendum{
		  @Override
		  public String toString() {
			  return "Referendum";
		  }
	  },
	  Singolo{
		  @Override
		  public String toString() {
			  return "Singolo";
		  }
	  }
	  
}
