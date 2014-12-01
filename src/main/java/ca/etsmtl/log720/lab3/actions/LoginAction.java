//Auteur: Alex Gagnon

package ca.etsmtl.log720.lab3.actions;

import ca.etsmtl.log720.lab3.util.GestionLogin;

public class LoginAction {
	
	String username;
	String password;

	public String login() throws Exception {
	      GestionLogin.logout();
	      if(GestionLogin.login(username, password))
	      {
	    	  return "success";
	      }
	      else
	      {
	    	  return "failure";
	      }
    }
	
	public String logout() throws Exception {
		GestionLogin.logout();
		return "success";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
