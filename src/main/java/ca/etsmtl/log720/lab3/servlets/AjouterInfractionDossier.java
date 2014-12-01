package ca.etsmtl.log720.lab3.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.etsmtl.log720.lab3.beans.GestionDonnees;
import ca.etsmtl.log720.lab3.util.Constantes;

/**
 * Servlet implementation class AjouterInfractionDossier
 */
public class AjouterInfractionDossier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterInfractionDossier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.isUserInRole(Constantes.ROLE_POLICIER))
		{
			String dossierId = request.getParameter("dossierId");
			String infractionId = request.getParameter("infractionId");
			
			GestionDonnees.getInstance().addInfractionDossier(Integer.parseInt(dossierId), Integer.parseInt(infractionId));
			request.getSession().setAttribute("donnees", GestionDonnees.getInstance());
			
			request.getRequestDispatcher("/ListeInfractionsDossier").forward(request, response);
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
