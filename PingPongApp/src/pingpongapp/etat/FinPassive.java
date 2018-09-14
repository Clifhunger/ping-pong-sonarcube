/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.etat;

import java.util.logging.Level;
import java.util.logging.Logger;

import pingpongapp.Joueur;
import pingpongapp.PDU.*;

/**
 *
 * @author clifhunger
 */
public class FinPassive implements Etat {
    private Joueur joueur;
    private static final String NOT_SUPPORTED = "Not supported yet.";
    private static final Logger LOG = Logger.getGlobal();

    public FinPassive(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public String getMessage() {
        LOG.log(Level.INFO, "finPassive - getMessage");
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    @Override
    public void init() {
        LOG.log(Level.INFO, "finPassive - init");
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    @Override
    public void attenteAck() {
        LOG.log(Level.INFO, "finPassive - attenteAck");
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    @Override
    public void echange() {
        try {

            joueur.getOutput().writeObject(new AckFin("l'autre joueur a bien recus la Fin de Partie"));

        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        joueur.setEtat(joueur.getEtatRepos());
    }

}
