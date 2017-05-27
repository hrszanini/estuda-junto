package model;

public class Usuario extends Acesso{
	
	private Login login;
	
	public Usuario(){}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	};

}
