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
public class FirstPingEmis extends Etat {
    private Joueur joueur;
    private static final Logger LOG = Logger.getGlobal();

    public FirstPingEmis(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public void echange() {
        LOG.log(Level.INFO, "1 er ping emis");
        Object objet = null;
        try {
            objet = joueur.getInput().readObject();

        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        if (objet instanceof Pong) {
            joueur.setEtat(joueur.getEtatJeJoue());
            LOG.log(Level.INFO, "j'ai reçus un ");
            LOG.log(Level.INFO, "{0}", objet);
        }
        if (objet instanceof Ace) {
            LOG.log(Level.INFO, "{0}", objet);
            joueur.setEtat(joueur.getEtatJeSers());
            joueur.jeMarque();
            LOG.log(Level.INFO, "Fin de l'échange");
            LOG.log(Level.INFO, "==========================================");
        }
        if (objet instanceof Smash) {
            joueur.setEtat(joueur.getEtatJeSers());
            joueur.ilMarque();
            LOG.log(Level.INFO, "Fin de l'échange");
            LOG.log(Level.INFO, "==========================================");
        }

    }
}
