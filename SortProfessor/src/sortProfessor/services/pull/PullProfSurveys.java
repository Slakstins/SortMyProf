package sortProfessor.services.pull;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import sortProfessor.services.DatabaseConnectionService;

public class PullProfSurveys extends DBPullService {
	private CallableStatement stmt = null;
	private String queryProc = "{? = call PullProfSurveys(?)}";
	private String column1Name = "Name";
	private String column2Name = "Score";
	private String column3Name = "TimeStamp";	
	private String column4Name = "Comment";	
	private String column5Name = "Username";	


	public PullProfSurveys(DatabaseConnectionService dbcs) {
		super(dbcs);
	}
	
	
/*
 * returns an array list of array lists with each inner arraylist representing an attribute column
 * The first value in each inner array is the column name.
 */
	public ArrayList<ArrayList<String>> pullProfSurveys(String professorID){

		ArrayList<String> houseNames = new ArrayList<String>();
		ArrayList<String> surveyScores = new ArrayList<String>();
		ArrayList<String> timeStamps = new ArrayList<String>();
		ArrayList<String> comments = new ArrayList<String>();
		ArrayList<String> usernames = new ArrayList<String>();
		stmt = generateCallableStatement(queryProc);
		try {
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, professorID);

			ResultSet rs = stmt.executeQuery();

			//build the result arrays
			while(rs.next()) {
				String houseName = rs.getString(column1Name);
				String surveyScore = rs.getString(column2Name);
				String timeStamp = rs.getString(column3Name);
				String comment = rs.getString(column4Name);
				String username = rs.getString(column5Name);

				houseNames.add(houseName);
				surveyScores.add(surveyScore);
				timeStamps.add(timeStamp);
				comments.add(comment);
				usernames.add(username);
				}
		
			ArrayList<ArrayList<String>> professors = new ArrayList<ArrayList<String>>();
			professors.add(houseNames);
			professors.add(surveyScores);
			professors.add(timeStamps);
			professors.add(comments);
			professors.add(usernames);
			return professors;
				
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			int code = 0;
				try {
					code = stmt.getInt(1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
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
			System.out.println("professorID invalid");
			break;
		}
	}

	@Override
	public void displaySuccess() {
		System.out.println("Professors surveys pulled");
	}
}