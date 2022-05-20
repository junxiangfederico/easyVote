package models.voto;

import java.util.List;

public class VotoSingolo extends Voto {


	private final String nomeCandidato;
	private final Tipo tipo = Tipo.Ordinale;


	public VotoSingolo(int numeroSessione, int idVotante, String nomeCandidato) {
		super(numeroSessione, idVotante);
		this.nomeCandidato = nomeCandidato;
	}

	@Override
	public String getTipo() {
		return this.tipo.toString();
	}

	public String getNomeCandidato() {
		return nomeCandidato;
	}


	//INSERT INTO `easyVote`.`voto` (`idSession`, `idUser`, `selection`) VALUES (61, 22, '{\"selection\": \"federico\"}');
	@Override
	public String getSelection() {
		return "{\"selection\": \"" + this.nomeCandidato + "\"}";
	}

	
}
