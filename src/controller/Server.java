package controller;
import spark.Spark;

public class Server {
	
	static Model model;
	
	private static void main(String[] args){
		
		ProcessBuilder process = new ProcessBuilder();
		Integer port = 4567;		
		
		DAO dao = new DAO();
		if(dao.connect()){
			model = dao.initializeMode();
		}else{
			System.out.println("Erro de conexão ao banco");
		}
		
		Spark.port(port);
		Spark.staticFileLocation("/view");
		
		Rest controller = new Rest(model);
		
		controller.addUsuario();
		
	}
}
