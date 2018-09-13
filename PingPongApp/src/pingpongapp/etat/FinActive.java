/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.etat;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import pingpongapp.Joueur;
import pingpongapp.PDU.*;

/**
 *
 * @author clifhunger
 */
public class FinActive implements Etat{
    private Joueur joueur;
    private static final String NOT_SUPPORTED="Not supported yet.";
    private static final Logger LOG=Logger.getGlobal();

    public FinActive(Joueur joueur) {
        this.joueur = joueur;
    }
    @Override
    public String getMessage() {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    @Override
    public void attenteAck() {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }
    
    @Override
    public void echange() {
        try {
        	   LOG.log(Level.INFO,"fin de la partie");
               Object ackFin=joueur.getInput().readObject();
               LOG.log(Level.INFO,"{0}",ackFin);
            } 
            catch(Exception e)
                {
            	  LOG.log(Level.SEVERE, e.getMessage(),e);
                }
          joueur.setEtat(joueur.getEtatRepos());
    }
}
