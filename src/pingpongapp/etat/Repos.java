/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.etat;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import pingpongapp.*;
import pingpongapp.PDU.*;

/**
 *
 * @author clifhunger
 */
public class Repos implements Etat{
    private Joueur joueur;

    public Repos(Joueur joueur) {
        this.joueur = joueur;
    } 

    @Override
    public void init() {
       if(joueur instanceof Client)                                             //si je suis un Client
       {
           //envoie de la demande de partie au serveur
           joueur.lancerConnexion();                                                //lancer la connexion
           System.out.println("je suis un client j'envoi une demande de partie");
           Init init=null;
           int scoremax=11;
           init=new Init(scoremax);                                                 //envoi d'une demande de partie avec un score max
           joueur.setScoreMax(scoremax);                                            //mise a jour du score max
           try {
               
              joueur.getOutput().writeObject(init);                                 //envoi de la demande
              System.out.println("envoie");
           }catch(Exception e)
            {
    	        e.printStackTrace();
    	        System.out.println(e.getMessage());
            }
           joueur.setEtat(joueur.getEtatInitActive());                              //passage à l'état Init Active
         
       }
       if(joueur instanceof Serveur)                                            //si je suis un serveur
       { 
           System.out.println("je suis un serveur à l'état de repos");
           joueur.lancerConnexion();                                                //etablir les paramètre pour attendre des connexions
            Init objet=null;
           //attente de demande
            try
            {
                   System.out.println("Attente de la reception d'une demande de partie");
                   objet=(Init) joueur.getInput().readObject();                     //lecture du flux d'entrée pour récupèrer la demande de partie
                   System.out.println("j'ai recus une demande : ");
                   System.out.println(objet);
            }
             catch(Exception e)
                 {
                     e.printStackTrace();
                     System.out.println(e.getMessage());
                 }
            
            //envoie de la reponse
              Ackinit reponse=null;
            if(objet.getScoreMax()==joueur.getScoreMax())                           //si le score choisit par l'adversaire est le même que le mien (11 par default)
            {
                reponse=new Ackinit("oui");                                             //envoi d'un Ack positif
                joueur.setEtat(joueur.getEtatJeSers());                                 //passage à l'état de service
                try {
               
              joueur.getOutput().writeObject(reponse);                                  //envoi du ack
              System.out.println("envoie de la réponse");
           }catch(Exception e)
            {
    	        e.printStackTrace();
    	        System.out.println(e.getMessage());
            }
            }
            else                                                                    //si le score ne me convient pas
            {
              
                reponse=new Ackinit("non");                                             //Ack négatif
                System.out.println(" ===== je refuse la partie ===== ");
                System.out.println(objet);
                try 
                {
                    joueur.getOutput().writeObject(reponse);                            //envoi du Ack negatif
                    System.out.println("envoie de la réponse");
                }catch(Exception e)
                {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
                try 
                {
                    joueur.getInput().close();                                          //fermeture des flux
                    joueur.getOutput().close();  
                   
                } catch(IOException e) 
                {
                    System.err.println("Erreur lors de la fermeture des flux et des sockets : " + e);
                    System.exit(-1);
                }   
                
            }
       }
        
    }

    @Override
    public void attenteAck() {
      throw new java.lang.IllegalStateException ("Méthode inaccessible par cet état"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void echange() {
      throw new java.lang.IllegalStateException ("Méthode inaccessible par cet état"); //To change body of generated methods, choose Tools | Templates.
    }
}
