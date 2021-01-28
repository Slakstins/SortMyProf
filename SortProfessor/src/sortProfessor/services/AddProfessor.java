package sortProfessor.services;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class AddProfessor extends DBAddService{
	private CallableStatement stmt = null;
	private String queryProc = "{? = call AddProfessor(?, ?)}";
	
	public AddProfessor(DatabaseConnectionService dbcs){
		super(dbcs);
		stmt = super.generateCallableStatement(queryProc);
	}
	
	public boolean addProfessor(String firstName, String lastName){
		try {
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			return super.finalizeAddStmt(stmt);
	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		System.out.println("Professor Added");
		
	}

}
