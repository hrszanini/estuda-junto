package model;

public class Materia {
	
	private boolean humanas;
	private boolean exatas;
	private boolean biologicas;
	private boolean redacao;

	
	public Materia(){}
	
	public boolean isHumanas() {
		return humanas;
	}


	public void setHumanas(boolean humanas) {
		this.humanas = humanas;
	}


	public boolean isExatas() {
		return exatas;
	}


	public void setExatas(boolean exatas) {
		this.exatas = exatas;
	}


	public boolean isBiologicas() {
		return biologicas;
	}


	public void setBiologicas(boolean biologicas) {
		this.biologicas = biologicas;
	}


	public boolean isRedacao() {
		return redacao;
	}


	public void setRedacao(boolean redacao) {
		this.redacao = redacao;
	}
	
	
}
