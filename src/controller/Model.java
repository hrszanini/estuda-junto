package controller;

import java.util.ArrayList;
import java.util.List;

import model.*;

public class Model {
	private List<Grupo> grupos;
	private List<Usuario> usuarios;
	
	public Model(){
		this.grupos = new ArrayList<Grupo>();
		this.usuarios = new ArrayList<Usuario>();
		
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
