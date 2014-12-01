package ca.etsmtl.log720.lab3.interceptors;

import ca.etsmtl.log720.lab3.util.GestionLogin;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor{
	public String intercept(ActionInvocation invocation) throws Exception {
		
		if(GestionLogin.isLoggedIn())
		{
			return invocation.invoke();
		}
		else
		{
			return "login";
		}
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}
}
