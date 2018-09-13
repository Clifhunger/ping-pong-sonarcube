/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.exemple;

import java.io.*;
import java.net.*;

/**
 *
 * @author MR
 */
public class Client {
    public static void main(String[] args){
    
        // Creation de la socket
	Socket socket = null;
	try {
	 socket = new Socket("localhost", Serveur.portEcoute);
         // socket = new Socket(InetAddress.getByName("10.192.51.67"),5555);
	} catch(UnknownHostException e) {
	    System.err.println("Erreur sur l'hôte : " + e);
	    System.exit(-1);
	} catch(IOException e) {
	    System.err.println("Creation de la socket impossible : " + e);
	    System.exit(-1);
	}
        
        // Association d'un flux d'entree et de sortie
	ObjectInputStream input = null;
	PrintWriter output = null;
	try {
	    input = new ObjectInputStream(socket.getInputStream());
	    output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
	} catch(IOException e) {
	    System.err.println("Association des flux impossible : " + e);
	    System.exit(-1);
	}
        
        try{
            System.out.println("Connexion OK");
            String message = "Envoie moi quelqu'un !";
            System.out.println("Envoi demande...");
            output.println(message);
            System.out.println("Message envoyé");
            System.out.println("Réception de la personne...");
        
            // Récuération de la personne 
            Object objet = input.readObject();
            System.out.println("Personne reçue");
            
            if (objet instanceof Personne)
            {
                Personne personneRecue = (Personne) objet;
                personneRecue.affiche();
            }
        } catch(Exception e)
            {
    	        e.printStackTrace();
    	        System.out.println(e.getMessage());
            }
        // Fermeture des flux et des sockets
        try {
            input.close();
            output.close();
            socket.close();
            System.out.println("Client de'connecte'.");
            System.exit(0);
        } catch(IOException e) {
            System.err.println("Erreur lors de la fermeture des flux et des sockets : " + e);
            System.exit(-1);
        }            
    }
}