package sortProfessor.services.Add;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

import sortProfessor.services.DatabaseConnectionService;

public class AddSchool extends DBAddService{
	private CallableStatement stmt = null;
	private String queryProc = "{? = call AddSchool(?)}";
	
	public AddSchool(DatabaseConnectionService dbcs){
		super(dbcs);
		stmt = super.generateCallableStatement(queryProc);
	}
	
	public boolean addSchool(boolean displayOutput,String schoolName){
		try {
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, schoolName);
			return super.finalizeAddStmt(displayOutput, stmt);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "That school already exists");
			return false;
		}
	}

	@Override
	public void handleErrorCode(int code) {
		switch(code) {
		case 1:
			JOptionPane.showMessageDialog(null, "School name cannot be null or empty");
			break;
		}
	}

	@Override
	public void displaySuccess() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "School Added");
	}
}
