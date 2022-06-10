package dao.voto;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.IDAO;
import database.DatabaseManager;
import models.voto.*;
import models.sessione.*;

public interface IDAOVoto extends IDAO<Voto> {
	public boolean castReferendum(VotoReferendum voto);
	public void castCategorico(VotoSingolo v);
	public void castOrdinale(VotoOrdinale voto);
	public String getReferendumResults(int idSessione);
	public String getCategoricResults(int idSessione);
	public String getOrdinaryResults(int idSessione);
}
