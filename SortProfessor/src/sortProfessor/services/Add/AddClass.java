package sortProfessor.services.Add;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

import sortProfessor.services.DatabaseConnectionService;



//NOTE: this will have to be added at the same time as a professor
public class AddClass extends DBAddService{
	private CallableStatement stmt = null;
	private String queryProc = "{? = call AddClass(?, ?)}";
	
	public AddClass(DatabaseConnectionService dbcs){
		super(dbcs);
		stmt = super.generateCallableStatement(queryProc);
	}
	
	public boolean addClass(String className, String ID){
		try {
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, className);
			stmt.setString(3, ID);

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
			JOptionPane.showMessageDialog(null, "Class name cannot be null or empty.");
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "Invalid ProfessorID.");
			break;
		}
	}

	@Override
	public void displaySuccess() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Class Added");
	}
}
