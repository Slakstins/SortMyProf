package pull.services;

import java.sql.CallableStatement;

import sortProfessor.services.DBService;
import sortProfessor.services.DatabaseConnectionService;

public abstract class DBPullService extends DBService {

	public DBPullService(DatabaseConnectionService dbcs) {
		super(dbcs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleErrorCode(int code) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displaySuccess() {
		// TODO Auto-generated method stub

	}
	

}
