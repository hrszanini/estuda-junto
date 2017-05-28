package controller;

import static spark.Spark.*;

import java.sql.SQLException;

public class MainServer {

	static Model model = new Model();

    public static void main(String[] args) throws SQLException {
    	
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        
        Rest controller = new Rest(model); 
        
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 3389;
        }
        
        port(port);
		staticFileLocation("/view");
		
		controller.addUsuario();
		controller.login();
		controller.grupos();
    }
	
}
