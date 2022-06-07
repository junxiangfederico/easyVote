package dao.sessione;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import database.DatabaseManager;
import models.sessione.*;
import models.sessione.Partecipante.TipoPartecipante;
import models.utenti.Elettore;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		int id,isopen;
		String contenuto;
		TipoSessione tipovoto;
		
		try {
			id=Integer.parseInt(res.getString(1));
			contenuto=res.getString(2);
			isopen=Integer.parseInt(res.getString(4));
			tipovoto=TipoSessione.fromString(res.getString(5));	
			//System.out.println(tipovoto);
			boolean io = (isopen==1);
			List<Candidato> candidati=SessioneDiVoto.jsontolist(res.getString(3),null);
			result = new SessioneDiVoto(id,tipovoto,io, contenuto, candidati);
			
		} catch (SQLException e) {
			System.out.println("Problemi con la base dati, riprovare! Context: getFromResult");
		}
		return result;
	}
	
	
	

	@Override
	public List<Candidato> getResults(int idSessione) {
		String q = "SELECT selection FROM voto where idSession = ?";
		
		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
		List<String> selections = new ArrayList<>();
		//System.out.println("91" + idSessione);
		try {	
			p.setInt(1, idSessione);
			ResultSet rs = p.executeQuery();
			if(rs.next()) {
				selections.add(rs.getString(1));
			}
			
		} catch (SQLException e) {
			System.out.println("Problemi con la base dati, riprovare! Context: getAll");
		}
		if (selections.size() == 0) { return null;}
		return SessioneDiVoto.getResultsByQuery(selections);
	}
	

	@Override
	public Candidato getOrdinaryResults(int idSessione) {

		String q = "SELECT selection FROM voto where idSession = ?";

		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
		List<String> selections = new ArrayList<>();
		//System.out.println("91" + idSessione);
		try {	
			p.setInt(1, idSessione);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				selections.add(rs.getString(1));
			}
			
		} catch (SQLException e) {
			System.out.println("Problemi con la base dati, riprovare! Context: getAll");
		}
		if (selections.size() == 0) { return null;}
		return SessioneDiVoto.getOrdinaryResultsByQuery(selections);
	}
	
	@Override
	public String getReferendumResults(int idSessione) {

		String q = "SELECT selection FROM voto where idSession = ?";

		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
		List<String> selections = new ArrayList<>();
		//System.out.println("91" + idSessione);
		try {	
			p.setInt(1, idSessione);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				selections.add(rs.getString(1));
			}
			
		} catch (SQLException e) {
			System.out.println("Problemi con la base dati, riprovare! Context: getAll");
		}
		if (selections.size() == 0) { return null;}
		return SessioneDiVoto.getReferendumResultsByQuery(selections);
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
			p.setInt(1, s.getNumeroSessione());
			p.execute();
		} catch (SQLException e) {
			System.out.println("Problemi con la base dati, riprovare! Context: start");
		}

	}

	@Override
	public void stop(SessioneDiVoto s) {
		String q = "update session set isOpen =0 where id = ?;";
		
		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
		try {	
			p.setInt(0, s.getNumeroSessione());
			p.execute();
		} catch (SQLException e) {
			System.out.println("Problemi con la base dati, riprovare! Context: stop");
		}

	}
	@Override
	public int aggiungi(String text,String type) {
		String q = "INSERT INTO session (text, type) VALUES (?, ?)";
		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);

		try {
			if (text.isBlank() || text.isEmpty()) {
			      p.setString(1,"");
		       }else {
		    	  p.setString(1,text);
		       }
			  p.setString(2,type);
			
			p.execute();
			System.out.println("hello world");
			int value=getValue();
			return value;
		} catch (SQLException e) {
			System.out.println("Problemi con la base dati, riprovare! Context: aggiungi");
			/*System.out.println("SQLExeption: "+ex.getMessage());
			System.out.println("SQLState: "+ex.getSQLState());
			System.out.println("VendorError: "+ ex.getErrorCode());*/
		}
		return 0;
	}
	@Override
	public void update(SessioneDiVoto t) {
		String q= "UPDATE `easyVote`.`session` SET `candidati` = ?, `isOpen` = ? WHERE `id` = ?";
		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
		try{
			p.setString(1, t.querygetCandidati());
		    p.setInt(2, t.querygetIsOpen());
		    p.setString(3, "" + t.getNumeroSessione());
		    p.execute();
		} catch (SQLException e) {
			System.out.println("Problemi con la base dati, riprovare! Context:update");
		}

	}
	private int getValue() throws SQLException {
		String q2 = "SELECT * FROM session ORDER BY id DESC LIMIT 1;";
		PreparedStatement p2 = DatabaseManager.getInstance().preparaStatement(q2);
   	 	ResultSet rs = p2.executeQuery();
		rs.next();
		
		return Integer.parseInt(rs.getString(1));
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
			System.out.println("Problemi con la base dati, riprovare! Context: delete");
		}
	}
	

	
	

	@Override
	public SessioneDiVoto get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getId(SessioneDiVoto s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SessioneDiVoto> getAll(Elettore e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(SessioneDiVoto t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SessioneDiVoto t, SessioneDiVoto u) {
		// TODO Auto-generated method stub
		
	}


	

}
