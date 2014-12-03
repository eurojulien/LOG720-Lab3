//Auteur: Alex Gagnon

package ca.etsmtl.log720.lab3.actions;

import java.util.ArrayList;

import ca.etsmtl.log720.lab3.beans.GestionDonnees;
import ca.etsmtl.log720.lab3.objetsDonnees.*;
import ca.etsmtl.log720.lab3.util.GestionLogin;
import ca.etsmtl.log720.lab3.util.HTMLFilter;

public class ListeInfractionsAction {
	private ArrayList<Infraction> infractions;
	
	String description;
	String gravite;
	
	String role;
	
	String message;

	public String afficherListe() throws Exception {
		
		setRole(GestionLogin.getRole());
		
		chargerInfractions();
		return "success";
	}
	
	public String ajouterInfraction() throws Exception
	{
		
		setRole(GestionLogin.getRole());
		
		if(!description.equals("") && !gravite.equals(""))
		{
			try{
				int graviteInt = Integer.parseInt(gravite);
				
				if(graviteInt > 0)
				{
					try{
						GestionDonnees.getInstance().addInfraction(
								HTMLFilter.filter(description), 
								graviteInt);
						message = "Infraction ajoutée avec succès";
					}
					catch(Exception e)
					{
						message = "Une erreur inattendue s'est produite.";
					}
				}
				else
				{
					message = "La gravite doit être supérieure à 0.";
				}
			}
			catch(NumberFormatException e)
			{
				message = "La gravité doit être un entier positif.";
			}
			
		}
		else
		{
			message = "Tous les champs doivent être remplis.";
		}
		chargerInfractions();
		
		return "success";
	}
	
	public ArrayList<Infraction> getInfractions() {
		return infractions;
	}

	public void setInfractions(ArrayList<Infraction> infractions) {
		this.infractions = infractions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGravite() {
		return gravite;
	}

	public void setGravite(String gravite) {
		this.gravite = gravite;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void chargerInfractions()
	{
		infractions = GestionDonnees.getInstance().getInfractions();
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


}