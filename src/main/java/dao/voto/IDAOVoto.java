package dao.voto;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import dao.IDAO;
public interface IDAOVoto extends IDAO<VotoSingolo> {
	
	public boolean castVote(int idSession, int idUser, String contenuto);
}
