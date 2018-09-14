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
import pingpongapp.pdu.*;

/**
 *
 * @author clifhunger
 */
public class IlJoue extends Etat {
    private Joueur joueur;
    private static final Logger LOG = Logger.getGlobal();

    public IlJoue(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public void echange() {
        Object objet = null;
        try {
            objet = joueur.getInput().readObject();
            LOG.log(Level.INFO, "j'ai reçu un ");
            LOG.log(Level.INFO, "{0}", objet);
        } catch (IOException | ClassNotFoundException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        if (objet instanceof Pong) {
            joueur.setEtat(joueur.getEtatJeJoue());
        }
        if (objet instanceof Smash) {
            joueur.setEtat(joueur.getEtatJeSers());
            joueur.ilMarque();
            LOG.log(Level.INFO, "Fin de l'échange");
            LOG.log(Level.INFO, "==========================================");
        }
    }
}
