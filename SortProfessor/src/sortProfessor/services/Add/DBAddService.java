package sortProfessor.services.Add;

import java.sql.CallableStatement;
import java.sql.SQLException;

import sortProfessor.services.DBService;
import sortProfessor.services.DatabaseConnectionService;

public abstract class DBAddService extends DBService{
	
	public DBAddService(DatabaseConnectionService dbcs) {
		super(dbcs);
	}

	/*
	 * code: returned by stmt.getInt(1)
	 */
	public abstract void handleErrorCode(int code);
	public abstract void displaySuccess();
	
	/*
	 * execute the statement and handle the error
	 * OVERRIDE THIS FOR THOSE THAT RETURN TABLES
	 */
	public boolean finalizeAddStmt(boolean displaySuccess, CallableStatement stmt) throws SQLException {
			stmt.execute();
			int code = stmt.getInt(1);
			if (code != 0) {
				System.out.println("SQL return code: " + code);
				handleErrorCode(code);
				return false;
			} else {
				if (displaySuccess) {
					
					displaySuccess();
				}
				return true;
			}
	}
	

}
