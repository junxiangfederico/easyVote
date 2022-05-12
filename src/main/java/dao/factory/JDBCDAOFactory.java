package dao.factory;

import dao.utenti.*;
import dao.sessione.*;

public class JDBCDAOFactory extends DAOFactory {
	private static IDAOUtenti daoUtente = null;
	private static SessioneIDAO daoSessione = null;

	public IDAOUtenti getUtenteDAOInstance() {
		if (daoUtente == null)
			daoUtente = new IDAOUtentijdbc();
		return daoUtente;
	}
	@Override
	public SessioneIDAO getSessioneDAOInstance() {
		if (daoSessione == null) 
			daoSessione = new SessioneJDBCDAO();
		return daoSessione;
	}
}
