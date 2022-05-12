package models.sessione;


/**
 * Classe che va a rappresentare un candidato
 * Un'istanza candidato è caratterizzato dal suo tipo, che identifica se il candidato è una persona fisica, partito, o altra forma
 * e dal suo identificativo
 * @author nyaaaa
 *
 */
public class Candidato extends Partecipante{
	public final String identificativo;
	
	public Candidato(TipoPartecipante tipo, String identificativo) {
		super(tipo);
		this.identificativo = identificativo;
	}
	
	public String getidentificativo(){
		return this.identificativo;
	}

}