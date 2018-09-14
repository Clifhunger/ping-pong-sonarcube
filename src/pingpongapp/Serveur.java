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
import java.util.logging.Level;
import java.util.logging.Logger;
import pingpongapp.etat.*;

/**
 *
 * @author ecossard
 */
public class Serveur extends Joueur {
    /**
     * le numero de port d'écoute utilisé par le serveur
     */
    private static int portEcoute;
    /**
     * le scoket serveur
     */
    private ServerSocket socketServeur = null;
    /**
     * la socket client côté serveur
     */
    private Socket socketClient = null;

    private static final Logger LOG = Logger.getGlobal();

    public Serveur() {
        super();
        int scoreMax = 11;
        unePartie = new ParamPartie(scoreMax);

        // recuperation du port dans le fichier config
        Properties ipProps = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream("src\\pingpongapp\\config.properties");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ipProps.load(in);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        portEcoute = parseInt(ipProps.getProperty("app.port"));
        LOG.info(String.valueOf(portEcoute));

        setEtat(super.getEtatRepos()); // Etat par défault
        try {
            socketServeur = new ServerSocket(portEcoute); // création de la socket
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

    /**
     * Permet détablir la connexion avec une serveur
     */
    public void lancerConnexion() {

        // Attente d'une connexion d'un client
        Socket localSocketClient = null;
        try {
            LOG.info("Attente de connexion");
            localSocketClient = socketServeur.accept();
            LOG.info("Connexion OK");
        } catch (IOException e) {
            LOG.info("Erreur lors de l'attente d'une connexion : " + e);
        }
        // Association d'un flux d'entree et de sortie
        if (localSocketClient != null) {
            try {
                input = new ObjectInputStream(localSocketClient.getInputStream());
                output = new ObjectOutputStream(localSocketClient.getOutputStream());

            } catch (IOException e) {
                LOG.info("Association des flux impossible : " + e);
            }
        }

    }

    @Override
    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    /**
     * Permet de se déconnecter du serveur et de fermer les flux
     */
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
}
