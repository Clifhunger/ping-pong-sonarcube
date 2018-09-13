/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp;

import java.io.*;
import java.net.*;
import pingpongapp.etat.*;

/**
 *  Represente un joueur de la partie
 * @author ecossard
 */
public abstract class Joueur {
    /**
     * les paramètres de la parties en cours
     */
    protected ParamPartie unePartie;
    /**
     * le flux d'entrée pour la communication Client/Serveur
     */
    protected static ObjectInputStream input = null;
    /**
     * le flux de sortie pour la communication Client/serveur
     */
    protected static ObjectOutputStream output = null;
    /**
     * Instance de l'Etat en cours
     */
    protected Etat etat;
    /**
     * Instance de l'Etat AttentePingSmash
     */
    private Etat etatAttentePingSmash;
    /**
     * Instance de l'Etat FirstPingEmis
     */
    private Etat etatFirstPingEmis;
    /**
     * Instance de l'Etat IlJoue
     */
    private Etat etatIlJoue;
    /**
     * Instance de l'Etat IlSert
     */
    private Etat etatIlSert;
    /**
     * Instance de l'Etat InitActive
     */
    private Etat etatInitActive;
    /**
     * Instance de l'Etat JeJoue
     */
    private Etat etatJeJoue;
    /**
     * Instance de l'Etat JeSers
     */
    private Etat etatJeSers;
    /**
     * Instance de l'Etat Repos
     */
    private Etat etatRepos;
    /**
     * Instance de l'Etat FinActive
     */
    private Etat etatFinActive;
    /**
     * Instance de l'Etat FinPassive
     */
    private Etat etatFinPassive;
    /**
     * Constructeur de la classe Joueur
     */
    public Joueur()
    {
        etatAttentePingSmash=new AttentPingSmash(this);
        etatFirstPingEmis=new FirstPingEmis(this);
        etatIlJoue=new IlJoue(this);
        etatIlSert=new IlSert(this);
        etatInitActive=new InitActive(this);
        etatJeJoue=new JeJoue(this);
        etatJeSers=new JeSers(this);
        etatRepos=new Repos(this);
        etatFinActive=new FinActive(this);
        etatFinPassive=new FinPassive(this);
    }
    /**
     * permet de lancer une connexion
     */
    public abstract void lancerConnexion();
    
    /**
     * Permet de lancer la méthode d'initialisation dans l'état courent
     */
    public  void init()
    {
        etat.init();
    }
    /**
     * Permet de lancer la méthode atttenteAck de l'etat en cours
     */
    public void attenteAck()
    {
        etat.attenteAck();
    }
    /**
     * Permet de lancer la méthode echange de l'etat en cours
     */
    public void echange()
    {
        etat.echange();
    }
    /**
     * Permet de changer l'etat courrant
     * @param etat 
     *              le nouvel etat courrant
     */
    public abstract void setEtat(Etat etat);
    /**
     * Permet de recuperer l'état courrant
     * @return une instance de Etat
     */
    public Etat getEtat() {
        return etat;
    }
    /**
     * Permet de recuperer l'attribut etatFirstPingEmis
     * @return une Instance de AttentePingSmash
     */
    public Etat getEtatAttentePingSmash() {
        return etatAttentePingSmash;
    }
     /**
     * Permet de recuperer l'attribut etatFirstPingEmis
     * @return une Instance de FirstPingEmis
     */
    public Etat getEtatFirstPingEmis() {
        return etatFirstPingEmis;
    }
    /**
     * Permet de recuperer l'attribut etatIlJoue
     * @return une Instance de IlJoue 
     */
    public Etat getEtatIlJoue() {
        return etatIlJoue;
    }
     /**
     * Permet de recuperer l'attribut etatIlSert
     * @return une Instance de IlSert
     */
    public Etat getEtatIlSert() {
        return etatIlSert;
    }
     /**
     * Permet de recuperer l'attribut etatInitActive
     * @return une Instance de InitActive 
     */
    public Etat getEtatInitActive() {
        return etatInitActive;
    }
     /**
     * Permet de recuperer l'attribut etatJeJoue
     * @return une Instance de JeJoue 
     */
    public Etat getEtatJeJoue() {
        return etatJeJoue;
    }
     /**
     * Permet de recuperer l'attribut etatJeSers
     * @return une Instance de JeSers
     */
    public Etat getEtatJeSers() {
        return etatJeSers;
    }
     /**
     * Permet de recuperer l'attribut etatRepos
     * @return une Instance de Repos
     */
    public Etat getEtatRepos() {
        return etatRepos;
    }
     /**
     * Permet de recuperer l'attribut etatFinActive
     * @return une Instance de FinActive
     */
    public Etat getEtatFinActive() {
        return etatFinActive;
    }
     /**
     * Permet de recuperer l'attribut etatFinPassive
     * @return une Instance de FinPassive
     */
    public Etat getEtatFinPassive() {
        return etatFinPassive;
    }
       /**
        * Permet de recuperer le flux d'entée
        * @return une instance de ObjectInputStream
        */
     public ObjectInputStream getInput() {
        return input;
    }
     /**
      * Permet de recuperer le flux de sortie
      * @return  une instance de ObjectOutputStream
      */
    public ObjectOutputStream getOutput() {
        return output;
    }
    /**
     * Permet de recuperer les paramètres de la partie
     * @return une instance de ParamPartie
     */
    public ParamPartie getUnePartie() {
        return unePartie;
    }
    /**
     * Permet de recuperer le numéro du service
     * @return un entier
     */
    public int getNumeroDuService() {
        return unePartie.getNumeroDuService();
    }
    /**
     * reset le numéro du service
     */
     public void resetNumeroDuService() {
       unePartie.resetNumeroDuService();
    }
     /**
      * incrémenter le numéro du service
      */
    public void serviceSuivant()
    {
        unePartie.serviceSuivant();
    }
    /**
     *  Permet de recuperer la probabilité de mon smsh
     * @return un entier
     */
     public int getProbaSmash() {
        return unePartie.getProbaSmash();
    }
    /**
     * Permet de recuperer la probabilité de l'ace du joueur adverse
     * @return un entier
     */
    public int getProbaAceAdversaire() {
        return unePartie.getProbaAceAdversaire();
    }
    /**
     * Permet de recuperer la probabilité de l'abandon du joueur
     * @return un entier
     */
    public int getProbaAbandon() {
        return unePartie.getProbaAbandon();
    }
    /**
     * Permet d'ajouter un point à mon score
     */
    public void jeMarque()
    {
        unePartie.jeMarque();
    }
     /**
     * Permet d'ajouter un point à son score
     */
    public void ilMarque()
    {
        unePartie.ilMarque();
    }
    /**
     * Permet de recuperer mon score
     * @return un entier
     */
     public int getMonScore() {
        return  unePartie.getMonScore();
    }
     /**
     * Permet de recuperer le score de l'adversaire
     * @return un entier
     */
    public int getSonScore() {
        return  unePartie.getSonScore();
    }
    /**
     * Permet de recuperer le score max d'une partie
     * @return un entier
     */
    public int getScoreMax() {
        return unePartie.getScoreMax();
    }
    /**
     * Permet modifier le score max d'une partie
     */
     public void setScoreMax(int scoreMax)
    {
        unePartie.setScoreMax(scoreMax);
    }
     /**
      * permet de fermer les sockets ainsi que les flux
      */
    public abstract void close();
}

