package models.sessione;

public enum TipoSessione {
	
	Referendum("Referendum"),
	OrdinaleCandidati("Ordinale Candidati"),
	OrdinalePartiti("Ordinale Partiti"),
	CategoricoCandidati("Categorico Candidati"),
	CategoricoPartiti("Categorico Partiti"),
	CategoricoPreferenze("Categorico Preferenze");
	private String stringname;
    private TipoSessione(String nome) {
        this.stringname = nome;
    }
    public static TipoSessione fromString(String nome) {
    	switch(nome) {
    	case "Referendum":
    		return TipoSessione.Referendum;
    	case "OrdinaleCandidati":
    		return TipoSessione.OrdinaleCandidati;
    	case "OrdinalePartiti":
    		return TipoSessione.OrdinalePartiti;
    	case "CategoricoCandidati":
    		return TipoSessione.CategoricoCandidati;
    	case "CategoricoPartiti":
    		return TipoSessione.CategoricoPartiti;
    	case "CategoricoPreferenze":
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
