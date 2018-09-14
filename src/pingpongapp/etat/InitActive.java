/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.etat;

import java.util.logging.Logger;

import pingpongapp.Joueur;
import pingpongapp.PDU.Ackinit;

/**
 *
 * @author clifhunger
 */
public class InitActive implements Etat {
    private static final Logger LOG = Logger.getGlobal();
    private Joueur joueur;

    public InitActive(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public void init() {
        throw new java.lang.IllegalStateException("Méthode inaccessible par cet état"); // To change body of generated
                                                                                        // methods, choose Tools |
                                                                                        // Templates.
    }

    @Override
    public void attenteAck() {
        // attente de la réponse du serveur
        Ackinit reponse = null;
        try {
            LOG.info("Attente de la réponse");
            reponse = (Ackinit) joueur.getInput().readObject();
            LOG.info("j'ai reçus la réponse du serveur : ");
            LOG.info(reponse.toString());
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        if (reponse != null) {
            if ("non".equals(reponse.getMessage())) // si le serveur refuse la partie
            {
                LOG.info("fermeture de l'application");
                joueur.close(); // fermeture des flux et de la scoket
            } else // si le serveur accepte la partie
            {
                joueur.setEtat(joueur.getEtatIlSert()); // passage à l'état de service
            }
        }

    }

    @Override
    public void echange() {
        throw new java.lang.IllegalStateException("Méthode inaccessible par cet état"); // To change body of generated
                                                                                        // methods, choose Tools |
                                                                                        // Templates.
    }
}
