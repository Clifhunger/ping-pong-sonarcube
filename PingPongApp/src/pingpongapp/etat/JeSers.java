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
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(JeSers.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(joueur.getMonScore()==joueur.getScoreMax() || joueur.getSonScore()==joueur.getScoreMax())
        {
             System.out.println("Score Final : "+joueur.getMonScore()+" - "+joueur.getSonScore());
             Fin uneFin=null;
             if(joueur.getMonScore()<joueur.getSonScore())
             {
                 System.out.println("l'autre joueur à gagné");
                uneFin=new Fin("la partie est fini et vous avez gagné") ;
             }
             else
             {
                 System.out.println("vous avez gagné");
                 uneFin=new Fin("la Partie est fini et vous avez perdu") ;
             }
              try {  
                joueur.getOutput().writeObject(uneFin);
               }catch(Exception e)
                {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                } 
              joueur.setEtat(joueur.getEtatFinActive());
        }
        else if(joueur.getNumeroDuService()==2)
        {
            try {
                System.out.println("changement de service");
                joueur.getOutput().writeObject(new Service("changement"));
            } 
            catch(Exception e)
                {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
           
            Service ackService = null;
            try {
               
               ackService=(Service)joueur.getInput().readObject();
            } 
            catch(Exception e)
                {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            System.out.println(ackService);
            joueur.setEtat(joueur.getEtatIlSert());
            joueur.resetNumeroDuService();
        }
        else
        {
             Random rand=new Random();
            if(rand.nextInt(100)+1<=joueur.getProbaAbandon() && joueur.getMonScore()<=(joueur.getSonScore()-3))
            {
                 try {  
                     System.out.println("\n==========================================");
                     System.out.println("Score: "+joueur.getMonScore()+" - "+joueur.getSonScore());
                     System.out.println("j'ai abandonné");
                     System.out.println("\n==========================================");
                joueur.getOutput().writeObject(new Abandon("l'autre joueur abandonne"));
                }catch(Exception e)
                {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
                    joueur.setEtat(joueur.getEtatFinActive());  

            }
            else
            {
                System.out.println("\n==========================================");
                System.out.println("Score: "+joueur.getMonScore()+" - "+joueur.getSonScore());
                System.out.println("==========================================\n");
                 
                System.out.println("\n==========================================");
                System.out.println("Debut de l'échange je Sert   ==  numéro du service : "+joueur.getNumeroDuService());
                joueur.serviceSuivant();

                try {
                    joueur.getOutput().writeObject(new Ping());
                   }catch(Exception e)
                    {
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                joueur.setEtat(joueur.getEtatFirstPingEmis());
            }
            
        }
    }
}
