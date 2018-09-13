/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.exemple;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gwen
 */
public class Personne implements Serializable {
	private static final Logger LOG=Logger.getGlobal();
	public String Nom;
	public String Prenom;
	public int Age;
	public Personne(String unNom, String unPrenom, int unAge){
		Nom = unNom;
		Prenom = unPrenom;
		Age = unAge;		
	}
	
	public void affiche() {
		LOG.log(Level.INFO,"Nom : " + this.Nom);
		LOG.log(Level.INFO,"Prenom : " + this.Prenom);
		LOG.log(Level.INFO,"Age : " + this.Age);
	}
}
