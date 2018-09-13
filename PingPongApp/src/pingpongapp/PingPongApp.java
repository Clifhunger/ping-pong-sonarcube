/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author ecossard
 */
public class PingPongApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        /*do
        {*/
            
        
        boolean bool=true;
        Joueur joueur=null; 
       
        do
        {
            System.out.println("Créer ou rechercher une partie ?");
            System.out.println("1 : Créer      2 : Rechercher    3 : Quitter");
           
           
            switch(sc.nextInt())
            {
                case 1:
                    joueur=new Serveur();
                    
                    bool=false;
                    break;
                case 2:
                    joueur= new Client();
                    bool=false;
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }while(bool);
        while(joueur.getEtat()==joueur.getEtatRepos())
        {
              joueur.init();
        }
        System.out.println("je suis sortie de l'état de repos");
        if(joueur instanceof Client)
        {
            joueur.attenteAck();
        }
        while(!(joueur.getEtatRepos()==joueur.getEtat()))
        {
            joueur.echange();
        }
        try {
            joueur.getInput().close();
            joueur.getOutput().close();
          
            
        } catch(IOException e) {
            System.err.println("Erreur lors de la fermeture des flux et des sockets : " + e);
            System.exit(-1);
        }     

       
    }
    
}
