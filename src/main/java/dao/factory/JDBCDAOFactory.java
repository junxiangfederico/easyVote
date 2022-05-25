package dao.factory;

import dao.utenti.*;
import dao.sessione.*;
import dao.voto.*;

public class JDBCDAOFactory extends DAOFactory {
	private static IDAOUtenti daoUtente = null;
	private static SessioneIDAO daoSessione = null;
	private static IDAOVoto daoVoto = null;
	
	@Override
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
	@Override
	public IDAOVoto getVotoDAOInstance() {
		if (daoVoto == null) 
			daoVoto = new IDAOVotojdbc();
		return daoVoto;
	}
}
