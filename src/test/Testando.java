package test;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DAO;
import model.*;

public class Testando {

	public static void main(String[] args) throws SQLException {
		DAO dao = new DAO();
		dao.connect();
		dao.resetTables();
		dao.disconnect();
	}
}
