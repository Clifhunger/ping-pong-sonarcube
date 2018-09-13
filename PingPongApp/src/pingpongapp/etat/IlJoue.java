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
        Object objet=null;
        try
            {
                  objet=joueur.getInput().readObject();
                  System.out.print("j'ai reçu un ");
                  System.out.println(objet);
            }
             catch(Exception e)
                 {
                     e.printStackTrace();
                     System.out.println(e.getMessage());
                 }
        if(objet instanceof Pong)
        {
             joueur.setEtat(joueur.getEtatJeJoue());
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
