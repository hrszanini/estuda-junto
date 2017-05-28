package model;

import java.util.Calendar;

public class Evento {
	
	private int id;
	private String nome;
	private String descricao;
	private Calendar data;
	private Calendar alerta;
	
	public Evento(){}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Calendar getAlerta() {
		return alerta;
	}

	public void setAlerta(Calendar alerta) {
		this.alerta = alerta;
	}

}
