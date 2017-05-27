package controller;
import spark.Spark;

public class Server {
	
	final static Model model = new Model();
	
	private static void main(String[] args){
		
		ProcessBuilder process = new ProcessBuilder();
		Integer port = 4567;		
		
		Spark.port(port);
		Spark.staticFileLocation("/view");
		
		//Rest controller = new Rest(sistema);
		
	}
}
