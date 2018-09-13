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
    public String getMessage() {
       return "Etat de Repos";
    }


    @Override
    public void init() {
       if(joueur instanceof Client)
       {
           //envoie de la demande de partie au serveur
           joueur.lancerConnexion();
           System.out.println("je suis un client j'envoi une demande de partie");
           Init init=null;
           init=new Init(11);
           try {
               
              joueur.getOutput().writeObject(init);
              System.out.println("envoie");
           }catch(Exception e)
            {
    	        e.printStackTrace();
    	        System.out.println(e.getMessage());
            }
           joueur.setEtat(joueur.getEtatInitActive());
         
       }
       if(joueur instanceof Serveur)
       { 
           System.out.println("je suis un serveur à l'état de repos");
           joueur.lancerConnexion();
            Init objet=null;
           //attente de demande
            try
            {
                   System.out.println("Attente de la reception d'une demande de partie");
                   objet=(Init) joueur.getInput().readObject(); 
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
            if(objet.getScoreMax()==joueur.getScoreMax())
            {
                reponse=new Ackinit("oui");
                joueur.setEtat(joueur.getEtatJeSers());
                try {
               
              joueur.getOutput().writeObject(reponse);
              System.out.println("envoie de la réponse");
           }catch(Exception e)
            {
    	        e.printStackTrace();
    	        System.out.println(e.getMessage());
            }
            }
            else
            {
              
                reponse=new Ackinit("non");
                System.out.println(" ===== je refuse la partie ===== ");
                System.out.println(objet);
                try 
                {
                    joueur.getOutput().writeObject(reponse);
                    System.out.println("envoie de la réponse");
                }catch(Exception e)
                {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
                try 
                {
                    joueur.getInput().close();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void echange() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
