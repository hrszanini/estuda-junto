package controller;

import java.sql.SQLException;
import java.util.List;

import model.*;

public class Model {
	private List<Grupo> grupos;
	private List<Usuario> usuarios;
	private DAO dao;
	
	public Model(){
        dao = new DAO();
        dao.connect();
        try{
        	this.grupos = dao.getGrupos();
            this.usuarios = dao.getUsuarios();
        }catch(SQLException e){
        	e.printStackTrace();
        }
	};
	
	public void addGrupo(Grupo grupo){
		grupos.add(grupo);
	}
	
	public void delGrupo(Grupo grupo){
		for(Grupo g: grupos)
			if(g.getId() == grupo.getId())
				grupos.remove(g);
	}
	
	public void addUsuario(Usuario usuario){
		usuarios.add(usuario);
	}
	
	public void delUsuario(Usuario usuario){
		for(Usuario u: usuarios)
			if(u.getId() == usuario.getId())
				grupos.remove(u);
	}
}
