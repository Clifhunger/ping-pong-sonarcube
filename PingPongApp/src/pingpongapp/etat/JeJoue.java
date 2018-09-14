/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.etat;

import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import pingpongapp.Joueur;
import pingpongapp.PDU.*;

/**
 *
 * @author clifhunger
 */
public class JeJoue extends Etat {
    private Joueur joueur;
    private static final Logger LOG = Logger.getGlobal();

    public JeJoue(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public void echange() {

        Object cout;
        Random rand = new SecureRandom();

        if ((rand.nextInt(100) + 1) <= joueur.getProbaSmash()) {
            cout = new Smash();
            joueur.jeMarque();
            joueur.setEtat(joueur.getEtatJeSers());
        } else {
            cout = new Ping();
            joueur.setEtat(joueur.getEtatIlJoue());
        }

        try {
            LOG.log(Level.INFO, "j envoi un {0}", cout);
            joueur.getOutput().writeObject(cout);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        if (cout instanceof Smash) {
            LOG.log(Level.INFO, "Fin de l'échange");
            LOG.log(Level.INFO, "==========================================");
        }
    }
}
