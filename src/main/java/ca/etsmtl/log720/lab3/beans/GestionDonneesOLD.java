package ca.etsmtl.log720.lab3.beans; 

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ca.etsmtl.log720.lab3.exceptions.InvalidLoginException;
import ca.etsmtl.log720.lab3.objetsDonnees.*;

public class GestionDonneesOLD 
{ 
	
	// Hibernate
	private static Configuration conf 				= null;
	private static ServiceRegistry sr				= null;
	private static SessionFactory sessionFactory	= null;
	
	private static DossierHome dh					= null;
	private static InfractionHome ih				= null;
	private static UtilisateurHome uh				= null;
	private static RoleHome rh						= null;
	
	private static GestionDonneesOLD instance = null;
	
	ArrayList<Dossier> dossiers = new ArrayList<Dossier>();
	ArrayList<Infraction> infractions = new ArrayList<Infraction>();

	private GestionDonneesOLD(){
		
		try{
			dh = new DossierHome();
			ih = new InfractionHome();
			uh = new UtilisateurHome();
			rh = new RoleHome();
			
		}
		catch(Exception e){
			System.out.println("Session factory FAILED !!! :" + e.getMessage());
		}
		
		addInfraction("Excès de vitesse", 1);
		addInfraction("Conduite imprudente", 3);
		addInfraction("Conduite avec facultés affaiblies", 5);
		addInfraction("Chasse au chevreuil", 10);
		
		addDossier("Bleau", "Jos", "123 ABC", "P3RM15");
		addDossier("Cipher", "Luc", "666 HEL", "4Permis5");
		addDossier("St-Hilaire", "Huguette", "456 DEF", "AsdfPermis");
		
	}
	
	public static GestionDonneesOLD getInstance()
	{
		if (instance == null){
			instance = new GestionDonneesOLD();
		}
		
		return instance;
	}
	
	public Dossier getDossier (int ID)
	{
		//Retourne un dossier spécifique
		return dossiers.get(ID);
	}
	
	public void addDossier (String nom, String prenom, String numPlaque, String permisDeConduire)
	{
		Dossier dossier = new Dossier();
		
		dossier.setId(dossiers.size() + 1);
		dossier.setNom(nom);
		dossier.setPrenom(prenom);
		dossier.setNumeroplaque(numPlaque);
		dossier.setNumeropermis(permisDeConduire);
	
		dh.persist(dossier);
		dossiers.add(dossier);
		
		System.out.println("Dossier ajouté!");
	}
	
	public ArrayList<Dossier> getDossiers ()
	{
		boolean endIsReached = false;
		int cpt = 1;
		dossiers.clear();
		while(!endIsReached){
			
			try{
				dossiers.add(dh.findById(cpt++));
			}
			catch(Exception e){
				endIsReached = true;
			}
		}
		//Récupère tous les dossiers
		return dossiers;
	}
	
	public ArrayList<Infraction> getInfractions ()
	{
		boolean endIsReached = false;
		int cpt = 1;
		infractions.clear();
		while(!endIsReached){
			
			try{
				infractions.add(ih.findById(cpt++));
			}
			catch(Exception e){
				endIsReached = true;
			}
		}
		
		//Récupère toutes les infractions
		return infractions;
	}
	
	public Infraction getInfractionDossier(int idDossier, int idInfraction)
	{
		//Retourne une infraction spécifique
		return null;
		//return dossiers.get(idDossier).getInfractions().get(idInfraction);
	}
	
	public ArrayList<Infraction> getInfractionsDossier(int idDossier)
	{
		//Retourne une liste d'infractions
		return null;
		//return dossiers.get(idDossier).getInfractions();
	}

	public void addInfractionDossier(int idDossier, int idInfraction)
	{
		System.out.println("Infraction ajoutée au dossier");
	}
	
	public void addInfraction(String description, int gravite)
	{
		Infraction infraction = new Infraction();
		
		infraction.setId(infractions.size() + 1);
		infraction.setDescription(description);
		infraction.setNiveaugravite(gravite);
		
		infractions.add(infraction);
		
		ih.persist(infraction);
		
		System.out.println("Infraction ajoutée");
	}
	
	public String validateLogin(String username, String password) throws InvalidLoginException
	{
		ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		
		boolean endIsReached = false;
		int cpt = 1;
		utilisateurs.clear();
		while(!endIsReached){
			
			try{
				utilisateurs.add(uh.findById(cpt++));
			}
			catch(Exception e){
				endIsReached = true;
			}
		}
		
		for (Utilisateur user : utilisateurs){
			
			if (user.getLogin().equals(username) && user.getPassword().equals(password)){
				return user.getLogin().toString();
			}
		}
		
		return null;	
	}
}