package dao.voto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseManager;
import models.sessione.SessioneDiVoto;
import models.voto.Voto;
import models.voto.VotoReferendum;
import models.voto.VotoSingolo;
import models.sessione.*;
import models.voto.VotoOrdinale;

public class IDAOVotojdbc implements IDAOVoto{



	@Override
	public boolean castReferendum(VotoReferendum voto) {
		Boolean result=null;
		String q = "INSERT INTO voto (idSession, idUser,selection) VALUES (?, ?,?)";
		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);

		try {
			p.setInt(1, voto.getSessioneDiVoto());
			p.setInt(2, voto.getIdVotante());
			System.out.println(voto.getSelection());
			p.setString(3, voto.getSelection());
			
			int count = p.executeUpdate();
			result = (count > 0);
			System.out.println(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}
	@Override
	public String getReferendumResults(int idSessione) {

		String q = "SELECT selection FROM voto where idSession = ?";

		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
		List<String> selections = new ArrayList<>();
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
	public boolean castCategorico(VotoSingolo v) {
		String q = "INSERT INTO `easyVote`.`voto` (`idSession`, `idUser`, `selection`) VALUES (?, ?, ?);";
		Boolean result=null;
		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
		
		try {	
			p.setInt(1, v.getSessioneDiVoto());
			p.setInt(2, v.getIdVotante());
			p.setString(3, v.getSelection());
			int count = p.executeUpdate();
			result = (count > 0);
		} catch (SQLException e) {
			
            e.printStackTrace();
            return false;
		}
		return result;
	}
	@Override
	public String getCategoricResults(int idSessione) {

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
		return SessioneDiVoto.getCategoricResultsByQuery(selections);
	}
	
	@Override
	public boolean castOrdinale(VotoOrdinale v) {
		String q = "INSERT INTO `easyVote`.`voto` (`idSession`, `idUser`, `selection`) VALUES (?, ?, ?);";
		Boolean result=null;
		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
		
		System.out.println(v.getSessioneDiVoto() + " " + v.getIdVotante() + v.getSelection());
		
		try {	
			p.setInt(1, v.getSessioneDiVoto());
			p.setInt(2, v.getIdVotante());
			p.setString(3, v.getSelection());
			int count = p.executeUpdate();
			result = (count > 0);
			System.out.println(result);
		} catch (SQLException e) {
	        e.printStackTrace();
	        return false;
		}
		return result;
	}
	@Override
	public List<CandidatoSemplice> getOrdinaryResults(int idSessione) {

		String q = "SELECT selection FROM voto where idSession = ?";

		PreparedStatement p = DatabaseManager.getInstance().preparaStatement(q);
		List<String> selections = new ArrayList<>();

		try {	
			p.setInt(1, idSessione);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				
				selections.add(rs.getString(1));
			}
			
		} catch (SQLException e) {
			System.out.println("Problemi con la base dati, riprovare! Context: getOrdinaryResults");
		}
		if (selections.size() == 0) { return null;}
		return SessioneDiVoto.getOrdinaryResultsByQuery(selections);
	}
	
	@Override
	public Voto get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Voto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Voto t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Voto t, Voto u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Voto t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Voto t) {
		// TODO Auto-generated method stub
		
	}

}
