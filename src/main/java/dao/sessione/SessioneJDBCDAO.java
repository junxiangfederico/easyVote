package dao.sessione;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dao.factory.DAOFactory;
import database.DatabaseManager;
import models.sessione.*;
import models.utenti.Elettore;

public class SessioneJDBCDAO implements SessioneIDAO {
	

	
	/*public List<SessioneDiVoto> getAll(Elettore e) {
		int id = DAOFactory.getFactory().getUtenteDAOInstance().getId(e);
		String q = "select * from Sessione as S where status = 's' and not exists (select * from Votato as V where V.id_sessione = S.id and V.id_utente = ?)";
		
		List<SessioneDiVoto> result = new ArrayList<SessioneDiVoto>();
		PreparedStatement p = DBManager.getInstance().preparaStatement(q);
		try {	
			p.setInt(1, id);
			ResultSet res = p.executeQuery();
			while (res.next())
				result.add(getSessioneFromResult(res));
		} catch (SQLException sqe) {
			throw new DatabaseException("Problemi con la base dati, riprovare! Context: getAll");
		}
			
		return result;
	}*/
	
	@Override
	public SessioneDiVoto getById(int id) {
		String q = "select * from session where id = ?;";
		
		SessioneDiVoto result = null;
		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
		try {
			p.setInt(1, id);
					
			ResultSet res = p.executeQuery();
			while (res.next())
				result = getSessioneFromResult(res);
		} catch (SQLException e) {
			System.out.println("Problemi con la base dati, riprovare! Context: getById");
		}
			
		return result;
	}
	
	private SessioneDiVoto getSessioneFromResult(ResultSet res) {
		SessioneDiVoto result = null;
		int id;
		String contenuto;
		TipoSessione tipovoto;
		
		try {
			id=Integer.parseInt(res.getString(1));
			tipovoto=TipoSessione.fromString(res.getString(5));
			contenuto=res.getString(2);
			
			
			result = new SessioneDiVoto(id,tipovoto,contenuto);
		} catch (SQLException e) {
			System.out.println("Problemi con la base dati, riprovare! Context: getFromResult");
		}
		return result;
	}

	@Override
	public List<SessioneDiVoto> getAll() {
		String q = "select * from session;";
		
		
		List<SessioneDiVoto> result = new ArrayList<SessioneDiVoto>();
		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
		try {	
			ResultSet res = p.executeQuery();
				
			while (res.next())
				result.add(getSessioneFromResult(res));
		} catch (SQLException e) {
			System.out.println("Problemi con la base dati, riprovare! Context: getAll");
		}
			
		return result;
	}

	
	@Override
	public void start(SessioneDiVoto s) {
		String q = "update session set isOpen = 1 where id = ?;";

		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
		try {	
			p.setInt(1, s.getNumerosessione());
			p.execute();
		} catch (SQLException e) {
			System.out.println("Problemi con la base dati, riprovare! Context: start");
		}

	}

	@Override
	public void stop(SessioneDiVoto s) {
		String q = "update Sessione set isOpen =0 where id = ?;";
		
		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
		try {	
			p.setInt(0, s.getNumerosessione());
			p.execute();
		} catch (SQLException e) {
			System.out.println("Problemi con la base dati, riprovare! Context: stop");
		}

	}
	
	@Override
	public void save(SessioneDiVoto t) {
		String q = "insert into Sessione(id, text,candidati,isOpen,type) values (?, ?, ?, ?, ?)";
		
		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
		try {	
			p.setInt(1, t.getNumerosessione());
			p.setString(2, t.getContenuto()); 
			p.setInt(3,t.); 
			p.setString(4, t.getIsOpen());
			p.setString(5, t.getTiposessione().toString());
			p.execute();
		} catch (SQLException e) {
			System.out.println("Problemi con la base dati, riprovare! Context: start");
		}
			
	
	}
	
	@Override
	public void delete(SessioneDiVoto t) {
		int idSessione = getId(t);
		String q = "delete from Sessione where id = ?";
		
		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
		try {	
			p.setInt(1, idSessione);
			p.execute();
		} catch (SQLException e) {
			System.out.println("Problemi con la base dati, riprovare! Context: start");
		}
	}
	
	@Override
	public int getId(SessioneDiVoto s) {
		String q = "select id from Sessione where nome = ?;";
		

		int result = 0;
		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
		try {
			p.setString(1, s.getNome());
			ResultSet res = p.executeQuery();
			
			if (!res.isBeforeFirst())
				throw new SessioneNotFoundException("Sessione non esiste!");
			
			while (res.next())
				result = res.getInt(1);
		} catch (SQLException e) {
			throw new DatabaseException("Problemi con la base dati, riprovare! Context: getId");
		}
			
		return result;
	}
	
	

	
	
	@Override
	public void update(SessioneDiVoto t, SessioneDiVoto u) {
		// non serve
	}
}
