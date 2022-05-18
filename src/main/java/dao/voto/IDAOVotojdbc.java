package dao.voto;

import java.util.List;

import models.voto.Voto;
import models.voto.VotoSingolo;

public class IDAOVotojdbc implements IDAOVoto{

	@Override
	public VotoSingolo get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VotoSingolo> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(VotoSingolo t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(VotoSingolo t, VotoSingolo u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(VotoSingolo t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(VotoSingolo t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean castVote(int idSession, int idUser, String contenuto) {
		// TODO Auto-generated method stub
		return false;
	}
}
