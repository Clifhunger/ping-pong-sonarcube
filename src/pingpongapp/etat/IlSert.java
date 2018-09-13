/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.etat;

import java.util.Random;
import pingpongapp.Joueur;
import pingpongapp.PDU.*;

/**
 *
 * @author clifhunger
 */
public class IlSert implements Etat{
    private Joueur joueur;

    public IlSert(Joueur joueur) {
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
    public void echange()
    {
         Object objet=null;
        try
            {
                   objet=joueur.getInput().readObject();                        //lecture du flux d'entrée
                  
            }
             catch(Exception e)
                 {
                     e.printStackTrace();
                     System.out.println(e.getMessage());
                 }
        if(objet instanceof Service)                                            //si je reçois une demande de changement de service
        {
             try {  
                System.out.println(objet);
                joueur.getOutput().writeObject(new Service("OK pour le changement"));//envoi d'un Ack pour notifier la bonne reception de la demande de changement
               }
             catch(Exception e)
                {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
             joueur.setEtat(joueur.getEtatJeSers());                                 //passage à l'etat de service
        }
        else if(objet instanceof Fin)                                           //si je reçois le PDU FIN
        {
            System.out.println(objet);
            System.out.println("Score Final : "+joueur.getMonScore()+" - "+joueur.getSonScore());
            System.out.println("Nombre échange: "+(joueur.getMonScore()+joueur.getSonScore()));
           
             joueur.setEtat(joueur.getEtatFinPassive());                            //passage à l'état de Fin Passive
        }
        else if(objet instanceof Abandon)                                       //si je reçois un Abandon
        {
             System.out.println("\n==========================================");
             System.out.println("Score: "+joueur.getMonScore()+" - "+joueur.getSonScore());
             System.out.println("Nombre échange: "+(joueur.getMonScore()+joueur.getSonScore()));
             System.out.println(objet);
              System.out.println("\n==========================================");
             joueur.setEtat(joueur.getEtatFinPassive());                            //passage à l'état de Fin Passive
        }
        else                                                                    //si je reçois un Ping
        {
            System.out.println("\n==========================================");
            System.out.println("Score: "+joueur.getMonScore()+" - "+joueur.getSonScore());
            System.out.println("==========================================\n");
                 
            System.out.println("\n==========================================");
            System.out.println("Debut de l'échange il Sert");
            
            System.out.println("j'ai reçu un "+objet);
            Random rand=new Random();                                               //nombre random entre 0 et 100
            
            Object unCout=null;
            if((rand.nextInt(100)+1)<=joueur.getProbaSmash())                   //si je fait un Smash
            {
                  unCout=new Smash();                                               //le cout est un Smash
                  System.out.println("j'envoi un "+unCout);
                  System.out.println("Fin de l'échange");
                  System.out.println("==========================================");
                  joueur.jeMarque();                                                //mon score ++
            }
            else if((rand.nextInt(100)+1)>(100-joueur.getProbaAceAdversaire())) //si l'adversaire a fait un Ace
            {
                 unCout=new Ace();                                                  //notif de lace Advers
                 System.out.println("j'envoi un "+unCout);
                 System.out.println("Fin de l'échange");
                 System.out.println("==========================================");
                 joueur.ilMarque();                                                 //son score ++
            }
            else                                                                //si je fait un Pong
            {
                 unCout=new Pong();                                                 //le cout est un Pong
                 System.out.println("j'envoi un "+unCout);
                 joueur.setEtat(joueur.getEtatAttentePingSmash());                  //passge a l'état attente de Ping ou de smash
                            
            }
             try {
                 
                joueur.getOutput().writeObject(unCout);                         //envoi du Cout
               }
             catch(Exception e)
                {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }

        }
    }
}
