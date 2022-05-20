package models.voto;

public class VotoReferendum extends Voto {

	private final Boolean isFavorevole;
	Tipo tipo = Tipo.Referendum;
	
	/**
	 * Questa classe va a rappresentare l'istanza di un voto referendum
	 * Il voto in un referendum può essere vero o falso
	 * 
	 * @param numeroSessione 	il numero della sessione di voto
	 * @param isFavorevole		il valore di adesione o meno ai cambiamenti definiti nel referendum
	 */
	public VotoReferendum(int numeroSessione, int idUser, Boolean isFavorevole) {
		super(numeroSessione, idUser);
		this.isFavorevole = isFavorevole;
	}

	
	@Override
	public String getTipo() {
		return this.tipo.toString();
	}


	public Boolean getIsFavorevole() {
		return isFavorevole;
	}


	@Override
	public String getSelection() {
		return isFavorevole ? "0" : "1";
	}

}
