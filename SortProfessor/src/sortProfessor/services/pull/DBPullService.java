package sortProfessor.services.pull;

import java.sql.CallableStatement;

import sortProfessor.services.DBService;
import sortProfessor.services.DatabaseConnectionService;

//no obvious functionality to add yet here, but there may be later
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
