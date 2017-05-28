package model;

public class Usuario extends Acesso{
	
	private Login login;
	private String vestibular;
	
	public String getVestibular() {
		return vestibular;
	}

	public void setVestibular(String vestibular) {
		this.vestibular = vestibular;
	}

	public Usuario(){}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	};

}
