/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp;

import java.util.Random;

/**
 *
 * @author clifhunger
 */
public class ParamPartie {
    /**
     * le score max d'une partie
     */
    private int scoreMax;
    /**
     * le score du joueur
     */
    private int monScore;
    /**
     * le score du joueur advers
     */
    private int sonScore;
    /**
     * le numéros du service en ccours
     */
    private int numeroDuService;
    /**
     * le probabilité du smash
     */
    private int probaSmash;
    /**
     * la probabilité de l'ace advers
     */
    private int probaAceAdversaire;
    /**
     * la probabilité de l'abandon
     */
    private int probaAbandon;
    /**
     * constructeur de la classe partie
     */
    public ParamPartie()
    {
        Random r=new Random();
        numeroDuService=0;
        monScore=0;
        sonScore=0;
        this.scoreMax=0;
        probaSmash=10+r.nextInt(41-10);
        probaAceAdversaire=10+r.nextInt(31-10);
        probaAbandon=r.nextInt(5)+1;
        
    }
    /**
     * constructeur de partie
     * @param scoreMax 
     *                  le score max du client
     */
    public ParamPartie(int scoreMax)
    {
        Random r=new Random();
        numeroDuService=0;
        monScore=0;
        sonScore=0;
        this.scoreMax=scoreMax;
        System.out.println(scoreMax);
        probaSmash=10+r.nextInt(41-10);
        probaAceAdversaire=10+r.nextInt(31-10);
        probaAbandon=r.nextInt(5)+1;
    }
    public int getScoreMax() {
        return scoreMax;
    }
    public int getMonScore() {
        return monScore;
    }

    public int getSonScore() {
        return sonScore;
    }
   
    public void setScoreMax(int scoreMax) {
        this.scoreMax = scoreMax;
    }

    public void setMonScore(int monScore) {
        this.monScore = monScore;
    }

    public void setSonScore(int sonScore) {
        this.sonScore = sonScore;
    }
    /**
     * permet d'incrémenter l'attribut monscore
     */
    public void jeMarque()
    {
        monScore++;
    }
    /**
     * permet d'incrémenter l'attribut sonscore
     */
    public void ilMarque()
    {
        sonScore++;
    }
     public int getNumeroDuService() {
        return numeroDuService;
    }
     public void resetNumeroDuService() {
        this.numeroDuService =0;
    }
     /**
      * permet d'incrémenter l'attribut numeroDuService
      */
     public void serviceSuivant()
     {
         this.numeroDuService++;
     }

    public int getProbaSmash() {
        return probaSmash;
    }

    public int getProbaAceAdversaire() {
        return probaAceAdversaire;
    }

    public int getProbaAbandon() {
        return probaAbandon;
    }
}
