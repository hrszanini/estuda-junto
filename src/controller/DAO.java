package controller;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
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
	
	public void resetTables() throws SQLException{
		dropTables();
		createTables();
	}
	
	public void dropTables() throws SQLException{
		execute("DROP TABLE MATERIA");
		execute("DROP TABLE GRUPO_USUARIO");
		execute("DROP TABLE USUARIO");
		execute("DROP TABLE ARQUIVO");
		execute("DROP TABLE EVENTO");
		execute("DROP TABLE CLIENTE");
	}
	
	public void createTables(){
		execute("CREATE TABLE CLIENTE("
				+"CLI_ID NUMBER,"
				+"CLI_NOME VARCHAR2(255),"
				+"CLI_DESCRICAO VARCHAR2(255),"
				+"CLI_LOCALIDADE VARCHAR2(255),"
				+"CLI_TIPO VARCHAR(255),"
				+"PRIMARY KEY(CLI_ID))"
		);
		execute("CREATE TABLE USUARIO("
				+"CLI_ID NUMBER,"
				+"USU_EMAIL VARCHAR(255),"
				+"USU_SENHA VARCHAR(255),"
				+"USU_VESTIBULAR VARCHAR(255),"
				+"PRIMARY KEY (CLI_ID),"
				+"FOREIGN KEY(CLI_ID) REFERENCES CLIENTE(CLI_ID))"
		);
		execute("CREATE TABLE EVENTO("
				+"EVE_ID NUMBER,"
				+"CLI_ID NUMBER,"
				+"EVE_NOME VARCHAR(255),"
				+"EVE_DESCRICAO VARCHAR(255),"
				+"EVE_DATA DATE,"
				+"EVE_ALERTA DATE,"
				+"PRIMARY KEY(EVE_ID),"
				+"FOREIGN KEY(CLI_ID) REFERENCES CLIENTE(CLI_ID))"
		);
		execute("CREATE TABLE ARQUIVO("
				+"ARQ_ID NUMBER,"
				+"CLI_ID NUMBER,"
				+"ARQ_NOME VARCHAR2(255),"
				+"ARQ_PATH VARCHAR2(255),"
				+"PRIMARY KEY(ARQ_ID),"
				+"FOREIGN KEY(CLI_ID) REFERENCES CLIENTE(CLI_ID))"
		);
		execute("CREATE TABLE GRUPO_USUARIO("
				+"CLI_ID_GRUPO NUMBER,"
				+"CLI_ID_USUARIO NUMBER,"
				+"PRIMARY KEY(CLI_ID_GRUPO,CLI_ID_USUARIO),"
				+"FOREIGN KEY(CLI_ID_GRUPO) REFERENCES CLIENTE(CLI_ID),"
				+"FOREIGN KEY(CLI_ID_USUARIO) REFERENCES CLIENTE(CLI_ID))"		
		);
		execute("CREATE TABLE MATERIA("
				+"CLI_ID NUMBER,"
				+"MAT_FLAG VARCHAR2(10),"
				+"MAT_HUMANAS NUMBER,"
				+"MAT_EXATAS NUMBER,"
				+"MAT_BIOLOGICAS NUMBER,"
				+"MAT_REDACAO NUMBER,"
				+"PRIMARY KEY(CLI_ID,MAT_FLAG),"
				+"FOREIGN KEY(CLI_ID) REFERENCES CLIENTE(CLI_ID))"
		);
	}
	
	public boolean connect(){
		boolean resp = false;
		
		String url = "jdbc:oracle:thin:@"+this.host+":"+port+":xe";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			this.c = DriverManager.getConnection(url,this.user,this.password);
			System.out.println("Conectado");
			resp = true;
		}catch(ClassNotFoundException e){
			System.out.println(e+"\nConnect");
		}catch(SQLException e){
			System.out.println(e+"\nConnect");
		}catch(InstantiationException e){
			System.out.println(e+"\nConnect");
		} catch (IllegalAccessException e) {
			System.out.println(e+"\nConnect");
		}
		return resp;
	}

	public boolean disconnect(){
		boolean resp = false;
		
		String url = "jdbc:oracle:thin:@"+this.host+":"+port+":xe";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			this.c = DriverManager.getConnection(url,this.user,this.password);
			this.c.close();
			System.out.println("Desconectado");
			resp = true;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(InstantiationException e){
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	public ResultSet execute(String query){
		System.out.println("Execute: "+query);
		Statement st = null;
		ResultSet result = null;
		
		try{
			st = this.c.createStatement();
			result = st.executeQuery(query);
			return result;
		}catch(SQLException e){
//			e.printStackTrace();
			System.out.println(e);
		}
		return null;
	}

	//INSERT
	public void insertUsuario(Usuario usuario){
		String insert = "INSERT INTO CLIENTE VALUES("
						+usuario.getId()+",'"
						+usuario.getNome()+"','"
						+usuario.getDescricao()+"','"
						+usuario.getLocalidade()+"','USUARIO')";
		execute(insert);
		insert = "INSERT INTO USUARIO VALUES("
				+usuario.getId()+",'"
				+usuario.getLogin().getEmail()+"','"
				+usuario.getLogin().getSenha()+"','"
				+usuario.getVestibular()+"')";
		execute(insert);
	}
	
	public void insertGrupo(Grupo grupo){
		String insert = "INSERT INTO CLIENTE VALUES("
				+grupo.getId()+",'"
				+grupo.getNome()+"','"
				+grupo.getDescricao()+"','"
				+grupo.getLocalidade()+"','GRUPO')";
		execute(insert);
		
	}

	public void insertArquivo(Arquivo arquivo, Acesso acesso){
		String insert = "INSERT INTO ARQUIVO VALUES("
				+arquivo.getId()+",'"
				+acesso.getId()+"','"
				+arquivo.getNome()+"','"
				+arquivo.getPath()+"')";
		execute(insert);
	}
	
	public void insertEvento(Evento evento, Acesso acesso){
		String insert = "INSERT INTO EVENTO VALUES("
				+evento.getId()+",'"
				+acesso.getId()+"','"
				+evento.getNome()+"','"
				+evento.getDescricao()+"','"
				+evento.getData()+"','"
				+evento.getAlerta()+"')";
		execute(insert);
	}

	public void insertGrupoUsuario(Grupo grupo, Usuario usuario){
		String insert = "INSERT INTO GRUPO_USUARIO VALUES("
				+grupo.getId()+",'"
				+usuario.getId()+"')";
		execute(insert);
	}

	public void insertMateriaAprender(Materia materia, Acesso acesso){
		String insert = "INSERT INTO MATERIA VALUES("
				+acesso.getId()+",'APRENDER','"
				+convertToInt(materia.isHumanas())+"','"
				+convertToInt(materia.isExatas())+"','"
				+convertToInt(materia.isBiologicas())+"','"
				+convertToInt(materia.isRedacao())+"')";
		execute(insert);
	}
	
	public void insertMateriaEnsinar(Materia materia, Acesso acesso){
		String insert = "INSERT INTO MATERIA VALUES("
				+acesso.getId()+",'ENSINAR','"
				+convertToInt(materia.isHumanas())+"','"
				+convertToInt(materia.isExatas())+"','"
				+convertToInt(materia.isBiologicas())+"','"
				+convertToInt(materia.isRedacao())+"')";
		execute(insert);
	}
	//DELETE
	public void delUsuario(Usuario usuario){
		execute("DELETE FROM MATERIA WHERE CLI_ID = "+usuario.getId());
		execute("DELETE FROM ARQUIVO WHERE CLI_ID = "+usuario.getId());
		execute("DELETE FROM EVENTO WHERE CLI_ID = "+usuario.getId());
		execute("DELETE FROM GRUPO_USUARIO WHERE CLI_ID_USUARIO = "+usuario.getId());
		execute("DELETE FROM USUARIO WHERE CLI_ID = "+usuario.getId());
		execute("DELETE FROM CLIENTE WHERE CLI_ID = "+usuario.getId());
	}
	
	public void delGrupo(Grupo grupo){
		execute("DELETE FROM MATERIA WHERE CLI_ID = "+grupo.getId());
		execute("DELETE FROM ARQUIVO WHERE CLI_ID = "+grupo.getId());
		execute("DELETE FROM EVENTO WHERE CLI_ID = "+grupo.getId());
		execute("DELETE FROM GRUPO_USUARIO WHERE CLI_ID_USUARIO = "+grupo.getId());
		execute("DELETE FROM CLIENTE WHERE CLI_ID = "+grupo.getId());
	}
	
	public void delArquivo(Arquivo arquivo){
		execute("DELETE FROM ARQUIVO WHERE ARQ_ID = "+arquivo.getId());
	}
	
	public void delEvento(Evento evento){
		execute("DELETE FROM EVENTO WHERE EVE_ID = "+evento.getId());
	}

	public void delGrupoUsuario(Grupo grupo, Usuario usuario){
		execute("DELETE FROM GRUPO_USUARIO WHERE CLI_ID_GRUPO = "+grupo.getId()
				+"AND CLI_ID_USUARIO = "+usuario.getId());
	}

	//GET
	public List<Grupo> getGrupos() throws SQLException{
		List<Grupo> grupos = new ArrayList<Grupo>();
		ResultSet result = execute("SELECT * FROM CLIENTE WHERE CLI_TIPO = 'GRUPO'");
		
		try {
			while(result.next()){
				Grupo grupo = new Grupo();
				grupo.setId(result.getInt("CLI_ID"));
				grupo.setNome(result.getString("CLI_NOME"));
				grupo.setDescricao(result.getString("CLI_DESCRICAO"));
				grupo.setLocalidade(result.getString("CLI_LOCALIDADE"));
				grupos.add(grupo);
			}
			return grupos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Usuario> getUsuarios() throws SQLException{
		List<Usuario> usuarios = new ArrayList<Usuario>();
		ResultSet result = execute("SELECT * FROM CLIENTE WHERE CLI_TIPO = 'USUARIO'");
	
		try {
			while(result.next()){
				Usuario usuario = new Usuario();
				usuario.setId(result.getInt("CLI_ID"));
				usuario.setNome(result.getString("CLI_NOME"));
				usuario.setDescricao(result.getString("CLI_DESCRICAO"));
				usuario.setLocalidade(result.getString("CLI_LOCALIDADE"));
				ResultSet aux = execute("SELECT * FROM USUARIO WHERE CLI_ID = "+usuario.getId());
				while(aux.next())
					usuario.setLogin(new Login(aux.getString("USU_EMAIL"),aux.getString("USU_SENHA")));
				usuarios.add(usuario);
			}
			
			return usuarios;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Inventario getInventario(Acesso acesso){
		Inventario inventario = new Inventario();
		ResultSet result = execute("SELECT * FROM ARQUIVO WHERE CLI_ID = "+acesso.getId());
		try{
			while(result.next()){
				Arquivo arquivo = new Arquivo();
				arquivo.setId(result.getInt("ARQ_ID"));
				arquivo.setNome(result.getString("ARQ_NOME"));
				arquivo.setPath(result.getString("ARQ_PATH"));
				inventario.addArquivo(arquivo);
			}
			return inventario;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Agenda getAgenda(Acesso acesso){
		Agenda agenda = new Agenda();
		Calendar c = Calendar.getInstance();
		ResultSet result = execute("SELECT * FROM EVENTO WHERE CLI_ID = "+acesso.getId());
		try{
			while(result.next()){
				Evento evento = new Evento();
				evento.setId(result.getInt("EVE_ID"));
				evento.setNome(result.getString("EVE_NOME"));
				c.setTime(result.getDate("EVE_DATA"));
				evento.setData(c);
				c.setTime(result.getDate("EVE_ALERTA"));
				evento.setAlerta(c);
				agenda.addEvento(evento);
			}
			return agenda;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Integer> getGrupoUsuario(Grupo grupo){
		ResultSet result = execute("SELECT * FROM GRUPO_USUARIO WHERE CLI_ID_GRUPO ="+grupo.getId());
		List<Integer> resposta = new ArrayList<Integer>();
		try{
			while(result.next()){
				resposta.add(result.getInt("CLI_ID_USUARIO"));
			return resposta;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;	
	}

	public Materia getMateriaApender(Acesso acesso){
		ResultSet result = execute("SELECT * FROM MATERIA WHERE CLI_ID = "+acesso.getId()
									+" AND MAT_FLAG = 'APRENDER'");
		Materia materia = new Materia();
		try{
			while(result.next()){
				materia.setHumanas(convertToBoolean(result.getInt("MAT_HUMANAS")));
				materia.setExatas(convertToBoolean(result.getInt("MAT_EXATAS")));
				materia.setBiologicas(convertToBoolean(result.getInt("MAT_BIOLOGICAS")));
				materia.setRedacao(convertToBoolean(result.getInt("MAT_REDACAO")));
				return materia;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Materia getMateriaEnsinar(Acesso acesso){
		ResultSet result = execute("SELECT * FROM MATERIA WHERE CLI_ID = "+acesso.getId()
		+" AND MAT_FLAG = 'ENSINAR'");
		Materia materia = new Materia();
		try{
			while(result.next()){
				materia.setHumanas(convertToBoolean(result.getInt("MAT_HUMANAS")));
				materia.setExatas(convertToBoolean(result.getInt("MAT_EXATAS")));
				materia.setBiologicas(convertToBoolean(result.getInt("MAT_BIOLOGICAS")));
				materia.setRedacao(convertToBoolean(result.getInt("MAT_REDACAO")));
				return materia;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	private boolean convertToBoolean(int i){
		if(i == 1)
			return true;
		return false;
	}
	
	private int convertToInt(boolean i){
		if(i)
			return 1;
		return 0;
	}
}