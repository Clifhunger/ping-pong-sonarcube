/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp;

import java.io.*;
import java.net.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import pingpongapp.etat.*;

/**
 *
 * @author ecossard
 */
public abstract class Joueur {
    protected ParamPartie unePartie;
    protected static ObjectInputStream input = null;
    protected static ObjectOutputStream output = null;
    protected Etat etat;
    private Etat etatAttentePingSmash;
    private Etat etatFirstPingEmis;
    private Etat etatIlJoue;
    private Etat etatIlSert;
    private Etat etatInitActive;
    private Etat etatJeJoue;
    private Etat etatJeSers;
    private Etat etatRepos;
    private Etat etatFinActive;
    private Etat etatFinPassive;

    public Joueur() {
        etatAttentePingSmash = new AttentPingSmash(this);
        etatFirstPingEmis = new FirstPingEmis(this);
        etatIlJoue = new IlJoue(this);
        etatIlSert = new IlSert(this);
        etatInitActive = new InitActive(this);
        etatJeJoue = new JeJoue(this);
        etatJeSers = new JeSers(this);
        etatRepos = new Repos(this);
        etatFinActive = new FinActive(this);
        etatFinPassive = new FinPassive(this);
    }

    public abstract void lancerConnexion();

    /**
     *
     */
    public void init() {
        etat.init();
    }

    public void attenteAck() {
        etat.attenteAck();
    }

    public void echange() {
        etat.echange();
    }

    public abstract void setEtat(Etat etat);

    public abstract Socket getSocket();

    public Etat getEtat() {
        return etat;
    }

    public Etat getEtatAttentePingSmash() {
        return etatAttentePingSmash;
    }

    public Etat getEtatFirstPingEmis() {
        return etatFirstPingEmis;
    }

    public Etat getEtatIlJoue() {
        return etatIlJoue;
    }

    public Etat getEtatIlSert() {
        return etatIlSert;
    }

    public Etat getEtatInitActive() {
        return etatInitActive;
    }

    public Etat getEtatJeJoue() {
        return etatJeJoue;
    }

    public Etat getEtatJeSers() {
        return etatJeSers;
    }

    public Etat getEtatRepos() {
        return etatRepos;
    }

    public Etat getEtatFinActive() {
        return etatFinActive;
    }

    public Etat getEtatFinPassive() {
        return etatFinPassive;
    }

    public ObjectInputStream getInput() {
        return input;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

    public ParamPartie getUnePartie() {
        return unePartie;
    }

    public int getNumeroDuService() {
        return unePartie.getNumeroDuService();
    }

    public void resetNumeroDuService() {
        unePartie.resetNumeroDuService();
    }

    public void serviceSuivant() {
        unePartie.serviceSuivant();
    }

    public int getProbaSmash() {
        return unePartie.getProbaSmash();
    }

    public int getProbaAceAdversaire() {
        return unePartie.getProbaAceAdversaire();
    }

    public int getProbaAbandon() {
        return unePartie.getProbaAbandon();
    }

    public void jeMarque() {
        unePartie.jeMarque();
    }

    public void ilMarque() {
        unePartie.ilMarque();
    }

    public int getMonScore() {
        return unePartie.getMonScore();
    }

    public int getSonScore() {
        return unePartie.getSonScore();
    }

    public int getScoreMax() {
        return unePartie.getScoreMax();
    }

    public abstract void close();

    public Properties runConfig() {
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
        return ipProps;
    }

}
