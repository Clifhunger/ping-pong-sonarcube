/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.exemple;

import java.net.*;
import java.io.*;

/**
 *
 * @author MR
 */
public class Serveur {
    public static final int portEcoute = 5555;
    
    public static void main(String[] args){
        
        // Creation de la socket serveur
	ServerSocket socketServeur = null;
	try {	
	    socketServeur = new ServerSocket(portEcoute);
	} catch(IOException e) {
	    System.err.println("Creation de la socket impossible : " + e);
	    System.exit(-1); // code retour pour le système
	}
        
        // Attente d'une connexion d'un client
	Socket socketClient = null;
	try {
            System.out.println("Attente de connexion");
	    socketClient = socketServeur.accept();
            System.out.println("Connexion OK");
	} catch(IOException e) {
	    System.err.println("Erreur lors de l'attente d'une connexion : " + e);
	    System.exit(-1); // code retour pour le système
	}
 
        // Association d'un flux d'entree et de sortie
	BufferedReader input = null; //  buffer de lecture
	ObjectOutputStream output = null; // flux d'ecriture
	try {
	    input = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
	    output = new ObjectOutputStream(socketClient.getOutputStream());
	} catch(IOException e) {
	    System.err.println("Association des flux impossible : " + e);
	    System.exit(-1);
	}
        
        // Lecture
	String message = "";
	try {
	    message = input.readLine();
	} catch(IOException e) {
	    System.err.println("Erreur lors de la lecture : " + e);
	    System.exit(-1);
	}
	System.out.println("Lu: " + message);
        
       
        // Creation de l'objet a envoyer et envoi
        try {
            System.out.println("Pre'paration de l'envoi...");
            Personne p = new Personne("Jean","Pierre",26);
            System.out.println("Envoi de la personne...");
            output.writeObject(p);
            System.out.println("Personne envoyee' !");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
        // Fermeture des flux et des sockets
        try {
            input.close();
            output.close();
            socketClient.close();
            socketServeur.close();
            System.out.println("Serveur de'connecte'.");
            System.exit(0);
        } catch(IOException e) {
            System.err.println("Erreur lors de la fermeture des flux et des sockets : " + e);
            System.exit(-1);
        }            

    }
}
