package sodabase.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SodaService {

	private DatabaseConnectionService dbService = null;
	
	public SodaService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}

	public boolean addSoda(String sodaName, String manf) {
		Connection connection = dbService.getConnection();
		CallableStatement stmt = null;

		try {
			stmt = connection.prepareCall("{? = call addSoda(?, ?)}");
			
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, sodaName);
			stmt.setString(3, manf);
			stmt.execute();
			
			int returnCode = stmt.getInt(1);
			switch(returnCode) {
			case 1:
			   System.out.println("ERROR: Soda name cannot be null or empty.");
				break;
			case 2:	
				System.out.println("ERROR: Soda name already exists.");
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
	
	public ArrayList<String> getSodas() {
		Statement stmt;
		ArrayList<String> sodas = new ArrayList<String>();
		try {
			stmt = dbService.getConnection().createStatement();
			
			ResultSet soda = stmt.executeQuery("Select name from Soda");
			while(soda.next()) {
				String restName = soda.getString("name");
				sodas.add(restName);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sodas;
	}
}
