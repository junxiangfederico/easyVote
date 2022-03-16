package models.voto;

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
	  }
	  
}
