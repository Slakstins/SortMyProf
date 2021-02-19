package sortProfessor.services.Add;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import sortProfessor.services.DatabaseConnectionService;

public class SubmitSurvey extends DBAddService{
	private CallableStatement stmt = null;
	private String queryProc = "{? = call AddSurvey(?, ?, ?, ?, ?)}";
	
	public SubmitSurvey(DatabaseConnectionService dbcs){
		super(dbcs);
		stmt = super.generateCallableStatement(queryProc);
	}
	
	public boolean submitSurvey(String profID, String studentUsername, String houseName,
			int score, String comment){
		try {
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, profID);
			stmt.setString(3, studentUsername);
			stmt.setString(4, houseName);
			stmt.setString(5, Integer.toString(score));
			stmt.setString(6, comment);
			boolean output = super.finalizeAddStmt(stmt);
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
			JOptionPane.showMessageDialog(null, "Score cannot be null");
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "Score must be from 1 to 10");
			break;
		case 3:
			JOptionPane.showMessageDialog(null, "ProfessorID cannot be null");
			break;
		case 4:
			JOptionPane.showMessageDialog(null, "Invalid professorID");
			break;
		case 5:
			JOptionPane.showMessageDialog(null, "StudentID cannot be null");
			break;
		case 6:
			JOptionPane.showMessageDialog(null, "Invalid student");
			break;
		case 7:
			JOptionPane.showMessageDialog(null, "HouseName cannot be null or empty");
			break;
		case 8:
			JOptionPane.showMessageDialog(null, "Invalid house name");
			break;
		}
	}

	@Override
	public void displaySuccess() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Survey added");
		
	}

}
