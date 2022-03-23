package database;

import java.sql.*;
public class DatabaseManager {
		private static DatabaseManager instance;
		private static String url = "jdbc:mysql://localhost/easyvote";
		private static String username = "prova";
		private static String password = "";
		private Connection con;
		
		private DatabaseManager() {}
		
		public static DatabaseManager getInstance() {
			if (instance == null) {
				instance = new DatabaseManager();
			}
			return instance;
		}
		
		/***
		 * Apre la connessione con la base dati.
		 * @return true se l'apertura e avvenuta con successo, false altrimenti.
		 */
		public boolean open() {
			try {
				con = DriverManager.getConnection(url,username,password);
				return true;
			} catch(SQLException e) {
				return false;
			}
		}
		
		/***
		 * Chiude la connessione con la base dati.
		 * @return true se la chiusura e avvenuta con successo, false altrimenti.
		 */
		public boolean close() {
			try {
				con.close();
				return true;
			} catch (SQLException e) {
				return false;
			}
		}

		// metodo utile
		public PreparedStatement preparaStatement(String q) {
			PreparedStatement result = null;
			try {
				result = con.prepareStatement(q);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
		}
	}

