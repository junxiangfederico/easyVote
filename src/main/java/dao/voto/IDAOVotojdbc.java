package dao.voto;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import database.DatabaseManager;
import models.voto.Voto;
import models.voto.VotoReferendum;
import models.voto.VotoSingolo;

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
			
			int i = p.executeUpdate();
			if(i>0) {
				result=true;
			}else {
				result=false;
			}
			System.out.println(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
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
