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
	
	
	public abstract void handleErrorCode(int code);
	public abstract void displaySuccess();
		
}
