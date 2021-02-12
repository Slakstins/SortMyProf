package sortProfessor.services.pull;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import sortProfessor.services.DatabaseConnectionService;

public class PullSchoolHouses extends DBPullService {
	private CallableStatement stmt = null;
	private String queryProc = "{? = call PullSchoolHouses(?)}";
	private String column1Name = "Name";
	private String column2Name = "HousePoints";

	public PullSchoolHouses(DatabaseConnectionService dbcs) {
		super(dbcs);
	}
	
	
/*
 * returns an array list of array lists with each inner arraylist representing an attribute column
 * The first value in each inner array is the column name.
 */
	public ArrayList<ArrayList<String>> pullSchoolHouses(String schoolName){

		ArrayList<String> houseNames = new ArrayList<String>();
		ArrayList<String> pointValues = new ArrayList<String>();
		stmt = generateCallableStatement(queryProc);
		try {
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, schoolName);
			
			ResultSet rs = stmt.executeQuery();

			//build the result arrays
			while(rs.next()) {
				String house = rs.getString(column1Name);
				String pointValue = rs.getString(column2Name);

				houseNames.add(house);
				pointValues.add(pointValue);
				}
		
			ArrayList<ArrayList<String>> houses = new ArrayList<ArrayList<String>>();
			houses.add(houseNames);
			houses.add(pointValues);
			return houses;
				
	
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
			JOptionPane.showMessageDialog(null, "The given school does not exist.");
			break;
		}
	}

	@Override
	public void displaySuccess() {
		System.out.println("School houses pulled.");
	}
}
