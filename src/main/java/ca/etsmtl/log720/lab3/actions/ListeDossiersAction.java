package ca.etsmtl.log720.lab3.actions;

import java.util.ArrayList;

import ca.etsmtl.log720.lab3.beans.GestionDonnees;
import ca.etsmtl.log720.lab3.objetsDonnees.Dossier;
import ca.etsmtl.log720.lab3.util.GestionLogin;
import ca.etsmtl.log720.lab3.util.HTMLFilter;

public class ListeDossiersAction {
	
	
	private ArrayList<Dossier> dossiers;
	
	String nom;
	String prenom;
	String immatriculation;
	String permis;

	String role;
	
	String message;

	public String afficherListe() throws Exception {
		
		setRole(GestionLogin.getRole());
		
		chargerDossiers();
		return "success";
	}
	
	public String ajouterDossier() throws Exception
	{
		
		if( nom.equals("") ||
			prenom.equals("") ||
			immatriculation.equals("") ||
			permis.equals("") )
		{
			setMessage("Tous les champs doivent être remplis.");
		}
		else
		{
			try
			{
				GestionDonnees.getInstance().addDossier(
						HTMLFilter.filter(nom),
						HTMLFilter.filter(prenom),
						HTMLFilter.filter(immatriculation),
						HTMLFilter.filter(permis)
				);
				message = "Dossier ajouté avec succès";
			}
			catch(Exception e)
			{
				message = "Une erreur inattendue s'est produite.";
			}
		}
		
		chargerDossiers();
		
		return "success";
	}
	
	public void chargerDossiers()
	{
		dossiers = GestionDonnees.getInstance().getDossiers();
	}
	
	public ArrayList<Dossier> getDossiers() {
		return dossiers;
	}

	public void setDossiers(ArrayList<Dossier> dossiers) {
		this.dossiers = dossiers;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getPermis() {
		return permis;
	}

	public void setPermis(String permis) {
		this.permis = permis;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}