/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.exemple;

import java.io.Serializable;

/**
 *
 * @author Gwen
 */
public class Personne implements Serializable {

	public String Nom;
	public String Prenom;
	public int Age;
	public Personne(String unNom, String unPrenom, int unAge){
		Nom = unNom;
		Prenom = unPrenom;
		Age = unAge;		
	}
	
	public void affiche() {
	    System.out.println("Nom : " + this.Nom);
	    System.out.println("Prenom : " + this.Prenom);
	    System.out.println("Age : " + this.Age);
	}
}
