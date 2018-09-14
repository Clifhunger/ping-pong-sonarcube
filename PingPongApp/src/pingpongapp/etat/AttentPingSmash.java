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
public class AttentPingSmash extends Etat {
    private Joueur joueur;
    private static final int HUNDRED = 100;
    private static final Logger LOG = Logger.getGlobal();

    public AttentPingSmash(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public void echange() {
        Object objet = null;
        try {
            objet = joueur.getInput().readObject();
            LOG.log(Level.INFO, "j ai recus un {0}", objet);
            LOG.info((String) objet);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        if (objet instanceof Ping) {
            Object unCout;

            Random rand = new SecureRandom();
            LOG.log(Level.INFO, "j'envoi un ");
            if ((rand.nextInt(HUNDRED) + 1) <= joueur.getProbaSmash()) {
                unCout = new Pong();
                LOG.log(Level.INFO, "{0}", unCout);

            } else {
                unCout = new Smash();
                LOG.log(Level.INFO, "{0}", unCout);
                joueur.setEtat(joueur.getEtatIlSert());
                joueur.jeMarque();
                LOG.log(Level.INFO, "Fin de l'échange");
                LOG.log(Level.INFO, "==========================================");
            }

            try {
                joueur.getOutput().writeObject(unCout);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        if (objet instanceof Smash) {
            joueur.setEtat(joueur.getEtatIlSert());
            joueur.ilMarque();
            LOG.log(Level.INFO, "Fin de l'échange");
            LOG.log(Level.INFO, "==========================================");
        }

    }

}
