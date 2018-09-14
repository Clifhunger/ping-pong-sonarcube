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
import pingpongapp.PDU.Ackinit;

/**
 *
 * @author clifhunger
 */
public class InitActive implements Etat {
    private Joueur joueur;
    private static final String NOT_SUPPORTED = "Not supported yet.";
    private static final Logger LOG = Logger.getGlobal();

    public InitActive(Joueur joueur) {
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
        // attente de la réponse du serveur
        Ackinit reponse = null;
        try {
            LOG.log(Level.INFO, "Attente de la réponse");
            reponse = (Ackinit) joueur.getInput().readObject();
            LOG.log(Level.INFO, "j ai reçus la réponse du serveur : ");
            LOG.log(Level.INFO, "{0}", reponse);
        } catch (IOException | ClassNotFoundException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }

        if (reponse != null) {
            if ("non".equals(reponse.getMessage())) {
                LOG.log(Level.INFO, "fermeture de l'application");
                joueur.close();
            } else {
                joueur.setEtat(joueur.getEtatIlSert());
            }
        }
    }

    @Override
    public void echange() {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }
}
