package test;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.DAO;

public class teste {

	@Test
	public void test() {
			DAO dao = new DAO();
			dao.connect();
			
	}

}
