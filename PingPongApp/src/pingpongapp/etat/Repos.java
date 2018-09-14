/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.etat;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import pingpongapp.*;
import pingpongapp.PDU.*;

/**
 *
 * @author clifhunger
 */
public class Repos extends Etat {
    private Joueur joueur;
    private static final Logger LOG = Logger.getGlobal();

    public Repos(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public String getMessage() {
        return "Etat de Repos";
    }

    @Override
    public void init() {
        if (joueur instanceof Client) {
            jeSuisJoueur();

        }
        if (joueur instanceof Serveur) {
            jeSuisServeur();
        }

    }

    private void jeSuisJoueur() {
        // envoie de la demande de partie au serveur
        joueur.lancerConnexion();
        LOG.log(Level.INFO, "je suis un client j'envoi une demande de partie");
        Init init = new Init(11);
        try {

            joueur.getOutput().writeObject(init);
            LOG.log(Level.INFO, "envoie");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        joueur.setEtat(joueur.getEtatInitActive());
    }

    private void jeSuisServeur() {
        LOG.log(Level.INFO, "je suis un serveur à l'état de repos");
        joueur.lancerConnexion();
        Init objet = null;
        // attente de demande
        try {
            LOG.log(Level.INFO, "Attente de la reception d'une demande de partie");
            objet = (Init) joueur.getInput().readObject();
            LOG.log(Level.INFO, "j ai recus une demande : ");
            LOG.log(Level.INFO, "{0}", objet);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }

        // envoie de la reponse
        Ackinit reponse;
        if (objet != null && joueur.getScoreMax() == objet.getScoreMax()) {
            reponse = new Ackinit("oui");
            joueur.setEtat(joueur.getEtatJeSers());
            try {

                joueur.getOutput().writeObject(reponse);
                LOG.log(Level.INFO, "envoie de la réponse");
            } catch (Exception e) {
                LOG.log(Level.SEVERE, e.getMessage(), e);
            }
        } else {

            reponse = new Ackinit("non");
            LOG.log(Level.INFO, " ===== je refuse la partie ===== ");
            LOG.log(Level.INFO, "{0}", objet);
            try {
                joueur.getOutput().writeObject(reponse);
                LOG.log(Level.INFO, "envoie de la réponse");
            } catch (Exception e) {
                LOG.log(Level.SEVERE, e.getMessage(), e);
            }
            try {
                joueur.getInput().close();
                joueur.getOutput().close();

            } catch (IOException e) {
                LOG.log(Level.SEVERE, e.getMessage(), e);
                return;
            }

        }
    }
}
