package controller;

import model.Sistema;
import spark.Spark;

public class Server {
	
	final static Sistema sistema = new Sistema();
	
	private static void main(String[] args){
		
		ProcessBuilder process = new ProcessBuilder();
		Integer port = 4567;		
		
		Spark.port(port);
		Spark.staticFileLocation("/view");
		
		//Rest controller = new Rest(sistema);
		
	}
}
