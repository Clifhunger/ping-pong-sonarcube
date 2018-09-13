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
    public String getMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void attenteAck() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void echange() {
          Object objet=null;
       try
            {
                   objet=joueur.getInput().readObject();
                   System.out.println("j'ai recus un "+ objet);
                   System.out.println(objet);
            }
             catch(Exception e)
                 {
                     e.printStackTrace();
                     System.out.println(e.getMessage());
                 }
       if(objet instanceof Ping)
       {
            Object unCout=null;
                   
            Random rand=new Random();
            System.out.print("j'envoi un ");
            if((rand.nextInt(100)+1)<=joueur.getProbaSmash())
            {
                unCout =new Pong();
                System.out.println(unCout);
            }    
            else
            {
                unCout=new Smash();
                System.out.println(unCout);
                joueur.setEtat(joueur.getEtatIlSert());
                joueur.jeMarque();
                System.out.println("Fin de l'échange");
                System.out.println("==========================================");
            }
     
            try {  
            joueur.getOutput().writeObject(unCout);
           }catch(Exception e)
            {
    	        e.printStackTrace();
    	        System.out.println(e.getMessage());
            }
       }
        if(objet instanceof Smash)
       {
           joueur.setEtat(joueur.getEtatIlSert());
           joueur.ilMarque();
           System.out.println("Fin de l'échange");
           System.out.println("==========================================");
       }
        
    }
    
    
}
