package dao.sessione;

import dao.IDAO;
import models.sessione.*;
import models.utenti.Elettore;

import java.util.List;

public interface SessioneIDAO extends IDAO<SessioneDiVoto> {
	public int getId(SessioneDiVoto s);
	public SessioneDiVoto getById(int id);
	public void start(SessioneDiVoto s);
	public void stop(SessioneDiVoto s);
	public List<SessioneDiVoto> getAll(Elettore e);
}
