package ca.etsmtl.log720.lab3.beans; 

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import ca.etsmtl.log720.lab3.exceptions.InvalidLoginException;
import ca.etsmtl.log720.lab3.objetsDonnees.*;

public class GestionDonnees 
{ 
	
	// Hibernate
	private static Configuration conf 				= null;
	private static ServiceRegistry sr				= null;
	private static SessionFactory sessionFactory	= null;
	
	
	private static GestionDonnees instance = null;
	
	ArrayList<Dossier> dossiers = new ArrayList<Dossier>();
	ArrayList<Infraction> infractions = new ArrayList<Infraction>();

	private GestionDonnees(){
		
		try{
			
			conf 				= new Configuration().configure();
			sr					= new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
			sessionFactory		= conf.buildSessionFactory(sr);
		}
		catch(Exception e){
			System.out.println("Session factory FAILED !!! :" + e.getMessage());
		}
		
		dossiers 		= getData(Dossier.class);
		infractions		= getData(Infraction.class);
		
		addInfraction("Excès de vitesse", 1);
		addInfraction("Conduite imprudente", 3);
		addInfraction("Conduite avec facultés affaiblies", 5);
		addInfraction("Chasse au chevreuil", 10);
		
		addDossier("Bleau", "Jos", "123 ABC", "P3RM15");
		addDossier("Cipher", "Luc", "666 HEL", "4Permis5");
		addDossier("St-Hilaire", "Huguette", "456 DEF", "AsdfPermis");
		
	}
	
	public static GestionDonnees getInstance()
	{
		if (instance == null){
			instance = new GestionDonnees();
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
		
		storeData(Dossier.class, dossier);
		dossiers.add(dossier);
		
		System.out.println("Dossier ajouté!");
	}
	
	public ArrayList<Dossier> getDossiers ()
	{
		//Récupère tous les dossiers
		return dossiers;
	}
	
	public ArrayList<Infraction> getInfractions ()
	{
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
		storeData(Infraction.class, infraction);
		
		System.out.println("Infraction ajoutée");
	}
	
	public String validateLogin(String username, String password) throws InvalidLoginException
	{
		Utilisateur utilisateur = new Utilisateur();
		
		try{
			utilisateur = getData(Utilisateur.class, "login="+username, "password="+password).get(0);
		}
		catch(Exception e){
			throw new InvalidLoginException();
		}
		
		if(utilisateur == null){
			throw new InvalidLoginException();
		}
		
		return utilisateur.getLogin().toString();
		
	}
	
	private <T> void storeData(Class<T> classType, Object object){
		
		try{
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate((T) object);
			session.getTransaction().commit();
		}
		
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
	private <T> ArrayList<T> getData(Class<T> classType){
		
		ArrayList<T> objectsList = new ArrayList<T>();
		
		try{
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			objectsList = (ArrayList<T>) session.createQuery("from " + classType).list();
			session.getTransaction().commit();
		}
		
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return objectsList;
	}
	
	private <T> ArrayList<T> getData(Class<T> classType, String ...parameters){
		
		ArrayList<T> objects = null;
		String sqlQuery = "from " + classType.getCanonicalName() + " where";
		try{
			Session session = sessionFactory.getCurrentSession();
			
			session.beginTransaction();
			for (int i = 0; i < parameters.length; i++){
				sqlQuery += " " + parameters[i];
				if(i < parameters.length - 1){
					sqlQuery += " and";
				}
			}
			
			org.hibernate.Query query = session.createQuery(sqlQuery);
			objects = (ArrayList<T>) query.list();
			session.getTransaction().commit();
		}
		
		catch(Exception e){
	
			System.out.println("ERREUR DURANT LA RECUPERATION (" + classType + ", SQL : " + sqlQuery + ") : " + e);
		}
		
		
		return objects;
	}
}