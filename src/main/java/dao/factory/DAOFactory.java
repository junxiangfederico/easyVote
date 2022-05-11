package dao.factory;

import dao.utenti.*;

public abstract class DAOFactory {
	// singleton DAOFactory
	private static DAOFactory factory = null;
	public static DAOFactory getFactory() {
		if (factory == null) 
			factory = new JDBCDAOFactory();
		return factory;
	}
	
	public abstract IDAOUtenti getUtenteDAOInstance();
	
}
