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
public class FinPassive extends Etat {
    private Joueur joueur;
    private static final Logger LOG = Logger.getGlobal();

    public FinPassive(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public void echange() {
        try {

            joueur.getOutput().writeObject(new AckFin("l'autre joueur a bien recus la Fin de Partie"));

        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        joueur.setEtat(joueur.getEtatRepos());
    }

}
