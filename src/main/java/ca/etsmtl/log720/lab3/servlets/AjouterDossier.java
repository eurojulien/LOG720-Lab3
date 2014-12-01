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
 * Servlet implementation class AjouterDossier
 */
public class AjouterDossier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterDossier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.isUserInRole(Constantes.ROLE_ADMINSTRATEUR))
		{
			if( request.getParameter("nom").equals("") ||
				request.getParameter("prenom").equals("") ||
				request.getParameter("immatriculation").equals("") ||
				request.getParameter("permis").equals("") )
			{
				request.setAttribute("erreur", "Tous les champs doivent être remplis.");
			}
			else
			{
				GestionDonnees.getInstance().addDossier(
						HTMLFilter.filter(request.getParameter("nom")),
						HTMLFilter.filter(request.getParameter("prenom")),
						HTMLFilter.filter(request.getParameter("immatriculation")),
						HTMLFilter.filter(request.getParameter("permis"))
				);
			}
			request.getRequestDispatcher("/ListeDossiers").forward(request, response);
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
