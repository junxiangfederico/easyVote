package models.voto;

import java.util.List;

public class VotoOrdinale extends Voto {
	
	private final List<String> nominativi;
	private final Tipo tipo = Tipo.Ordinale;
	
	/**
	 * 
	 * Un voto ordinale è un tipo di voto in cui l'elettore ordina in maniera decrescente i candidati preferiti
	 * 
	 * Sia una lista di ballottaggio la seguente: Federico, Angelo, Andrea, Carlo
	 * 
	 * L'elettore ha scelto di ordinare i candidati nels eguente ordine:
	 *  			Federico > Angelo > Andrea > Carlo
	 * 
	 * 
	 * @param numeroSessione	Il numero della sessione di voto
	 * @param nominativi		La lista ordinata di candidati dell'elettore
	 */
	public VotoOrdinale(int numeroSessione, int idUser, List<String> nominativi) {
		super(numeroSessione, idUser);
		this.nominativi = nominativi;
		
	}

	@Override
	public String getTipo() {
		return this.tipo.toString();
	}

	public List<String> getNominativi() {
		return nominativi;
	}

	@Override
	public String getSelection() {
		StringBuilder output = new StringBuilder("{\"selection\": \"");
		for (String s : nominativi) {
			output.append(s + "; ");
		}
		output.deleteCharAt(output.lastIndexOf(";"));
		output.append("\"}");
		return output.toString();
	}
	
	

}
