package model;

import java.util.Calendar;

public class Evento {
	
	private int id;
	private String nome;
	private String descricao;
	private Calendar data;
	private Calendar ealerta;
	
	public Evento(){}
	
	public int getId() {
		return id;
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

	public Calendar getEalerta() {
		return ealerta;
	}

	public void setEalerta(Calendar ealerta) {
		this.ealerta = ealerta;
	}

}
