package ca.etsmtl.log720.lab3.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.etsmtl.log720.lab3.beans.GestionDonnees;
import ca.etsmtl.log720.lab3.util.Constantes;
import ca.etsmtl.log720.lab3.util.HTMLFilter;

/**
 * Servlet implementation class AjouterInfraction
 */
public class AjouterInfraction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterInfraction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.isUserInRole(Constantes.ROLE_ADMINSTRATEUR))
		{
			int gravite;
			
			try{
				gravite = Integer.parseInt(request.getParameter("gravité"));
				
				if(request.getParameter("description").equals(""))
				{
					request.setAttribute("erreur", "La description ne doir pas être un champ vide.");
				}
				else
				{
					GestionDonnees.getInstance().addInfraction(
							HTMLFilter.filter(request.getParameter("description")),
							gravite 
					);
				}
			}
			catch(NumberFormatException e)
			{
				request.setAttribute("erreur", "La gravité doit être un entier positif.");
			}
			
			request.getRequestDispatcher("/ListeInfractions").forward(request, response);
		}
		else
		{
			request.getRequestDispatcher("/acces_refuse.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
