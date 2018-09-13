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
     public static int portEcoute;
     /**
      * le scoket serveur
      */
     private ServerSocket socketServeur=null;
     /**
      * la socket client côté serveur
      */
     private Socket socketClient = null;
    public Serveur()
    {
        super();
        int scoreMax=11;
        unePartie=new ParamPartie(scoreMax);
        
                                                                                    //recuperation du port dans le fichier config
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
        portEcoute=parseInt(ipProps.getProperty("app.port"));
        System.out.println(portEcoute );
        
        setEtat(super.getEtatRepos());                                              // Etat par défault
        try {	
	    socketServeur = new ServerSocket(portEcoute);                           //création de la socket
	} catch(IOException e) {
	    System.err.println("Creation de la socket impossible : " + e);
	    System.exit(-1); // code retour pour le système
	}
        
    }
     /**
     * Permet détablir la connexion avec une serveur
     */
    public void lancerConnexion()
    {
	
                                                                                        // Attente d'une connexion d'un client
        Socket socketClient = null;
	try {
            System.out.println("Attente de connexion");
	    socketClient = socketServeur.accept();
            System.out.println("Connexion OK");
	} catch(IOException e) {
	    System.err.println("Erreur lors de l'attente d'une connexion : " + e);
	    System.exit(-1); // code retour pour le système
	}  
                                                                                        // Association d'un flux d'entree et de sortie
           try {
	    input = new ObjectInputStream(socketClient.getInputStream());
	    output = new ObjectOutputStream(socketClient.getOutputStream());

	} catch(IOException e) {
	    System.err.println("Association des flux impossible : " + e);
	    System.exit(-1);
	}
	
    }

     @Override
    public void setEtat(Etat etat) {
       this.etat=etat;
    }
    /**
     * Permet de se déconnecter du serveur et de fermer les flux
     */
    @Override
    public void close() {
        try{
       input.close();
       output.close();
       socketClient.close();
       socketServeur.close();
        }
       catch(IOException e) {
            System.err.println("Erreur lors de la fermeture des flux et des sockets : " + e);
            System.exit(-1);
        }  
    }
}
