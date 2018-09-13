/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author ecossard
 */
public class PingPongApp {

    private static final Logger LOG = Logger.getGlobal();

    private PingPongApp() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean bool = true;
        Joueur joueur = null;

        do {
            LOG.info("Créer ou rechercher une partie ?");
            LOG.info("1 : Créer      2 : Rechercher    3 : Quitter");

            switch (sc.nextInt()) {
            case 1:
                joueur = new Serveur();

                bool = false;
                break;
            case 2:
                joueur = new Client();
                bool = false;
                break;
            case 3:
                System.exit(0);
                break;
            default:
            }
        } while (bool);
        sc.close();
        while (joueur.getEtat() == joueur.getEtatRepos()) {
            joueur.init();
        }
        LOG.info("je suis sortie de l'état de repos");
        if (joueur instanceof Client) {
            joueur.attenteAck();
        }
        while (joueur.getEtatRepos() != joueur.getEtat()) {
            joueur.echange();
        }
        try {
            joueur.getInput().close();
            joueur.getOutput().close();

        } catch (IOException e) {
            LOG.info("Erreur lors de la fermeture des flux et des sockets : " + e);
            System.exit(-1);
        }

    }

}
