package models.sessione;

public enum TipoSessione {
	
	Referendum{},
	/*
	 * ordinale con preferenza da 1 a n
	 * 
	 */
	OrdinaleCandidati{},
	OrdinalePartiti{},
	/**
	 * Unico voto
	 */
	CategoricoCandidati{},
	CategoricoPartiti{},
	CategoricoPreferenze{}
	
}
