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
    public void echange()
    {
       
         Object objet=null;
        try
            {
                   objet=joueur.getInput().readObject(); 
                  
            }
             catch(Exception e)
                 {
                     e.printStackTrace();
                     System.out.println(e.getMessage());
                 }
        if(objet instanceof Service)
        {
             try {  
                System.out.println(objet);//demande de changement
                joueur.getOutput().writeObject(new Service("OK pour le changement"));
               }
             catch(Exception e)
                {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
             joueur.setEtat(joueur.getEtatJeSers());
        }
        else if(objet instanceof Fin)
        {
            System.out.println(objet);//notif de fin de partie 
            System.out.println("Score Final : "+joueur.getMonScore()+" - "+joueur.getSonScore());
           
             joueur.setEtat(joueur.getEtatFinPassive());
        }
        else if(objet instanceof Abandon)
        {
             System.out.println("\n==========================================");
             System.out.println("Score: "+joueur.getMonScore()+" - "+joueur.getSonScore());
             System.out.println(objet);//notif de fin de partie 
              System.out.println("\n==========================================");
             joueur.setEtat(joueur.getEtatFinPassive());
        }
        else
        {
            System.out.println("\n==========================================");
            System.out.println("Score: "+joueur.getMonScore()+" - "+joueur.getSonScore());
            System.out.println("==========================================\n");
                 
            System.out.println("\n==========================================");
            System.out.println("Debut de l'échange il Sert");
            
            System.out.println("j'ai reçu un "+objet);
            Random rand=new Random();
            int cas = 0;
            if((rand.nextInt(100)+1)<=joueur.getProbaSmash())
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
                            System.out.println("j'envoi un "+unCout);
                            System.out.println("Fin de l'échange");
                            System.out.println("==========================================");
                            joueur.ilMarque();

                        break;
                        case 2:
                             unCout=new Smash();
                             System.out.println("j'envoi un "+unCout);
                             System.out.println("Fin de l'échange");
                             System.out.println("==========================================");
                             joueur.jeMarque();
                        break;
                        default: 
                            unCout=new Pong();
                            System.out.println("j'envoi un "+unCout);
                            joueur.setEtat(joueur.getEtatAttentePingSmash());
                            break;

                    }
             try {
                 
                joueur.getOutput().writeObject(unCout);
               }
             catch(Exception e)
                {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }

        }
    }
}
