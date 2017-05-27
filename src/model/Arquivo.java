package model;

public class Arquivo {
	
	private int id;
	private String nome;
	private String path;
	
	public Arquivo(int id,String nome, String path){
		this.id = id;
		this.path = path;
		this.nome = nome;
	}
	
	public int getId(){
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
