package model;

public class Login {
	
	private String email;
	private String senha;
	
	public Login(String email, String senha){
		this.email = email;
		this.senha = senha;
	}
	
	public boolean match(Login login){
		if(this.email.equals(login.email) && this.senha.equals(login.senha))
			return true;
		return false;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

}
