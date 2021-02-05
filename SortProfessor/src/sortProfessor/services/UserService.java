package sortProfessor.services;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JOptionPane;

public class UserService {
	private static final Random RANDOM = new SecureRandom();
	private static final Base64.Encoder enc = Base64.getEncoder();
	private static final Base64.Decoder dec = Base64.getDecoder();
	private DatabaseConnectionService dbService = null;
	private String loginQueryProc = "{? = call GetPasswordsForUsername(?)}";
	private ServiceManager serviceManager;

	public UserService(DatabaseConnectionService dbService, ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
		this.dbService = dbService;
	}

	public boolean useApplicationLogins() {
		return true;
	}

	public boolean login(String username, String password) {
		int code = 0;
		CallableStatement stmt = null;
		try {
			
			stmt = this.dbService.getConnection().prepareCall(loginQueryProc);

			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, username);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				String saltString = rs.getString("passwordSalt");
				
				String hash = hashPassword(getBytesFromString(saltString), password);
				
				if (hash.equals(rs.getString("PasswordHash"))) {
					System.out.println("login successful");
					serviceManager.setUser(username);
					return true;
				}
				
			}
				code = stmt.getInt(1);
			}
		catch (SQLException ex) {

			try {
				code = stmt.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			handleErrorCodeGetPasswordsForUsername(code);
			return false;
			}
		return false;

	}

	public boolean register(String username, String password, String firstName, String lastName) {
		//TODO: Task 6
		byte[] salt = getNewSalt();
		String hashed = hashPassword(salt, password);
		
		
		Connection connection = dbService.getConnection();
		CallableStatement stmt = null;

		try {
			stmt = connection.prepareCall("{? = call AddStudent(?, ?, ?, ?, ?)}");
			
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, firstName);
			
			stmt.setString(3, lastName);
			stmt.setString(4, username);
			stmt.setString(5, getStringFromBytes(salt));
			stmt.setString(6, hashed);
			stmt.execute();
			int returnCode = stmt.getInt(1);

		    if (returnCode != 0) {
		    	this.handleErrorCodeAddStudent(returnCode);
		    	return false;
		    }
			
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "That username is already taken");
			// TODO Auto-generated catch block
			return false;
		}
		
		serviceManager.setUser(username);
		return true;
		
		

	}
	
	public void handleErrorCodeGetPasswordsForUsername(int code) {
		switch(code) {
		case 1:
			JOptionPane.showMessageDialog(null, "Username cannot be null or empty");
			break;
		}
	}

	public void handleErrorCodeAddStudent(int code) {
		switch(code) {
		case 1:
			JOptionPane.showMessageDialog(null, "Student first and last name cannot be null or empty.");
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "Username cannot be null or empty.");
			break;
		case 3:
			JOptionPane.showMessageDialog(null, "PasswordSalt cannot be null or empty.");
		case 4:
			JOptionPane.showMessageDialog(null, "PasswordHash cannot be null or empty.");
		}
	}

	public byte[] getNewSalt() {
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
		return salt;
	}
	
	public String getStringFromBytes(byte[] data) {
		return enc.encodeToString(data);
	}
	
	public byte[] getBytesFromString(String byteString) {
		return dec.decode(byteString);
	}

	public String hashPassword(byte[] salt, String password) {

		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		SecretKeyFactory f;
		byte[] hash = null;
		try {
			f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = f.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
			e.printStackTrace();
		}
		return getStringFromBytes(hash);
	}

}
