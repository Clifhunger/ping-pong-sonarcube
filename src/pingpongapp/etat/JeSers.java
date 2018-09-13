/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.etat;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import pingpongapp.Joueur;
import pingpongapp.PDU.*;


/**
 *
 * @author clifhunger
 */
public class JeSers implements Etat{
    private Joueur joueur;

    public JeSers(Joueur joueur) {
        this.joueur = joueur;
    } 

    @Override
    public void init() {
       throw new java.lang.IllegalStateException ("Méthode inaccessible par cet état"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void attenteAck() {
       throw new java.lang.IllegalStateException ("Méthode inaccessible par cet état"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void echange() {
        try {
            Thread.sleep(500);                                                                          //pose de 500 ms pour une meilleur vision à l'affichage
        } catch (InterruptedException ex) {
            Logger.getLogger(JeSers.class.getName()).log(Level.SEVERE, null, ex);                       
        }
        if(joueur.getMonScore()==joueur.getScoreMax() || joueur.getSonScore()==joueur.getScoreMax())   //si un des deux joueurs a atteint le score max
        {
             System.out.println("Score Final : "+joueur.getMonScore()+" - "+joueur.getSonScore());
             System.out.println("Nombre échange: "+(joueur.getMonScore()+joueur.getSonScore()));
             Fin uneFin=null;
             if(joueur.getMonScore()<joueur.getSonScore())                      //si l'adversaire a gagné
             {
                 System.out.println("l'autre joueur à gagné");
                uneFin=new Fin("la partie est fini et vous avez gagné") ;           //envoi d'une notification à l'autre joueur
             }
             else                                                               //si j'ai gangé
             {
                 System.out.println("vous avez gagné");
                 uneFin=new Fin("la Partie est fini et vous avez perdu") ;          //envoi d'une notification à l'autre joueur
             }
              try {  
                joueur.getOutput().writeObject(uneFin);                         //envoi de la notif
               }catch(Exception e)
                {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                } 
              joueur.setEtat(joueur.getEtatFinActive());
        }
        else if(joueur.getNumeroDuService()==2)                                 //si j'ai fait deux services de suite
        {
            try {
                System.out.println("changement de service");                    
                joueur.getOutput().writeObject(new Service("changement"));          //envoi de la notification de changement
            } 
            catch(Exception e)
                {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
           
            Service ackService = null;                                          
            try {
               
               ackService=(Service)joueur.getInput().readObject();                  //lecture du flux d'entrée
            } 
            catch(Exception e)
                {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            System.out.println(ackService);
            joueur.setEtat(joueur.getEtatIlSert());                                 // passage à l'état Il sert
            joueur.resetNumeroDuService();                                          //num service =0
        }
        else                                                                    //si je peux servir
        {
             Random rand=new Random();
            if(rand.nextInt(100)+1<=joueur.getProbaAbandon() && joueur.getMonScore()<=(joueur.getSonScore()-3)) //si la différence des score est de trois et que le joueur abandonne
            {
                 try {  
                     System.out.println("\n==========================================");
                     System.out.println("Score: "+joueur.getMonScore()+" - "+joueur.getSonScore());
                     System.out.println("j'ai abandonné");
                     System.out.println("Nombre échange: "+(joueur.getMonScore()+joueur.getSonScore()));
                     System.out.println("\n==========================================");
                joueur.getOutput().writeObject(new Abandon("l'autre joueur abandonne"));                        //envoi de l'anbandon à l'adversaire
                }catch(Exception e)
                {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
                    joueur.setEtat(joueur.getEtatFinActive());                                                  //passage à l'état Fin Active

            }
            else                                                                           //si le joueur sert
            {
                System.out.println("\n==========================================");
                System.out.println("Score: "+joueur.getMonScore()+" - "+joueur.getSonScore());
               
                System.out.println("==========================================\n");
                 
                System.out.println("\n==========================================");
                System.out.println("Debut de l'échange je sers   ==  numéro du service : "+joueur.getNumeroDuService());
                joueur.serviceSuivant();                                                        //num service ++

                try {
                    joueur.getOutput().writeObject(new Ping());                                 //envoi d'un Ping
                   }catch(Exception e)
                    {
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                joueur.setEtat(joueur.getEtatFirstPingEmis());                                  //passage à l'état premier ping emis
            }
            
        }
    }
}
