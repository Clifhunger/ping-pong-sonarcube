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
    private int IdPartie;
    private int scoreMax;
    private int monScore;
    private int sonScore;
    private Service serviceEncours;
    private int numeroDuService;
    private int probaSmash;
    private int probaAceAdversaire;
    private int probaAbandon;

    public ParamPartie() {
        Random r = new Random();
        numeroDuService = 0;
        monScore = 0;
        sonScore = 0;
        this.scoreMax = 0;
        probaSmash = 10 + r.nextInt(41 - 10);
        probaAceAdversaire = 10 + r.nextInt(31 - 10);
        probaAbandon = r.nextInt(5) + 1;

    }

    public ParamPartie(int scoreMax) {
        Random r = new Random();
        numeroDuService = 0;
        monScore = 0;
        sonScore = 0;
        this.scoreMax = scoreMax;
        probaSmash = 10 + r.nextInt(41 - 10);
        probaAceAdversaire = 10 + r.nextInt(31 - 10);
        probaAbandon = r.nextInt(5) + 1;

    }

    public int getScoreMax() {
        return scoreMax;
    }

    public Service getServiceEncours() {
        return serviceEncours;
    }

    public int getMonScore() {
        return monScore;
    }

    public int getSonScore() {
        return sonScore;
    }

    public void setServiceEncours(Service serviceEncours) {
        this.serviceEncours = serviceEncours;
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

    public void jeMarque() {
        monScore++;
    }

    public void ilMarque() {
        sonScore++;
    }

    public int getNumeroDuService() {
        return numeroDuService;
    }

    public void resetNumeroDuService() {
        this.numeroDuService = 0;
    }

    public void serviceSuivant() {
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

    /**
     * @return the iDPartie
     */
    public int getIDPartie() {
        return IdPartie;
    }

    /**
     * @param iDPartie the iDPartie to set
     */
    public void setIDPartie(int iDPartie) {
        this.IdPartie = iDPartie;
    }
}
