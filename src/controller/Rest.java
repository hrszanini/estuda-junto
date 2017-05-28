package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;

import spark.*;
import org.json.*;

import model.*;

@SuppressWarnings("unused")
public class Rest {
	
	private Model model;
	
	public Rest(Model model){
		this.model = model;
	}
	
	public void addUsuario(){
		post("/usuario", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	
	        	response.header("Access-Control-Allow-Origin", "*");
	        	
				JSONObject jsonRequest = new JSONObject(convertJSONString(request.body()));
				JSONArray jsonResponseArray = new JSONArray();
				JSONObject jsonResponse = new JSONObject();
	        	
	        	try {
					Usuario usuario = new Usuario();
					Materia materiaAprender = new Materia();
					Materia materiaEnsinar = new Materia();
					Login login = new Login(jsonRequest.getString("email"),jsonRequest.getString("senha"));
					
					usuario.setNome(jsonRequest.getString("nome"));
					usuario.setDescricao(jsonRequest.getString("descricao"));
					usuario.setLocalidade(jsonRequest.getString("localidade"));
					usuario.setVestibular(jsonRequest.getString("vestibular"));
					
					materiaEnsinar.setHumanas(jsonRequest.getBoolean("humanas"));
					materiaEnsinar.setExatas(jsonRequest.getBoolean("exatas"));
					materiaEnsinar.setBiologicas(jsonRequest.getBoolean("biologicas"));
					materiaEnsinar.setRedacao(jsonRequest.getBoolean("redacao"));
					
					materiaAprender.setHumanas(jsonRequest.getBoolean("lhumanas"));
					materiaAprender.setExatas(jsonRequest.getBoolean("lexatas"));
					materiaAprender.setBiologicas(jsonRequest.getBoolean("lbiologicas"));
					materiaAprender.setRedacao(jsonRequest.getBoolean("lredacao"));
					
					usuario.setAprender(materiaAprender);
					usuario.setEnsinar(materiaEnsinar);
					usuario.setLogin(login);
					
					model.addUsuario(usuario);
            			
					jsonResponse.put("status", 1);
					jsonResponseArray.put(jsonResponse);
	         	   	return jsonResponseArray; 
	         	   	
        		}catch (JSONException e){
        			e.printStackTrace();
        		}
    			
	        	jsonResponse.put("status", 0);
				jsonResponseArray.put(jsonResponse);
				
         	   	return jsonResponseArray; 
	         }
		});
	}
	
	public void login(){
		post("/login", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	
	        	response.header("Access-Control-Allow-Origin", "*");
	        	
				JSONObject jsonRequest = new JSONObject(convertJSONString(request.body()));
				JSONArray jsonResponseArray = new JSONArray();
				JSONObject jsonResponse = new JSONObject();
	        	
	        	try {
	        		
					Login login = new Login(jsonRequest.getString("email"),jsonRequest.getString("senha"));
					
					if(model.login(login)){
						jsonResponse.put("status", 1);
						jsonResponseArray.put(jsonResponse);
			    	   	return jsonResponseArray; 
					}
	         	
        		}catch (JSONException e){
        			e.printStackTrace();
        		}
    			
	        	jsonResponse.put("status", 0);
				jsonResponseArray.put(jsonResponse);
				
         	   	return jsonResponseArray; 
	         }
		});
	}
	
	public void addUsuario2(){
		get("/usuario/:nome/:senha", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	
	        	response.header("Access-Control-Allow-Origin", "*");
	        	
				JSONArray jsonResponseArray = new JSONArray();
				JSONObject jsonResponse = new JSONObject();
	        	
	        	try {
	        		Login login = new Login(request.params(":nome"),request.params(":senha"));
					Usuario usuario = new Usuario();
					
					
					usuario.setLogin(login);
					model.addUsuario(usuario);
            			
					jsonResponse.put("status", 1);
					jsonResponseArray.put(jsonResponse);
	         	   	return jsonResponseArray; 
	         	   	
        		}catch (JSONException e){
        			e.printStackTrace();
        		}
    			
	        	jsonResponse.put("status", 0);
				jsonResponseArray.put(jsonResponse);
				
         	   	return jsonResponseArray; 
	         }
		});
	}
	
	private String convertJSONString(String str){
		
		Charset utf8charset = Charset.forName("UTF-8");
		Charset iso88591charset = Charset.forName("ISO-8859-1");
	
		ByteBuffer inputBuffer = ByteBuffer.wrap(str.getBytes());
	
		// decode UTF-8
		CharBuffer data = utf8charset.decode(inputBuffer);
	
		// encode ISO-8559-1
		ByteBuffer outputBuffer = iso88591charset.encode(data);
		byte[] outputData = outputBuffer.array();
		
		str = new String(outputData);
		
		return str;
	}
}
