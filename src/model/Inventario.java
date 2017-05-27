package model;

import java.util.ArrayList;
import java.util.List;

public class Inventario {

	private List<Arquivo> arquivos;
	
	public Inventario(){
		this.arquivos = new ArrayList<Arquivo>();
	}
	
	public void addArquivo(Arquivo arquivo){
		arquivos.add(arquivo);
	}
	
	public void delArquivo(Arquivo arquivo){
		for(Arquivo a: arquivos)
			if(a.getId() == arquivo.getId())
				arquivos.remove(a);
	}
}
