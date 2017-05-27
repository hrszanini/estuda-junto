package controller;

import static spark.Spark.get;
import static spark.Spark.post;
import spark.*;
import org.json.*;

import model.*;

public class Rest {
	
	private Model model;
	
	public Rest(Model model){
		this.model = model;
	}
	
	public void addUsuario(){
		Spark.post("/usuario/add", new Route(){
			@Override
			public Object handle(Request request, Response response) throws Exception {
				response.header("Access-Control-Allow-Origin", "*");
				
				JSONObject jsonRequest = new JSONObject(request.body());
				
				try{
					Login login = new Login(jsonRequest.getString("email"),jsonRequest.getString("senha"));
					Usuario usuario = new Usuario();
					usuario.setLogin(login);
					model.addUsuario(usuario);
					
					
					
				}catch(JSONException e){
					
				}
				return null;
			}
		});
	}
	

}
