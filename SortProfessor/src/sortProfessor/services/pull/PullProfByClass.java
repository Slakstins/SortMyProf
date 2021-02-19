package sortProfessor.services.pull;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import sortProfessor.services.DatabaseConnectionService;

public class PullProfByClass extends DBPullService {
	private CallableStatement stmt = null;
	private String queryProc = "{? = call PullProfByClass(?, ?)}";
	private String column1Name = "ID";
	private String column2Name = "FirstName";
	private String column3Name = "LastName";
	private String column4Name = "AvgScore";

	public PullProfByClass(DatabaseConnectionService dbcs) {
		super(dbcs);
	}
	
	
/*
 * returns an array list of array lists with each inner arraylist representing an attribute column
 * The first value in each inner array is the column name.
 */
	public ArrayList<ArrayList<String>> pullProfByClass(String schoolName, String className){

		ArrayList<String> ids = new ArrayList<String>();
		ArrayList<String> fNames = new ArrayList<String>();
		ArrayList<String> lNames = new ArrayList<String>();
		ArrayList<String> scores = new ArrayList<String>();
		
		stmt = generateCallableStatement(queryProc);
		try {
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, schoolName);
			stmt.setString(3, className);
			
			ResultSet rs = stmt.executeQuery();

			//build the result arrays
			while(rs.next()) {
				String id = rs.getString(column1Name);
				String fName = rs.getString(column2Name);
				String lName = rs.getString(column3Name);
				String score = rs.getString(column4Name);
			
				ids.add(id);
				fNames.add(fName);
				lNames.add(lName);
				scores.add(score);
				}
		
			ArrayList<ArrayList<String>> profs = new ArrayList<ArrayList<String>>();
			profs.add(ids);
			profs.add(fNames);
			profs.add(lNames);
			profs.add(scores);
			return profs;
				
	
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
			JOptionPane.showMessageDialog(null, "The school name cannot be empty.");
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "The given school does not exist.");
			break;
		case 3:
			JOptionPane.showMessageDialog(null, "The given class does not exist.");
			break;
	}
	}

	@Override
	public void displaySuccess() {
		System.out.println("Professors pulled by school / class.");
	}
}
