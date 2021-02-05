package sortProfessor.services.pull;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import sortProfessor.services.DatabaseConnectionService;

public class PullQuestions extends DBPullService {
	private CallableStatement stmt = null;
	private String queryProc = "{? = call PullQuestions()}";
	private String column1Name = "text";
	private String column2Name = "AnswerA";
	private String column3Name = "AnswerB";	
	private String column4Name = "AnswerC";	
	private String column5Name = "AnswerD";

	public PullQuestions(DatabaseConnectionService dbcs) {
		super(dbcs);
	}
	
	public ArrayList<ArrayList<String>> pullQuestions() {
		ArrayList<String> qtexts = new ArrayList<String>();
		ArrayList<String> answerAs = new ArrayList<String>();
		ArrayList<String> answerBs = new ArrayList<String>();
		ArrayList<String> answerCs = new ArrayList<String>();
		ArrayList<String> answerDs = new ArrayList<String>();
		
		stmt = generateCallableStatement(queryProc);
		try {
			stmt.registerOutParameter(1, Types.INTEGER);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String text = rs.getString(column1Name);
				String AnswerA = rs.getString(column2Name);
				String AnswerB = rs.getString(column3Name);
				String AnswerC = rs.getString(column4Name);
				String AnswerD = rs.getString(column5Name);
				
				qtexts.add(text);
				answerAs.add(AnswerA);
				answerBs.add(AnswerB);
				answerCs.add(AnswerC);
				answerDs.add(AnswerD);
			}
			ArrayList<ArrayList<String>> questions = new ArrayList<ArrayList<String>>();
			questions.add(qtexts);
			questions.add(answerAs);
			questions.add(answerBs);
			questions.add(answerCs);
			questions.add(answerDs);
			
			return questions;
			
		} catch (SQLException e) {
			int code = 0;
			try {
				code = stmt.getInt(1);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			handleErrorCode(code);
			return null;
		}
	}

	@Override
	public void handleErrorCode(int code) {
		switch(code) {
		case 1:
			JOptionPane.showMessageDialog(null, "System failed to pull questions");
			break;
		}
	}

}
