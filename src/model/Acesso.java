package model;

public abstract class Acesso {
	
	private int id;
	private String nome;
	private Inventario inventario;
	private String descricao;
	private Agenda agenda;
	private String localidade;
	private Materia ensinar;
	private Materia aprender;
	
	public Materia getEnsinar() {
		return ensinar;
	}

	public void setEnsinar(Materia ensinar) {
		this.ensinar = ensinar;
	}

	public Materia getAprender() {
		return aprender;
	}

	public void setAprender(Materia aprender) {
		this.aprender = aprender;
	}

	public Acesso(){}

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

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

}
