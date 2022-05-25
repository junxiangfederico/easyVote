package dao.voto;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import dao.IDAO;
import models.voto.*;

public interface IDAOVoto extends IDAO<Voto> {
	
	public boolean castReferendum(VotoReferendum voto);
}
