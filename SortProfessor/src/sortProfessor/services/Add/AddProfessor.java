package sortProfessor.services.Add;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import sortProfessor.services.DatabaseConnectionService;

public class AddProfessor extends DBAddService{
	private CallableStatement stmt = null;
	private String queryProc = "{? = call AddProfessor(?, ?, ?)}";
	
	public AddProfessor(DatabaseConnectionService dbcs){
		super(dbcs);
		stmt = super.generateCallableStatement(queryProc);
	}
	
	public boolean addProfessor(boolean displayOutput,String firstName, String lastName, String schoolName){
		try {
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.setString(4, schoolName);
			boolean output = super.finalizeAddStmt(displayOutput, stmt);
			return output;
	
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "duplicate professor");

			return false;
		}
	}

	@Override
	public void handleErrorCode(int code) {
		switch(code) {
		case 1:
			JOptionPane.showMessageDialog(null, "Professor first and last name cannot be null.");
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "School name cannot be null or empty.");
			break;
		case 3:
			JOptionPane.showMessageDialog(null, "School does not exist.");
		}
	}

	@Override
	public void displaySuccess() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Professor Added");
		
	}

}
