//Auteur: Alex Gagnon

package ca.etsmtl.log720.lab3.actions;

import java.util.ArrayList;

import ca.etsmtl.log720.lab3.beans.GestionDonnees;
import ca.etsmtl.log720.lab3.objetsDonnees.Dossier;
import ca.etsmtl.log720.lab3.objetsDonnees.Infraction;
import ca.etsmtl.log720.lab3.util.GestionLogin;

public class ListeInfractionsDossiersAction {
	private ArrayList<Infraction> infractionsDossier; //Liste des infractions du dossier
	private ArrayList<Infraction> listeInfractions; //Liste des infractions de la liste déroulante
	private String dossierId;
	private Dossier dossier;
	private String infractionId;
	
	String role;
	
	String message;

	public String afficherListeInfractionsDossier() throws Exception {
		
		setRole(GestionLogin.getRole());

		chargerDossier();
		chargerListeInfractions();
		
		return "success";
	}
	
	public String ajouterInfractionDossier() throws Exception {
		
		setRole(GestionLogin.getRole());
				
		GestionDonnees donnees = GestionDonnees.getInstance();
		
		try
		{
			donnees.addInfractionDossier(Integer.parseInt(dossierId), Integer.parseInt(infractionId));
		}
		catch(Exception e)
		{
			message = "Une erreur inattendue s'est produite.";
		}
		
		message = "Infraction ajoutée avec succès";
		
		chargerDossier();
		chargerListeInfractions();
		
		return "success";
	}
	
	public void chargerDossier()
	{
		GestionDonnees donnees = GestionDonnees.getInstance();
		
		setInfractionsDossier(donnees.getInfractionsDossier(Integer.parseInt(dossierId)));
		setDossier(donnees.getDossier(Integer.parseInt(dossierId)));
	}
	
	public void chargerListeInfractions()
	{
		GestionDonnees donnees = GestionDonnees.getInstance();
		
		setListeInfractions(donnees.getInfractions());
	}

	public ArrayList<Infraction> getInfractionsDossier() {
		return infractionsDossier;
	}

	public void setInfractionsDossier(ArrayList<Infraction> arrayList) {
		this.infractionsDossier = arrayList;
	}

	public Dossier getDossier() {
		return dossier;
	}

	public void setDossier(Dossier dossier2) {
		this.dossier = dossier2;
	}
	
	public String getDossierId() {
		return dossierId;
	}

	public void setDossierId(String dossierId) {
		this.dossierId = dossierId;
	}

	public ArrayList<Infraction> getListeInfractions() {
		return listeInfractions;
	}

	public void setListeInfractions(ArrayList <Infraction> arrayList) {
		this.listeInfractions = arrayList;
	}
	
	public String getInfractionId() {
		return infractionId;
	}

	public void setInfractionId(String infractionId) {
		this.infractionId = infractionId;
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