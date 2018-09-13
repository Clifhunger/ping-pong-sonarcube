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
public class FinPassive implements Etat{
     private Joueur joueur;

    public FinPassive(Joueur joueur) {
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
         try {  
                
                joueur.getOutput().writeObject(new AckFin("l'autre joueur a bien recus la Fin de Partie"));
              
               }
             catch(Exception e)
                {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            joueur.setEtat(joueur.getEtatRepos());
    }
    
}
