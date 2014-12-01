package ca.etsmtl.log720.lab3.util;

import com.opensymphony.xwork2.ActionContext;

import ca.etsmtl.log720.lab3.beans.GestionDonnees;
import ca.etsmtl.log720.lab3.exceptions.InvalidLoginException;

public class GestionLogin {
	  
	  public static boolean login(String username, String password)
	  {
		  try {
			String role = GestionDonnees.getInstance().validateLogin(username, password);
			ActionContext.getContext().getSession().put("role", role);
			return true;
		  } catch (InvalidLoginException e) {
			return false;
		  }
	  }
	  
	  public static void logout()
	  {
		  if(ActionContext.getContext().getSession().containsKey("role"))
		  {
			  ActionContext.getContext().getSession().remove("role");
		  }
	  }
	  
	  public static Boolean isLoggedIn()
	  {
		  return ActionContext.getContext().getSession().containsKey("role");
	  }
	  
	  public static String getRole()
	  {
		  return (String) ActionContext.getContext().getSession().get("role");
	  }
	
}
