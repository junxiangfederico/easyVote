package dao.factory;

import dao.utenti.*;
import dao.sessione.*;
public abstract class DAOFactory {
	// singleton DAOFactory
	private static DAOFactory factory = null;
	public static DAOFactory getFactory() {
		if (factory == null) 
			factory = new JDBCDAOFactory();
		return factory;
	}
	
	public abstract IDAOUtenti getUtenteDAOInstance();
	public abstract SessioneIDAO getSessioneDAOInstance();
}
