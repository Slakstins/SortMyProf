package sortProfessor.services;

import java.sql.CallableStatement;
import java.sql.SQLException;

public abstract class DBService {
	DatabaseConnectionService dbcs = null;
	
	public DBService(DatabaseConnectionService dbcs) {
		this.dbcs = dbcs;
	}
	
	public CallableStatement generateCallableStatement(String queryCall) {
		CallableStatement stmt = null;
		try {
			stmt = dbcs.getConnection().prepareCall(queryCall);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to generate callable statement for " + queryCall);
		}
		return stmt;
		
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
