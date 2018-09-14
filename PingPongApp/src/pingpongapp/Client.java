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
public class Client extends Joueur {
    private Socket socket = null;

    public Client() {
        super();
        unePartie = new ParamPartie();
        setEtat(super.getEtatRepos());
    }

    public void lancerConnexion() {

        // recuperation de la config
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
        try {
            Logger.getLogger(Client.class.getName()).info(String.valueOf(ipProps.getProperty("app.port")));
            socket = new Socket(InetAddress.getByName(ipProps.getProperty("app.ipServeur")),
                    parseInt(ipProps.getProperty("app.port")));// ici a finir=====================================
        } catch (UnknownHostException e) {
            Logger.getLogger(Client.class.getName()).info("Erreur sur l'hôte : " + e);
        } catch (IOException e) {
            Logger.getLogger(Client.class.getName()).info("Creation de la socket impossible : " + e);
        }
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            Logger.getLogger(Client.class.getName()).info("Association des flux impossible : " + e);
        }
        Logger.getLogger(Client.class.getName()).info("Connexion OK");

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
            socket.close();
        } catch (IOException e) {
            Logger.getLogger(Client.class.getName()).info("Erreur lors de la fermeture des flux et des sockets : " + e);
        }
    }

    @Override
    public Socket getSocket() {
        return socket;
    }

}
