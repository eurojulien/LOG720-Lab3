//Auteur: Alex Gagnon et Julien Drolet Noel

package ca.etsmtl.log720.lab3.beans; 

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ca.etsmtl.log720.lab3.exceptions.InvalidLoginException;
import ca.etsmtl.log720.lab3.objetsDonnees.*;

public class GestionDonnees 
{ 
	
	// Hibernate
	private static Configuration conf 					= null;
	private static StandardServiceRegistryBuilder sr	= null;
	private static SessionFactory sessionFactory		= null;
	
	private static GestionDonnees instance = null;
	
	ArrayList<Dossier> dossiers = null;
	ArrayList<Infraction> infractions = null;

	private GestionDonnees(){
		
		try{
			conf 	= new Configuration().configure("hibernate.cfg.xml");
			System.out.println(" -- CONFIGURATION SUCCEEDED --");
			sr		= new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
			System.out.println(" -- SERVICE REGISTRY SUCCEEDED --");
			sessionFactory = conf.buildSessionFactory(sr.build());
			System.out.println(" -- SESSION FACTORY SUCCEEDED --");
		}
		catch(Exception e){
			System.out.println("Session factory FAILED !!! :" + e.getMessage());
		}
		
		initData();
		
	}
	
	public static GestionDonnees getInstance()
	{
		if (instance == null){
			instance = new GestionDonnees();
		}
		
		return instance;
	}
	
	private void initData(){
		
		dossiers 	= new ArrayList<Dossier>();
		infractions = new ArrayList<Infraction>();
		
		dossiers.addAll(getData(Dossier.class));
		infractions.addAll(getData(Infraction.class));
	
		if (infractions.size() == 0){
			addInfraction("Exc�s de vitesse", 1);
			addInfraction("Conduite imprudente", 3);
			addInfraction("Conduite avec facult�s affaiblies", 5);
			addInfraction("Chasse au chevreuil", 10);
		}
		
		if(dossiers.size() == 0){
			addDossier("Bleau", "Jos", "123 ABC", "P3RM15");
			addDossier("Cipher", "Luc", "666 HEL", "4Permis5");
			addDossier("St-Hilaire", "Huguette", "456 DEF", "AsdfPermis");
		}
	}

	public Dossier getDossier (int ID)
	{
		//Retourne un dossier sp�cifique
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
		
		System.out.println("Dossier ajout�!");
	}
	
	public ArrayList<Dossier> getDossiers ()
	{
		dossiers.clear();
		
		try{
			dossiers = getData(Dossier.class);
		}
		catch(Exception e){
			System.out.println(" Erreur getDossiers : " + e.getMessage());
		}
		
		//R�cup�re tous les dossiers
		return dossiers;
	}
	
	public ArrayList<Infraction> getInfractions ()
	{
		infractions.clear();
		
		try{
			infractions = getData(Infraction.class);
		}
		catch(Exception e){
			System.out.println(" Erreur getInfractions : " + e.getMessage());
		}	
	
		return infractions;
	}
	
	public Infraction getInfractionDossier(int idDossier, int idInfraction)
	{
		//Retourne une infraction sp�cifique
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
		System.out.println("Infraction ajout�e au dossier");
	}
	
	public void addInfraction(String description, int gravite)
	{
		Infraction infraction = new Infraction();
		
		infraction.setId(infractions.size() + 1);
		infraction.setDescription(description);
		infraction.setNiveaugravite(gravite);
		
		infractions.add(infraction);
		
		storeData(Infraction.class, infraction);
		
		System.out.println("Infraction ajout�e");
	}
	
	public String validateLogin(String username, String password) throws InvalidLoginException
	{
		Utilisateur utilisateur = new Utilisateur();
		
		try{
			utilisateur = getData(Utilisateur.class, "login = '"+username+"'", "userpassword = '"+password+"'").get(0);
		}
		
		catch(Exception e){
			System.out.println("Erreur dans validateLogin : " + e.getMessage());
		}
		
		if (utilisateur.getRole() != null){
			return utilisateur.getLogin().toString();
		}
		
		throw new InvalidLoginException();
	}
	

	private <T> void storeData(Class<T> classType, Object object){
		
		try{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate((T) object);
			session.getTransaction().commit();
			session.close();
		}
		
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	private <T> ArrayList<T> getData(Class<T> classType){
		
		ArrayList<T> objectsList = new ArrayList<T>();
		
		try{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			objectsList = ((ArrayList<T>) session.createQuery("from " + classType.getCanonicalName()).list());
			session.getTransaction().commit();
			session.close();
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
			Session session = sessionFactory.openSession();
			
			session.beginTransaction();
			for (int i = 0; i < parameters.length; i++){
				sqlQuery += " " + parameters[i];
				if(i < parameters.length - 1){
					sqlQuery += " and";
				}
			}
			
			org.hibernate.Query query = session.createQuery(sqlQuery);
			objects = ((ArrayList<T>) query.list());
			session.getTransaction().commit();
			session.close();
		}
		
		catch(Exception e){
	
			System.out.println("ERREUR DURANT LA RECUPERATION (" + classType + ", SQL : " + sqlQuery + ") : " + e);
		}
		
		
		return objects;
	}
}