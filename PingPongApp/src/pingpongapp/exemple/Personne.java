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
	private static final long serialVersionUID = -4315822854881361218L;
	private static final Logger LOG = Logger.getGlobal();
	public final String nom;
	public final String prenom;
	public final int age;

	public Personne(String unNom, String unPrenom, int unAge) {
		nom = unNom;
		prenom = unPrenom;
		age = unAge;
	}

	public void affiche() {
		LOG.log(Level.INFO, "Nom : {0}", this.nom);
		LOG.log(Level.INFO, "Prenom : {0}", this.prenom);
		LOG.log(Level.INFO, "Age : {0}", this.age);
	}
}
