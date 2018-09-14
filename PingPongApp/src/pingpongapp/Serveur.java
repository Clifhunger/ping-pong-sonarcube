/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp;

import java.io.*;
import static java.lang.Integer.parseInt;
import java.net.*;
import java.util.Properties;
import java.util.logging.Logger;
import pingpongapp.etat.*;

/**
 *
 * @author ecossard
 */
public class Serveur extends Joueur {
    private int portEcoute;
    private ServerSocket socketServeur = null;
    private Socket socketClient = null;
    private static final Logger LOG = Logger.getGlobal();
    private static final int SCORE_MAX = 11;

    public Serveur() {
        super();
        unePartie = new ParamPartie(SCORE_MAX);

        Properties ipProps = super.runConfig();
        portEcoute = parseInt(ipProps.getProperty("app.port"));
        String portEcouteString = String.valueOf(portEcoute);
        LOG.info(portEcouteString);
        setEtat(super.getEtatRepos());
        try {
            socketServeur = new ServerSocket(portEcoute);
        } catch (IOException e) {
            LOG.info("Creation de la socket impossible : " + e);
        }

    }

    /**
     * @return the portEcoute
     */
    public int getPortEcoute() {
        return portEcoute;
    }

    /**
     * @param portEcoute the portEcoute to set
     */
    public void setPortEcoute(int portEcoute) {
        this.portEcoute = portEcoute;
    }

    public void lancerConnexion() {

        // Attente d'une connexion d'un client
        Socket localeSocketClient = null;
        try {
            LOG.info("Attente de connexion");
            localeSocketClient = socketServeur.accept();
            LOG.info("Connexion OK");
        } catch (IOException e) {
            LOG.info("Erreur lors de l'attente d'une connexion : " + e);
        }
        // Association d'un flux d'entree et de sortie
        if (localeSocketClient != null) {
            try {
                input = new ObjectInputStream(localeSocketClient.getInputStream());
                output = new ObjectOutputStream(localeSocketClient.getOutputStream());

            } catch (IOException e) {
                LOG.info("Association des flux impossible : " + e);
            }
        }

    }

    @Override
    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    @Override
    public void close() {
        try {
            input.close();
            output.close();
            socketClient.close();
            socketServeur.close();
        } catch (IOException e) {
            LOG.info("Erreur lors de la fermeture des flux et des sockets : " + e);
        }
    }

    @Override
    public Socket getSocket() {
        return socketClient;
    }

}
