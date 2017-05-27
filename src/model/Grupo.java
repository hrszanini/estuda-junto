package model;

import java.util.ArrayList;
import java.util.List;

public class Grupo extends Acesso{
	private List<Usuario> usuarios;
	
	public Grupo(){
		this.usuarios = new ArrayList<Usuario>();
	}
	public void addUsuario(Usuario usuario){
		usuarios.add(usuario);
	}
	
	public void delUsuario(Usuario usuario){
		for(Usuario u: usuarios)
			if(u.getId() == usuario.getId())
				usuarios.remove(u);
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}
