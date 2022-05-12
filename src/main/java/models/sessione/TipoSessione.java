package models.sessione;

public enum TipoSessione {
	
	Referendum("Referendum"),
	OrdinaleCandidati("Ordinale con candidati"),
	OrdinalePartiti("Ordinale con partiti"),
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
    	case "Ordinale con candidati":
    		return TipoSessione.OrdinaleCandidati;
    	case "Ordinale con partiti":
    		return TipoSessione.OrdinalePartiti;
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
