//Auteur: Alex Gagnon

package ca.etsmtl.log720.lab3.donnees;

import java.util.ArrayList;

public class Dossier {
	
	private int id;;
	private String nom;
	private String prenom;
	private String numPlaque;
	private String permisDeConduire;
	private ArrayList<Infraction> infractions;
	
	public Dossier(int id, String nom, String prenom, String numPlaque, String permisDeConduire, ArrayList<Infraction> infractions)
	{
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.numPlaque = numPlaque;
		this.permisDeConduire = permisDeConduire;
		this.infractions = infractions;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public String getPrenom()
	{
		return prenom;
	}
	
	public String getNumPlaque()
	{
		return numPlaque;
	}
	
	public String getPermisDeConduire()
	{
		return permisDeConduire;
	}
	
	public ArrayList<Infraction> getInfractions()
	{
		return infractions;
	}
	
	public void setInfractions(ArrayList<Infraction> infractionsList) {
		this.infractions = infractionsList;
	}
}
