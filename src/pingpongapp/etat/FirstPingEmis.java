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
public class FirstPingEmis implements Etat{
   private Joueur joueur;

    public FirstPingEmis(Joueur joueur) {
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
        System.out.println("1 er ping emis");
         Object objet=null;
         try
            {
                   objet=joueur.getInput().readObject();                        //lecture du flux d'entrée
                   
            }
             catch(Exception e)
                 {
                     e.printStackTrace();
                     System.out.println(e.getMessage());
                 }
         if (objet instanceof Pong)                                             // si je reçois un Pong  
         {
               joueur.setEtat(joueur.getEtatJeJoue());                              //passage à l'état je joue
               System.out.print("j'ai reçus un ");
               System.out.println(objet);
         }
         if(objet instanceof Ace)                                               // si je reçois un Ace
         {
             System.out.println(objet);
                joueur.setEtat(joueur.getEtatJeSers());                            // passage a l'état de service
                joueur.jeMarque();                                                 // mon score ++
             System.out.println("Fin de l'échange");
             System.out.println("==========================================");
         }
         if(objet instanceof Smash)                                             // si je rçois un Smash
         {
             joueur.setEtat(joueur.getEtatJeSers());                                //passage a l'état de service
             joueur.ilMarque();                                                     // son score ++
             System.out.println("Fin de l'échange");
             System.out.println("==========================================");
         }
        
    }
}
