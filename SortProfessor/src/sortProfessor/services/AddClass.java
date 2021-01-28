package sortProfessor.services;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;



//NOTE: this will have to be added at the same time as a professor
public class AddClass extends DBAddService{
	private CallableStatement stmt = null;
	private String queryProc = "{? = call AddClass(?)}";
	
	public AddClass(DatabaseConnectionService dbcs){
		super(dbcs);
		stmt = super.generateCallableStatement(queryProc);
	}
	
	public boolean addClass(String className){
		try {
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, className);
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
		System.out.println("Class Added");
	}
}
