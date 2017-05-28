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
		int id = 0;
		for(Grupo g: grupos)
			if(g.getId() > id)
				id = g.getId();
		grupo.setId(++id);
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
		int id = 0;
		for(Usuario u: usuarios)
			if(u.getId() > id)
				id = u.getId();
		usuario.setId(++id);
		usuarios.add(usuario);
		dao.insertUsuario(usuario);
		dao.insertMateriaAprender(usuario.getAprender(), usuario);
		dao.insertMateriaEnsinar(usuario.getEnsinar(), usuario);
	}
	
	public void delUsuario(Usuario usuario){
		for(Usuario u: usuarios)
			if(u.getId() == usuario.getId()){
				usuarios.remove(usuario);
				dao.delUsuario(usuario);
			}	
	}
	
	public boolean login(Login login){
		for(Usuario u: usuarios)
			if(u.getLogin().match(login))
				return true;
		return false;
	}
	
	public Usuario getUsuario(String email){
		for(Usuario u: usuarios)
			if(u.getLogin().getEmail().equals(email))
				return u;
		return null;
	}
	
	public List<Grupo> getGrupos(Usuario usuario){
		List<Grupo> retorno = new ArrayList<Grupo>();
		for(Grupo g: grupos){
			if(g.getUsuarios().contains(usuario))
				retorno.add(g);
		}
		return grupos;
	}
}
