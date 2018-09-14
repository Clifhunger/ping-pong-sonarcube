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
    private static int portEcoute;
    private ServerSocket socketServeur = null;
    private Socket socketClient = null;
    private static final Logger LOG = Logger.getGlobal();

    public Serveur() {
        super();
        unePartie = new ParamPartie(11);

        Properties ipProps = super.runConfig();
        portEcoute = parseInt(ipProps.getProperty("app.port"));
        LOG.info(String.valueOf(portEcoute));
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
    public static int getPortEcoute() {
        return portEcoute;
    }

    /**
     * @param portEcoute the portEcoute to set
     */
    public static void setPortEcoute(int portEcoute) {
        Serveur.portEcoute = portEcoute;
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
