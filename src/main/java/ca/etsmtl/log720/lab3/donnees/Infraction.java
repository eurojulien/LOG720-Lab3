//Auteur: Alex Gagnon

package ca.etsmtl.log720.lab3.donnees;

public class Infraction {
	
	private int id;
	private String description;
	private int gravite;
	
	public Infraction(int id, String description, int gravite)
	{
		this.id = id;
		this.description = description;
		this.gravite = gravite;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public int getGravite()
	{
		return gravite;
	}
}
