package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class DAO {
	
	private String host;
	private String user;
	private String password;
	private int port;
	public Connection c;
	
	public DAO(){
		this.host = "localhost";
		this.password = "estuda_junto";
		this.user = "estuda_junto";
		this.port = 1521;
	}
	
	public DAO(String host,String user, String password,int port){
		this.password = password;
		this.user = user;
		this.host = host;
		this.port = port;
	}
	
	public boolean connect(){
		boolean resp = false;
		
		String url = "jbdc:oracle:thin:@"+this.host+":"+port+":xe";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			this.c = DriverManager.getConnection(url,this.user,this.password);
			resp = true;
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(SQLException e){
			System.out.println(e);
		}catch(InstantiationException e){
			System.out.println(e);
		} catch (IllegalAccessException e) {
			System.out.println(e);
		}
		return resp;
	}

	public boolean disconnect(){
		boolean resp = false;
		
		String url = "jbdc:oracle:thin:@"+this.host+":"+port+":xe";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			this.c = DriverManager.getConnection(url,this.user,this.password);
			this.c.close();
			resp = true;
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(SQLException e){
			System.out.println(e);
		}catch(InstantiationException e){
			System.out.println(e);
		} catch (IllegalAccessException e) {
			System.out.println(e);
		}
		return resp;
	}
	
	public ResultSet execute(String query){
		Statement st;
		ResultSet rs;
		
		try{
			st = this.c.createStatement();
			rs = st.executeQuery(query);
			return rs;
		}catch(SQLException e){
			System.out.println(e);
		}
		return null;
	}
	
	public List<Usuario> getUsuarios(){
		String query = "SELECT * FROM USUARIO";
		List usuarios = new ArrayList<Usuario>();	
		ResultSet rs = execute(query);
		
		return usuarios;
	}
}