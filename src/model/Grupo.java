package model;

import java.util.ArrayList;
import java.util.List;

public class Grupo extends Acesso{
	private List<Integer> usuarios;
	
	public Grupo(){
		this.usuarios = new ArrayList<Integer>();
	}
	public void addUsuario(Usuario usuario){
		usuarios.add(usuario.getId());
	}
	
	public void delUsuario(Usuario usuario){
		for(Integer u: usuarios)
			if(u == usuario.getId())
				usuarios.remove(u);
	}

	public List<Integer> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Integer> usuarios) {
		this.usuarios = usuarios;
	}
	
}
