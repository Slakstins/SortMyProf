package sortProfessor.services;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

public class GenerateQuestions extends DBService {

	private CallableStatement stmt = null;
	private String queryProc = "{? = call GenerateQuestions()}";
	public GenerateQuestions(DatabaseConnectionService dbcs) {
		super(dbcs);
		stmt = super.generateCallableStatement(queryProc);
		// TODO Auto-generated constructor stub
	}

	public boolean generateQuestions(){
		try {
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.execute();
			int code = stmt.getInt(1);
			if (code != 0) {
				System.out.println("SQL return code: " + code);
				handleErrorCode(code);
				return false;
			} else {
				displaySuccess();
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "duplicate professor");

			return false;
		}
	}

	@Override
	public void handleErrorCode(int code) {
		JOptionPane.showMessageDialog(null, "problem generatingQuestions");
	}

	@Override
	public void displaySuccess() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Questions Generated");
		
	}

}