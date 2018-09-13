/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.etat;
import java.io.*;
import java.net.*;
import pingpongapp.*;
import pingpongapp.PDU.*;

/**
 *
 * @author ecossard
 */
public interface Etat {
    
    /**
     * Permet D'initialiser la connexion et de verifier les paramètre de la partie
     */
    public void init();
    /**
     * permet au client d'attendre la reponse du serveur
     */
    public void attenteAck();
    /**
     * permet au client et au serveur de s'échanger des PDU
     */
    public void echange();
}
