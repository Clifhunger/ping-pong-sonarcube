/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.PDU;

import java.io.Serializable;

/**
 * Classe qui représente le PDU Init
 * @author ecossard
 */
public class Init implements Serializable{
    
    private int scoreMax;
    public Init(int unScoreMax)
    {
        this.scoreMax=unScoreMax;
    }
    public String toString()
    {
       return "je suis un Client je veux jouer une partie avec un score maximum de "+ this.scoreMax+" points"; 
    }
    public int getScoreMax() {
        return scoreMax;
    }
    
}
