/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.etat;

import java.io.IOException;
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
public class JeSers implements Etat{
    private Joueur joueur;
    private static final String NOT_SUPPORTED="Not supported yet.";
    private static final Logger LOG=Logger.getGlobal();
    private static final int SLEEP =500;

    public JeSers(Joueur joueur) {
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
    public void echange() {
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException ex) {
        	LOG.log(Level.SEVERE, ex.getMessage(), ex);
            Thread.currentThread().interrupt();
        }
        if(joueur.getMonScore()==joueur.getScoreMax() || joueur.getSonScore()==joueur.getScoreMax())
        {
             LOG.log(Level.INFO,"Score Final : "+joueur.getMonScore()+" - "+joueur.getSonScore());
             Fin uneFin=null;
             if(joueur.getMonScore()<joueur.getSonScore())
             {
            	LOG.log(Level.INFO,"l'autre joueur à gagné");
                uneFin=new Fin("la partie est fini et vous avez gagné") ;
             }
             else
             {
            	 LOG.log(Level.INFO,"vous avez gagné");
                 uneFin=new Fin("la Partie est fini et vous avez perdu") ;
             }
              try {  
                joueur.getOutput().writeObject(uneFin);
               }catch(Exception e)
                {
            	   LOG.log(Level.SEVERE, e.getMessage(), e);
                } 
              joueur.setEtat(joueur.getEtatFinActive());
        }
        else if(joueur.getNumeroDuService()==2)
        {
            try {
            	LOG.log(Level.INFO,"changement de service");
                joueur.getOutput().writeObject(new Service("changement"));
            } 
            catch(Exception e)
                {
            	LOG.log(Level.SEVERE, e.getMessage(), e);
                }
           
            Service ackService = null;
            try {
               
               ackService=(Service)joueur.getInput().readObject();
            } 
            catch(Exception e)
                {
            	LOG.log(Level.SEVERE, e.getMessage(), e);
                }
            LOG.log(Level.INFO,"{0}",ackService);
            joueur.setEtat(joueur.getEtatIlSert());
            joueur.resetNumeroDuService();
        }
        else
        {
             Random rand=new SecureRandom();
            if(rand.nextInt(100)+1<=joueur.getProbaAbandon() && joueur.getMonScore()<=(joueur.getSonScore()-3))
            {
                 try {  
                	 LOG.log(Level.INFO,"\n===========================================");
                	 LOG.log(Level.INFO,"Score: "+joueur.getMonScore()+" - "+joueur.getSonScore());
                	 LOG.log(Level.INFO,"j ai abandonné");
                	 LOG.log(Level.INFO,"\n=========================================");
                joueur.getOutput().writeObject(new Abandon("l'autre joueur abandonne"));
                }catch(Exception e)
                {
                	LOG.log(Level.SEVERE, e.getMessage(), e);
                }
                    joueur.setEtat(joueur.getEtatFinActive());  

            }
            else
            {
            	 LOG.log(Level.INFO,"\n==========================================");
            	 LOG.log(Level.INFO,"Score: "+joueur.getMonScore()+" - "+joueur.getSonScore());
            	 LOG.log(Level.INFO,"==========================================\n");
                 
            	 LOG.log(Level.INFO,"\n==========================================");
            	 LOG.log(Level.INFO,"Debut de l'échange je Sert   ==  numéro du service : "+joueur.getNumeroDuService());
                joueur.serviceSuivant();

                try {
                    joueur.getOutput().writeObject(new Ping());
                   }catch(Exception e)
                    {
                	   LOG.log(Level.SEVERE, e.getMessage(), e);
                    }
                joueur.setEtat(joueur.getEtatFirstPingEmis());
            }
            
        }
    }
}
