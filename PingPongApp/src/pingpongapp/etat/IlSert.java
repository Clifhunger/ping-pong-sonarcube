/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.etat;

import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import pingpongapp.Joueur;
import pingpongapp.PDU.*;

/**
 *
 * @author clifhunger
 */
public class IlSert implements Etat{
    private Joueur joueur;
    private static final String NOT_SUPPORTED="Not supported yet.";
    private static final Logger LOG=Logger.getGlobal();

    public IlSert(Joueur joueur) {
        this.joueur = joueur;
    } 

    @Override
    public String getMessage() {
        throw new UnsupportedOperationException(NOT_SUPPORTED); 
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException(NOT_SUPPORTED); 
    }

    @Override
    public void attenteAck() {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    @Override
    public void echange()
    {
       
         Object objet=null;
        try
            {
                   objet=joueur.getInput().readObject(); 
                  
            }
             catch(Exception e)
                 {
            	 LOG.log(Level.SEVERE, e.getMessage(),e); 
                 }
        if(objet instanceof Service)
        {
             try {  
            	LOG.log(Level.INFO,"{0}",objet);
                joueur.getOutput().writeObject(new Service("OK pour le changement"));
               }
             catch(Exception e)
                {
            	 LOG.log(Level.SEVERE, e.getMessage(),e); 
                }
             joueur.setEtat(joueur.getEtatJeSers());
        }
        else if(objet instanceof Fin)
        {
        	LOG.log(Level.INFO,"{0}",objet); 
        	LOG.log(Level.INFO,"Score Final : {0}",new StringBuilder(joueur.getMonScore()).append(" - ").append(joueur.getSonScore()));
           
             joueur.setEtat(joueur.getEtatFinPassive());
        }
        else if(objet instanceof Abandon)
        {
        	LOG.log(Level.INFO,"\n===========================================");
        	LOG.log(Level.INFO,"Score: "+joueur.getMonScore()+" - "+joueur.getSonScore());
        	LOG.log(Level.INFO,"{0}",objet);
        	LOG.log(Level.INFO,"\n=========================================");
             joueur.setEtat(joueur.getEtatFinPassive());
        }
        else
        {
        	LOG.log(Level.INFO,"\n==========================================");
        	LOG.log(Level.INFO,"Score: "+joueur.getMonScore()+" - "+joueur.getSonScore());
            LOG.log(Level.INFO,"==========================================\n");
                 
            LOG.log(Level.INFO,"\n==========================================");
            LOG.log(Level.INFO,"Debut de l'échange il Sert");
            
            LOG.log(Level.INFO,"j ai reçu un {0}",objet);
            Random rand=new SecureRandom();
            int cas = 0;
            if((rand.nextInt()+1)<=joueur.getProbaSmash())
            {
            cas=2;
            }
            else if((rand.nextInt(100)+1)>(100-joueur.getProbaAceAdversaire()))
            {
                cas=1;
            }
            Object unCout=null;
            switch(cas)
                    {
                        case 1:
                            unCout=new Ace();
                            LOG.log(Level.INFO,"j envoi un  {0}",unCout);
                            LOG.log(Level.INFO,"Fin de l'échange");
                            LOG.log(Level.INFO,"==========================================");
                            joueur.ilMarque();

                        break;
                        case 2:
                             unCout=new Smash();
                             LOG.log(Level.INFO,"j envoi un {0}",unCout);
                             LOG.log(Level.INFO,"Fin de l'échange");
                             LOG.log(Level.INFO,"==========================================");
                             joueur.jeMarque();
                        break;
                        default: 
                            unCout=new Pong();
                            LOG.log(Level.INFO,"j envoi un {0}",unCout);
                            joueur.setEtat(joueur.getEtatAttentePingSmash());
                            break;

                    }
             try {
                 
                joueur.getOutput().writeObject(unCout);
               }
             catch(Exception e)
                {
            	 LOG.log(Level.SEVERE, e.getMessage(),e); 
                }

        }
    }
}
