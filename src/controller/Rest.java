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
		post("/usuario/add", new Route(){
			@Override
			public Object handle(Request request, Response response) throws Exception {
				response.header("Access-Control-Allow-Origin", "*");
				
				JSONObject jsonRequest = new JSONObject(request.body());
				JSONObject jsonResponse = new JSONObject();
				JSONArray jsonResponseArray = new JSONArray();
				
				try{
					Login login = new Login(jsonRequest.getString("email"),jsonRequest.getString("senha"));
					Usuario usuario = new Usuario();
					usuario.setLogin(login);
					model.addUsuario(usuario);
					
					jsonResponse.put("status", 1);
					jsonResponseArray.put(jsonResponse);
					
				}catch(JSONException e){
					e.printStackTrace();
				}
				jsonResponse.put("status", 0);
				jsonResponseArray.put(jsonResponse);
     	       
     	        return jsonResponseArray;
			}
		});
	}
	

}
