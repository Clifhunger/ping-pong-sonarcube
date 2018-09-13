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
public class AttentPingSmash implements Etat {
    private Joueur joueur;

    public AttentPingSmash(Joueur joueur) {
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
          Object objet=null;
       try
            {
                   objet=joueur.getInput().readObject();                                    //lecture d'un Objet sur le flux d'entrée
                   System.out.println("j'ai recus un "+ objet);
                   System.out.println(objet);
            }
             catch(Exception e)
                 {
                     e.printStackTrace();
                     System.out.println(e.getMessage());
                 }
       if(objet instanceof Ping)                                                            // si je reçois un Ping
       {
            Object unCout=null;
                   
            Random rand=new Random();                                                           //Nombre random entre 0 et 100
            System.out.print("j'envoi un ");
            if((rand.nextInt(100)+1)<=joueur.getProbaSmash())                                  //si le joueur fait un Smash
            {
                unCout=new Smash();                                                                     //la reponse sera un Pong
                System.out.println(unCout);
                joueur.setEtat(joueur.getEtatIlSert());                                         //retour à l'état de service
                joueur.jeMarque();                                                              //mon score ++
                System.out.println("Fin de l'échange");
                System.out.println("==========================================");
            }    
            else                                                                                 //si le joueur ne fait pas de Smash
            {
                unCout =new Pong();                                                                     //la reponse seras un Pong
                System.out.println(unCout);
            }
     
            try {  
            joueur.getOutput().writeObject(unCout);                                             //envoi du cout
           }catch(Exception e)
            {
    	        e.printStackTrace();
    	        System.out.println(e.getMessage());
            }
       }
        if(objet instanceof Smash)                                                          //si je reçois un Smash
       {
           joueur.setEtat(joueur.getEtatIlSert());                                              //retour à l'état de service
           joueur.ilMarque();                                                                   //son score ++
           System.out.println("Fin de l'échange");
           System.out.println("==========================================");
       }
        
    }
    
    
}
