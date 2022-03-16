package models.sessione;

public enum TipoSessione {
	
	Referendum{
		int numeroCandidati = 0;
	},
	OrdinaleCandidati{},
	OrdinalePartiti{},
	CategoricoCandidati{},
	CategoricoPartiti{},
	CategoricoPreferenze{}
	
}
