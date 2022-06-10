package models.sessione;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import models.sessione.Partecipante.TipoPartecipante;
import models.sessione.Candidato;

public class SessioneDiVoto {

	private final int numeroSessione;
	private final TipoSessione tipoSessione;
	private Boolean isOpen;
	private final String contenuto;
	private static List<Candidato> candidati;
	/**
	 * Classe che va a rappresentare una sessione di voto.
	 * Una sessione di voto è caratterizzata dal numero univoco della sessione,
	 * dal tipo della sessione, da un attributi isOpen che rappresenta se l'inserimento di nuovi voti
	 * è ancora possibile, e dal testo del voto.
	 * @param numeroSessione 	il numero univoco della sessione
	 * @param tipoSessione		il tipo della sessione di voto
	 * @param contenuto			il testo della sessione di voto
	 */
	public SessioneDiVoto(int numeroSessione, TipoSessione tipoSessione, boolean isOpen, String contenuto, List<Candidato> candidati) {
		this.numeroSessione = numeroSessione;
		this.tipoSessione = tipoSessione;
		this.contenuto = contenuto;
		this.isOpen = isOpen;
		SessioneDiVoto.candidati = candidati;	
	}


	
	
	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}

	public int getNumeroSessione() {
		return numeroSessione;
	}

	public TipoSessione getTipoSessione() {
		return tipoSessione;
	}

	public String getContenuto() {
		return contenuto;
	}

	public static void addCandidato(Candidato candidato) {
			candidati.add(candidato);
	}





	/**
	 * 1 aperto, 0 chiuso
	 * @return
	 */
	public int querygetIsOpen() {
		System.out.println(isOpen);
		return isOpen ? 1 : 0;
	}






	/**
	 * '{"candidato1": "fede", "candidato2": "angelo", "candidato3": "marco"}'
	 * @return
	 */
	public String querygetCandidati() {
		if (candidati.size() == 0) {
			return "{}";
		}
		
		
		StringBuilder s = new StringBuilder("{");
		for (int i = 1; i < candidati.size() + 1; i++) {
			s.append("\"candidato" + i + "\": \"");
			s.append(candidati.get(i-1).getidentificativo());
			s.append("\", ");
		}
		s.deleteCharAt(s.lastIndexOf(","));
		s.append("}");
		return s.toString();
		
	}

	public Boolean removeCandidato(String s){
		for (Candidato c : candidati) {
			if (c.getidentificativo().equals(s)) {
				candidati.remove(c);
				return true;
			}
		}
		return false;
	}
	
	public static List<Candidato> jsontolist(String string,TipoPartecipante tipo){
		List<Candidato> lista=new ArrayList<>();
		if (string==null) {
			return lista;
		}
		
		string=string.substring(1,string.length()-1);
		string = string.replaceAll("\\s+", "");
	    string = string.replaceAll("\"", "");
		String [] parts = string.split(",");
		if(!(string.equals(""))) {
				for(String part:parts) {
					String [] parts2 = part.split(":");
					part=parts2[1];
					Candidato candidato=new Candidato(tipo,part);
					lista.add(candidato);
				}
		}
		return lista;
	}
	
	
	public List<Candidato> getCandidati() {
		return Collections.unmodifiableList(SessioneDiVoto.candidati);
	}



	public static List<Candidato> getResultsByQuery(List<String> selections){
		if (selections.size() == 0) return null;
		Map<String, Integer> results = new HashMap<>();
		for (String s : selections) {
			String[] current = s.split(":");
			String b = current[1].substring(2, current[1].length()-2);
			String[] a = b.split(";");
			for (String aa : a) {
				aa = aa.strip();
				
				if (results.containsKey(aa)) {
					results.put(aa, results.get(aa)+1);
				}else {
					results.put(aa, 1);
				}
			}
			
			for (String bb : results.keySet()) {
				System.out.println(bb + " " + results.get(bb)); 
			}
			
		}
		return null;
		
	}




	public static String getCategoricResultsByQuery(List<String> selections) {
		if (selections.size() == 0) return null;
		Map<String, Integer> results = new TreeMap<>();
		for (String s : selections) {
			String[] current = s.split(":");
			String b = current[1].substring(2, current[1].length()-2);
			System.out.println(s);
				if (results.containsKey(b)) {
					results.put(b, results.get(b)+1);
				}else {
					results.put(b, 1);
				}
			
		}
		int highest = 0;
		String winner = "";
		for (String ss : results.keySet()) {
			if (results.get(ss) > highest) {
				highest = results.get(ss);
				winner = ss;
			}
		}
	
		TipoPartecipante t = TipoPartecipante.Persona;
		Candidato c = new Candidato(t, winner);
		return "il vincitore e': "+c.getidentificativo();
	}




	public static String getReferendumResultsByQuery(List<String> selections) {
		int favorevoli = 0;
		int contro = 0;
		int bianche = 0;
		for (String s : selections) {
			String[] current = s.split(":");
			if (current[1].charAt(2) == '0') {
				contro++;
			}else if (current[1].charAt(2) == '1') {
				favorevoli++;
			}else {
				bianche++;
			}
		}
		if (favorevoli > contro) {
			return "Il referendum e' approvato, i favorevoli sono maggiori dei contro.";
		}
		return "Il referendum non e' approvato, contro sono maggiori del favorevoli.";
	}
	
	
	/**
	 * 
	 * Prima posizione: 4
	 * seconda posizione 3
	 * terza posizione 2
	 * quarta posizione 1
	 * 
	 * @param selections
	 * @return
	 */
	public static List<Candidato> getOrdinaryResultsByQuery(List<String> selections) {
		if (selections.size() == 0) return null;

		int i = 4;
		Map<String, Integer> results = new HashMap<>();
		for (String s : selections) {
			System.out.println(i);
			//System.out.println(s);
			String[] current = s.split(":");
			String b = current[1].substring(2, current[1].length()-2);
			//System.out.println(b);
			
			String[] aaa = b.split(";");
			for (String names : aaa) {
				if (results.containsKey(names.trim())) {
					results.put(names.trim(), (results.get(names.trim())+1) * i);
				}else {
					results.put(names.trim(), 1 * i);
				}
				i--;
			}
		}
		int highest = 0;
		String winner = "";
		for (String ss : results.keySet()) {
			System.out.println(ss + " " + results.get(ss));
			if (results.get(ss) > highest) {
				highest = results.get(ss);
				winner = ss;
			}
		}
		
		LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
		 
		//Use Comparator.reverseOrder() for reverse ordering
		results.entrySet()
		  .stream()
		  .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
		  .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

		TipoPartecipante t = TipoPartecipante.Persona;
		List<Candidato> output = new ArrayList<>();
		for (String s : reverseSortedMap.keySet()) {
			output.add(new Candidato(t, s));
		}
		
		
	
		return output;
}
}




































