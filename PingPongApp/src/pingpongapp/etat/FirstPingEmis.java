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
    public String getMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void attenteAck() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void echange() {
        System.out.println("1 er ping emis");
         Object objet=null;
         try
            {
                   objet=joueur.getInput().readObject();
                   
            }
             catch(Exception e)
                 {
                     e.printStackTrace();
                     System.out.println(e.getMessage());
                 }
         if (objet instanceof Pong)
         {
               joueur.setEtat(joueur.getEtatJeJoue());
               System.out.print("j'ai reçus un ");
               System.out.println(objet);
         }
         if(objet instanceof Ace)
         {
             System.out.println(objet);
             joueur.setEtat(joueur.getEtatJeSers());
             joueur.jeMarque();
             System.out.println("Fin de l'échange");
             System.out.println("==========================================");
         }
         if(objet instanceof Smash)
         {
             joueur.setEtat(joueur.getEtatJeSers());
             joueur.ilMarque();
             System.out.println("Fin de l'échange");
             System.out.println("==========================================");
         }
        
    }
}
