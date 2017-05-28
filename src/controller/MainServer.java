package controller;

import static spark.Spark.*;

import java.sql.SQLException;

public class MainServer {

	static Model model = new Model();

    public static void main(String[] args) throws SQLException {

        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        
        DAO dao = new DAO();
        dao.connect();
        model = dao.initializeMode();
        
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        
        port(port);
		staticFileLocation("/view");
		
		Rest controller = new Rest(model); 
		
		controller.addUsuario();
    }
	
}
