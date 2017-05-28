package controller;

import spark.Spark;

public class MainServer {

	public static void main(String[] args) {
		Integer port = 4567;		
		Model model;
		DAO dao = new DAO();
		
		if(dao.connect()){
			model = dao.initializeMode();
		}else{
			return;
		}
		
		Spark.port(port);
		Spark.staticFileLocation("/view");
		
		Rest controller = new Rest(model);
		
		controller.addUsuario();

	}

}
