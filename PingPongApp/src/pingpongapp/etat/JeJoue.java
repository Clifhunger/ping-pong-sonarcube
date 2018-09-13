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
        
        
        Object cout=null; 
        Random rand=new Random();
        
        if((rand.nextInt(100)+1)<=joueur.getProbaSmash())
        {
            cout=new Smash();
            joueur.jeMarque();
            joueur.setEtat(joueur.getEtatJeSers());
        }
        else
        {
             cout=new Ping();
             joueur.setEtat(joueur.getEtatIlJoue()); 
        }
       
        try {  
            System.out.println("j'envoi un "+cout);
            joueur.getOutput().writeObject(cout);
           }catch(Exception e)
            {
    	        e.printStackTrace();
    	        System.out.println(e.getMessage());
            }
         if(cout instanceof Smash)
         {
             System.out.println("Fin de l'échange");
            System.out.println("==========================================");
         }
    }
}
