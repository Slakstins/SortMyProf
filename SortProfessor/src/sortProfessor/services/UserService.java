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

	public UserService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}

	public boolean useApplicationLogins() {
		return true;
	}
	//IMPLEMENT WHEN  LOGIN PROCEDURE IS READY
	public boolean login(String username, String password) {
//		try {
//			
////			String query = "Select PasswordHash, PasswordSalt\n from [User]\n where [Username] = ?\n";
////			PreparedStatement stmt = this.dbService.getConnection().prepareStatement(query);
////			
////			if (username != null) {
////				stmt.setString(1, username);
////			}
////			ResultSet rs = stmt.executeQuery();
////
////			while(rs.next()) {
////				String saltString = rs.getString("passwordSalt");
////				
////				String hash = hashPassword(getBytesFromString(saltString), password);
////				
////				if (hash.equals(rs.getString("PasswordHash")))
////					return true;
////				return false;
//				
//			}
//			
//
//			
//
//			
//	}
//	catch (SQLException ex) {
//		ex.printStackTrace();
//		return false;
//	}
		return false;
	}

	public boolean register(String username, String password) {
		//TODO: Task 6
		byte[] salt = getNewSalt();
		String hashed = hashPassword(salt, password);
		
		
		Connection connection = dbService.getConnection();
		CallableStatement stmt = null;

		try {
			stmt = connection.prepareCall("{? = call AddStudent(?, ?, ?, ?, ?)}");
			
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, "Harry");
			
			stmt.setString(3, "Potter");
			stmt.setString(4, username);
			stmt.setString(5, getStringFromBytes(salt));
			stmt.setString(6, hashed);
			stmt.execute();
			int returnCode = stmt.getInt(1);

		    if (returnCode != 0) {
		    	System.out.println("Registration SQL error code is " + returnCode);
		    	return false;
		    }
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
		
		

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
