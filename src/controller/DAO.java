package controller;

import java.sql.*;
import java.util.Calendar;

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
	
	public Model initializeMode(){
		Model model = new Model();
		String query = "SELECT CLI_ID, CLI_TIPO FROM CLIENTE;";
		ResultSet rs = execute(query);
		
		try {
			while(!rs.isAfterLast()){
				if(rs.getString("CLI_TIPO").equals("USUARIO"))
					model.addUsuario(getUsuario(rs.getInt("CLI_ID")));
				if(rs.getString("CLI_TIPO").equals("GRUPO"))
					model.addGrupo(getGrupo(rs.getInt("CLI_ID")));
				rs.next();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return model;
	}
	
	public Usuario getUsuario(int id){
		String query = "SELECT * FROM CLIENTE WHERE CLI_ID = "+id+";";
		ResultSet rs = execute(query);
		
		Usuario usuario = new Usuario();
		
		try {
			usuario.setId(id);
			usuario.setNome(rs.getString("CLI_NOME"));
			usuario.setDescricao(rs.getString("CLI_DESCRICAO"));
			usuario.setLocalidade(rs.getString("CLI_LOCALIDADE"));
			usuario.setLogin(getLogin(id));
			usuario.setAgenda(getAgenda(id));
			usuario.setInventario(getInventario(id));
			return usuario;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	
	public Grupo getGrupo(int id){
		String query = "SELECT * FROM CLIENTE WHERE CLI_ID = "+id+";";
		ResultSet rs = execute(query);
		
		Grupo grupo = new Grupo();
		
		try {
			grupo.setId(id);
			grupo.setNome(rs.getString("CLI_NOME"));
			grupo.setDescricao(rs.getString("CLI_DESCRICAO"));
			grupo.setLocalidade(rs.getString("CLI_LOCALIDADE"));
			grupo.setAgenda(getAgenda(id));
			return grupo;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	
	public Agenda getAgenda(int idCliente){
		String query = "SELECT * FROM EVENTO WHERE CLI_ID = "+idCliente+";";
		ResultSet rs = execute(query);
		
		Agenda agenda = new Agenda();
		
		try{
			while(!rs.isAfterLast()){
				Evento evento = new Evento();
				evento.setId(rs.getInt("EVE_ID"));
				evento.setNome(rs.getString("EVE_NOME"));
				evento.setDescricao(rs.getString("EVE_DESCRICAO"));
				Calendar c = Calendar.getInstance();
				c.setTime(rs.getDate("EVE_ALERTA"));
				evento.setAlerta(c);
				c.setTime(rs.getDate("EVE_DATA"));
				evento.setData(c);
				agenda.addEvento(evento);
				rs.next();
			}
			return agenda;
		}catch(SQLException e){
			System.out.println(e);
		}
		
		return null;
	}
	
	public Login getLogin(int id){
		String query = "SELECT * FROM USUARIO WHERE ID = "+id+";";
		ResultSet rs = execute(query);
		
		try{
			return new Login(rs.getString("USR_EMAIL"),rs.getString("USR_SENHA"));	
		}catch(SQLException e){
			System.out.println(e);
		}
		return null;
	}
	
	public Inventario getInventario(int idCliente){
		String query = "SELECT * FROM ARQUIVO WHERE CLI_ID = "+idCliente+";";
		ResultSet rs = execute(query);
		
		Inventario inventario = new Inventario();
		
		try{
			while(!rs.isAfterLast()){
				Arquivo arquivo = new Arquivo();
				arquivo.setId(rs.getInt("ARQ_ID"));
				arquivo.setNome(rs.getString("ARQ_NOME"));
				arquivo.setPath(rs.getString("ARQ_PATH"));
				inventario.addArquivo(arquivo);
				rs.next();
			}
			return inventario;
		}catch(SQLException e){
			System.out.println(e);
		}
		
		return null;
	}
}