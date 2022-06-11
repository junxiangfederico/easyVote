package dao.sessione;

import dao.IDAO;
import models.sessione.*;
import models.utenti.Elettore;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface SessioneIDAO extends IDAO<SessioneDiVoto> {
	public int getId(SessioneDiVoto s);
	public SessioneDiVoto getById(int id) throws JsonParseException, JsonMappingException, IOException;
	public void start(SessioneDiVoto s);
	public void stop(SessioneDiVoto s);
	public List<SessioneDiVoto> getAll(Elettore e);
	public int aggiungi(String text,String type);
	public List<CandidatoSemplice> getResults(int idSessione);

}
