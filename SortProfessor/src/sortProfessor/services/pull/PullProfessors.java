package sortProfessor.services.pull;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import sortProfessor.services.DatabaseConnectionService;

public class PullProfessors extends DBPullService {
	private CallableStatement stmt = null;
	private String queryProc = "{? = call PullProfessors(?, ?)}";
	private String column1Name = "ID";
	private String column2Name = "FirstName";
	private String column3Name = "LastName";	
	private String column4Name = "SchoolName";	

	public PullProfessors(DatabaseConnectionService dbcs) {
		super(dbcs);
		// TODO Auto-generated constructor stub
	}
	
	
/*
 * returns an array list of array lists with each inner arraylist representing an attribute column
 * The first value in each inner array is the column name.
 */
	public ArrayList<ArrayList<String>> pullProfessors(String profFName, String profLName){

		ArrayList<String> ids = new ArrayList<String>();
		ArrayList<String> FNames = new ArrayList<String>();
		ArrayList<String> LNames = new ArrayList<String>();
		ArrayList<String> schoolNames = new ArrayList<String>();
		stmt = generateCallableStatement(queryProc);
		try {
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, profFName);
			stmt.setString(3, profLName);
			
			
			ResultSet rs = stmt.executeQuery();

			//build the result arrays
			while(rs.next()) {
				String id = rs.getString(column1Name);
				String FName = rs.getString(column2Name);
				String LName = rs.getString(column3Name);
				String schoolName = rs.getString(column4Name);

				ids.add(id);
				FNames.add(FName);
				LNames.add(LName);
				schoolNames.add(schoolName);
				
				System.out.println(schoolName);
				}
		
			ArrayList<ArrayList<String>> professors = new ArrayList<ArrayList<String>>();
			professors.add(ids);
			professors.add(FNames);
			professors.add(LNames);
			professors.add(schoolNames);
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
			System.out.println("Professor first name cannot be null or empty.");
			break;
		case 2:
			System.out.println("Professor last name cannot be null or empty.");
			break;
		}
	}

	@Override
	public void displaySuccess() {
		System.out.println("Professors pulled");
	}
}