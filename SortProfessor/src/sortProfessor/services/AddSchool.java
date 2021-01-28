package sortProfessor.services;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class AddSchool extends DBService{
	private CallableStatement stmt = null;
	private String queryProc = "{? = call AddSchool(?)}";
	
	public AddSchool(DatabaseConnectionService dbcs){
		super(dbcs);
		stmt = super.generateCallableStatement(queryProc);
	}
	
	public boolean addSchool(String schoolName){
		try {
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, schoolName);
			return super.finalizeAddStmt(stmt);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	@Override
	public void handleErrorCode(int code) {
		switch(code) {
		case 1:
			System.out.println("Whoops");
			break;
		}
	}

	@Override
	public void displaySuccess() {
		// TODO Auto-generated method stub
		System.out.println("School Added");
	}
}
