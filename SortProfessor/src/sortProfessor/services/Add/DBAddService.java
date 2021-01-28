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
	public boolean finalizeAddStmt(CallableStatement stmt) {
		try {
			stmt.execute();
			int code = stmt.getInt(1);
			if (code != 0) {
				System.out.println("Nonzero SQL return code");
				handleErrorCode(code);
				return false;
			} else {
				displaySuccess();
				return true;
			}
			
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	

}
