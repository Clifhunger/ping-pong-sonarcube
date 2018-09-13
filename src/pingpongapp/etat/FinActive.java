/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.etat;

import java.io.IOException;
import pingpongapp.Joueur;
import pingpongapp.PDU.*;

/**
 *
 * @author clifhunger
 */
public class FinActive implements Etat{
    private Joueur joueur;

    public FinActive(Joueur joueur) {
        this.joueur = joueur;
    }
   

    @Override
    public void init() {
        throw new java.lang.IllegalStateException ("Méthode inaccessible par cet état"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void attenteAck() {
       throw new java.lang.IllegalStateException ("Méthode inaccessible par cet état"); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void echange() {
        try {
               System.out.println("fin de la partie");
               Object ackFin=joueur.getInput().readObject();                    //lecture du flux d'entrée
               System.out.println(ackFin);
            } 
            catch(Exception e)
                {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
          joueur.setEtat(joueur.getEtatRepos());                                //passage à l'état de repos
    }
}
