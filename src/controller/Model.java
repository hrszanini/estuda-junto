package controller;

import java.sql.SQLException;
import java.util.ArrayList;
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
        	this.grupos = new ArrayList<Grupo>();
            for(Grupo g: dao.getGrupos()){
            	g.setAgenda(dao.getAgenda(g));
            	g.setInventario(dao.getInventario(g));
            	g.setUsuarios(dao.getGrupoUsuario(g));
            	grupos.add(g);
            }
            
        	this.usuarios = new ArrayList<Usuario>();
            for(Usuario u: dao.getUsuarios()){
            	u.setAgenda(dao.getAgenda(u));
            	u.setInventario(dao.getInventario(u));
            	usuarios.add(u);
            }
        }catch(SQLException e){
        	e.printStackTrace();
        }  
	};
	
	public void addGrupo(Grupo grupo){
		grupos.add(grupo);
		dao.insertGrupo(grupo);
	}
	
	public void delGrupo(Grupo grupo){
		for(Grupo g: grupos)
			if(g.getId() == grupo.getId()){
				grupos.remove(grupo);
				dao.delGrupo(grupo);
			}
	}
	
	public void addUsuario(Usuario usuario){
		usuarios.add(usuario);
		dao.insertUsuario(usuario);
	}
	
	public void delUsuario(Usuario usuario){
		for(Usuario u: usuarios)
			if(u.getId() == usuario.getId()){
				usuarios.remove(usuario);
				dao.delUsuario(usuario);
			}	
	}
}
