/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.etat;

import java.io.IOException;
import pingpongapp.Joueur;
import pingpongapp.PDU.Ackinit;

/**
 *
 * @author clifhunger
 */
public class InitActive implements Etat{
    private Joueur joueur;

    public InitActive(Joueur joueur) {
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
        // attente de la réponse du serveur
         Ackinit reponse=null;
           try
            {
                   System.out.println("Attente de la réponse");
                   reponse=(Ackinit) joueur.getInput().readObject(); 
                   System.out.print("j'ai reçus la réponse du serveur : ");
                   System.out.println(reponse);
            }
             catch(Exception e)
                 {
                     e.printStackTrace();
                     System.out.println(e.getMessage());
                 }
         if(reponse.getMessage().equals("non"))
         {
             System.out.println("fermeture de l'application");
             joueur.close();
              System.exit(0);
         }
         else
         {
              joueur.setEtat(joueur.getEtatIlSert());
         }
          
    }

    @Override
    public void echange() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
