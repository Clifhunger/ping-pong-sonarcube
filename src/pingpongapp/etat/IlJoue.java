/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.etat;

import pingpongapp.Joueur;
import pingpongapp.PDU.*;

/**
 *
 * @author clifhunger
 */
public class IlJoue implements Etat{
   private Joueur joueur;

    public IlJoue(Joueur joueur) {
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
        Object objet=null;
        try
            {
                  objet=joueur.getInput().readObject();                         //lecture du flux d'entrée
                  System.out.print("j'ai reçu un ");
                  System.out.println(objet);
            }
             catch(Exception e)
                 {
                     e.printStackTrace();
                     System.out.println(e.getMessage());
                 }
        if(objet instanceof Pong)                                               // si je reçois un Pong
        {
             joueur.setEtat(joueur.getEtatJeJoue());                                //passage a l'état je joue
        }
        if(objet instanceof Smash)                                              // si je reçois un Smash
        {
             joueur.setEtat(joueur.getEtatJeSers());                                //passage a l'état de service
             joueur.ilMarque();                                                     //son score ++
             System.out.println("Fin de l'échange");
             System.out.println("==========================================");
        }
    }
}
