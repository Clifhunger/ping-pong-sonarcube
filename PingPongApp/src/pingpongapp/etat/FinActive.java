/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.etat;

import java.util.logging.Level;
import java.util.logging.Logger;

import pingpongapp.Joueur;

/**
 *
 * @author clifhunger
 */
public class FinActive implements Etat {
    private Joueur joueur;
    private static final Logger LOG = Logger.getGlobal();

    public FinActive(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public String getMessage() {
        LOG.log(Level.INFO, "finActive - getMessage");
        return joueur.toString();
    }

    @Override
    public void init() {
        LOG.log(Level.INFO, "finActive - init");
    }

    @Override
    public void attenteAck() {
        LOG.log(Level.INFO, "finActive - attenteAck");
    }

    @Override
    public void echange() {
        try {
            LOG.log(Level.INFO, "fin de la partie");
            Object ackFin = joueur.getInput().readObject();
            LOG.log(Level.INFO, "{0}", ackFin);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        joueur.setEtat(joueur.getEtatRepos());
    }
}
