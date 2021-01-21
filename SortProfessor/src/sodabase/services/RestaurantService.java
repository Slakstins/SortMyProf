package sodabase.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class RestaurantService {

	private DatabaseConnectionService dbService = null;
	
	public RestaurantService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}
	
	public boolean addResturant(String restName, String addr, String contact) {
		//TODO: Task 5

		Connection connection = dbService.getConnection();
		CallableStatement stmt = null;

		try {
			stmt = connection.prepareCall("{? = call addRestaurant(?, ?, ?)}");
			
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, restName);
			stmt.setString(3, addr);
			stmt.setString(4, contact);
			stmt.execute();
			
			int returnCode = stmt.getInt(1);
			switch(returnCode) {
			case 1:
			   System.out.println("ERROR: Restaurant name cannot be null or empty");
				break;
			case 2:	
				System.out.println("ERROR: Restaurant name already exists.");
				break;
			case 0:
				return true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		return false;
	}
	

	public ArrayList<String> getRestaurants() {
		Statement stmt;
		ArrayList<String> rests = new ArrayList<String>();
		try {
			stmt = dbService.getConnection().createStatement();
			
			ResultSet rs = stmt.executeQuery("Select name from Rest");
			while(rs.next()) {
				String restName = rs.getString("name");
				rests.add(restName);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rests;

		
		
		

	}
}
