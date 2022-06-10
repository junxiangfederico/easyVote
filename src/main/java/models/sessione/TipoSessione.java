package models.sessione;

public enum TipoSessione {
	
	Referendum("Referendum"),
	Ordinale("Ordinale"),
	Categorico("Categorico");
	private String stringname;
    private TipoSessione(String nome) {
        this.stringname = nome;
    }
    public static TipoSessione fromString(String nome) {
    	switch(nome) {
    	case "Referendum":
    		return TipoSessione.Referendum;
    	case "Ordinale":
    		return TipoSessione.Ordinale;
    	case "Categorico":
    		return TipoSessione.Categorico;
    	default:
    		return null;
    	}
    		
    }
    @Override
    public String toString(){
        return stringname;
    }
}
