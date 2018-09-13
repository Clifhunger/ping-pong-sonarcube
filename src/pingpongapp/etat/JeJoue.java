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
public class JeJoue implements Etat{
    private Joueur joueur;

    public JeJoue(Joueur joueur) {
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
        
        
        Object cout=null; 
        Random rand=new Random();                                               //nombre random entre 0 et 100
        
        if((rand.nextInt(100)+1)<=joueur.getProbaSmash())                       //si le joueur fait un Smash
        {
            cout=new Smash();                                                       //le cout est un Smash
            joueur.jeMarque();                                                      //mon score ++
            joueur.setEtat(joueur.getEtatJeSers());                                 //passage à l'état de service
        }
        else                                                                    //si le joueur fait un Ping
        {
             cout=new Ping();                                                       //le cout est un Ping
             joueur.setEtat(joueur.getEtatIlJoue());                                //passage à l'état IL joue
        }
       
        try {  
            System.out.println("j'envoi un "+cout);                             
            joueur.getOutput().writeObject(cout);                               //envoi du Coup
           }catch(Exception e)
            {
    	        e.printStackTrace();
    	        System.out.println(e.getMessage());
            }
         if(cout instanceof Smash)                                              //gestion de l'affichage si le coup est un smash
         {
             System.out.println("Fin de l'échange");
            System.out.println("==========================================");
         }
    }
}
