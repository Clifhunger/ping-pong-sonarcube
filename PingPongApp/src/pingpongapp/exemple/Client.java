/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.exemple;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MR
 */
public class Client {
    private static final Logger LOG=Logger.getGlobal();
    public static void main(String[] args) throws IOException{
    
        // Creation de la socket
	Socket socket = null;
	try {
	 socket = new Socket("localhost", Serveur.portEcoute);
         // socket = new Socket(InetAddress.getByName("10.192.51.67"),5555);
	} catch(IOException e) {
		LOG.log(Level.SEVERE,"Erreur sur l'hôte : " + e.getMessage());
	    System.exit(-1);
	}
	finally {
		socket.close();
	}
        
        // Association d'un flux d'entree et de sortie
	ObjectInputStream input = null;
	PrintWriter output = null;
	try {
	    input = new ObjectInputStream(socket.getInputStream());
	    output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
	} catch(IOException e) {
		LOG.log(Level.SEVERE,"Association des flux impossible : " + e);
	    System.exit(-1);
	}
        
        try{
        	LOG.log(Level.INFO,"Connexion OK");
            String message = "Envoie moi quelqu'un !";
            LOG.log(Level.INFO,"Envoi demande...");
            output.println(message);
            LOG.log(Level.INFO,"Message envoyé");
            LOG.log(Level.INFO,"Réception de la personne...");
        
            // Récuération de la personne 
            Object objet = input.readObject();
            LOG.log(Level.INFO,"Personne reçue");
            
            if (objet instanceof Personne)
            {
                Personne personneRecue = (Personne) objet;
                personneRecue.affiche();
            }
        } catch(Exception e)
            {
        	LOG.log(Level.SEVERE, e.getMessage(),e);
            }
        // Fermeture des flux et des sockets
        try {
            input.close();
            output.close();
            socket.close();
            LOG.log(Level.INFO,"Client de'connecte'.");
            System.exit(0);
        } catch(IOException e) {
        	LOG.log(Level.SEVERE,"Erreur lors de la fermeture des flux et des sockets : " + e);
            System.exit(-1);
        }            
    }
}