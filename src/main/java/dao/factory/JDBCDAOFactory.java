package dao.factory;

import dao.utenti.*;

public class JDBCDAOFactory extends DAOFactory {
	private static IDAOUtenti daoUtente = null;

	public IDAOUtenti getUtenteDAOInstance() {
		if (daoUtente == null)
			daoUtente = new IDAOUtentijdbc();
		return daoUtente;
	}
}
