package models.sessione;

public enum TipoSessione {
	
	Referendum("Referendum"),
	SingoloCandidati("Singolo con candidati"),
	SingoloPartiti("Singolo con partiti"),
	OrdinaleCandidatiPreferenze("Ordinale con preferenze su candidati"),
	OrdinalePartitiPreferenze("Ordinale con preferenze su candidati"),
	CategoricoCandidati("Categorico con candidati"),
	CategoricoPartiti("Categorico con partiti"),
	CategoricoPreferenze("Categorico con preferenze");
	private String stringname;
    private TipoSessione(String nome) {
        this.stringname = nome;
    }
    public static TipoSessione fromString(String nome) {
    	switch(nome) {
    	case "Referendum":
    		return TipoSessione.Referendum;
    	case "Singolo con candidati":
    		return TipoSessione.SingoloCandidati;
    	case "Singolo con partiti":
			return TipoSessione.SingoloPartiti;
    	case "Ordinale con preferenze su candidati":
    		return TipoSessione.OrdinaleCandidatiPreferenze;
    	case "Ordinale con preferenze su partiti":
    		return TipoSessione.OrdinalePartitiPreferenze;
    	case "Categorico con candidati":
    		return TipoSessione.CategoricoCandidati;
    	case "Categorico con partiti":
    		return TipoSessione.CategoricoPartiti;
    	case "Categorico con preferenze":
    		return TipoSessione.CategoricoPreferenze;
    	
    	default:
    		return null;
    	}
    		
    }
    @Override
    public String toString(){
        return stringname;
    }
}
