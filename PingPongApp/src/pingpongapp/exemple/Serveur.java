/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.exemple;

import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;

/**
 *
 * @author MR
 */
public class Serveur {
	public static final int PORT_ECOUTE = 5555;
	private static final Logger LOG = Logger.getGlobal();
	private static final int AGE = 26;

	private Serveur() {
	}

	public static void main(String[] args) throws Exception {

		// Creation de la socket serveur
		ServerSocket socketServeur = null;
		try (AutoCloseable i = null) {
			socketServeur = new ServerSocket(PORT_ECOUTE);
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Creation de la socket impossible : " + e);
			return;
		} finally {
			if (socketServeur != null) {
				socketServeur.close();
			}
		}

		// Attente d'une connexion d'un client
		Socket socketClient = null;
		try {
			LOG.log(Level.INFO, "Attente de connexion");
			socketClient = socketServeur.accept();
			LOG.log(Level.INFO, "Connexion OK");
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Erreur lors de l'attente d'une connexion : " + e);
			return;
		}

		// Association d'un flux d'entree et de sortie
		BufferedReader input = null;
		ObjectOutputStream output = null;
		try {
			input = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			output = new ObjectOutputStream(socketClient.getOutputStream());
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Association des flux impossible : " + e);
			System.exit(-1);
		}

		// Lecture
		String message = "";
		try {
			message = input.readLine();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Erreur lors de la lecture : " + e);
			System.exit(-1);
		}
		LOG.log(Level.INFO, "Lu: {0}", message);

		// Creation de l'objet a envoyer et envoi
		try {
			LOG.log(Level.INFO, "Pre'paration de l'envoi...");
			Personne p = new Personne("Jean", "Pierre", AGE);
			LOG.log(Level.INFO, "Envoi de la personne...");
			output.writeObject(p);
			LOG.log(Level.INFO, "Personne envoyee' !");
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}

		// Fermeture des flux et des sockets
		try {
			input.close();
			output.close();
			socketClient.close();
			socketServeur.close();
			LOG.log(Level.INFO, "Serveur de'connecte'.");
			System.exit(0);
		} catch (IOException e) {
			LOG.log(Level.INFO, "Erreur lors de la fermeture des flux et des sockets : " + e);
			System.exit(-1);
		}

	}
}
