//Auteur: Alex Gagnon

package ca.etsmtl.log720.lab3.beans; 

import java.util.ArrayList;

import ca.etsmtl.log720.lab3.donnees.Dossier;
import ca.etsmtl.log720.lab3.donnees.Infraction;
import ca.etsmtl.log720.lab3.exceptions.InvalidLoginException;

public class GestionDonnees 
{ 
	
	private static GestionDonnees instance = new GestionDonnees();
	
	ArrayList<Dossier> dossiers = new ArrayList<Dossier>();
	ArrayList<Infraction> infractions = new ArrayList<Infraction>();

	public GestionDonnees(){
		infractions.add(new Infraction(0, "Exc�s de vitesse", 1));
		infractions.add(new Infraction(1, "Conduite imprudente", 3));
		infractions.add(new Infraction(2, "Conduite avec facult�s affaiblies", 5));
		infractions.add(new Infraction(3, "Chasse au chevreuil", 10));
		
		dossiers.add(new Dossier(0, "Bleau", "Jos", "123 ABC", "P3RM15", infractions));
		dossiers.add(new Dossier(1, "Cipher", "Luc", "666 HEL", "4Permis5", infractions));
		dossiers.add(new Dossier(2, "St-Hilaire", "Huguette", "456 DEF", "AsdfPermis", new ArrayList<Infraction>()));
	}
	
	public static GestionDonnees getInstance()
	{
		return instance;
	}
	
	public Dossier getDossier (int ID)
	{
		//Retourne un dossier sp�cifique
		return dossiers.get(ID);
	}
	
	public void addDossier (String nom, String prenom, String numPlaque, String permisDeConduire)
	{
		System.out.println("Dossier ajout�!");
	}
	
	public ArrayList<Dossier> getDossiers ()
	{
		//R�cup�re tous les dossiers
		return dossiers;
	}
	
	public ArrayList<Infraction> getInfractions ()
	{
		//R�cup�re toutes les infractions
		return infractions;
	}
	
	public Infraction getInfractionDossier(int idDossier, int idInfraction)
	{
		//Retourne une infraction sp�cifique
		return dossiers.get(idDossier).getInfractions().get(idInfraction);
	}
	
	public ArrayList<Infraction> getInfractionsDossier(int idDossier)
	{
		//Retourne une liste d'infractions
		return dossiers.get(idDossier).getInfractions();
	}

	public void addInfractionDossier(int idDossier, int idInfraction)
	{
		System.out.println("Infraction ajout�e au dossier");
	}
	
	public void addInfraction(String description, int gravite)
	{
		System.out.println("Infraction ajout�e");
	}
	
	public String validateLogin(String username, String password) throws InvalidLoginException
	{
		if(username.equals("police") || username.equals("admin"))
		{
			return username;
		}
		else
		{
			System.out.println("Non");
			throw new InvalidLoginException();
		}
		
	}
}