/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.pdu;

import java.io.Serializable;

/**
 *
 * @author ecossard
 */
public class Init implements Serializable {

    private static final long serialVersionUID = 1L;
    private int scoreMax;

    public Init(int unScoreMax) {
        this.scoreMax = unScoreMax;
    }

    @Override
    public String toString() {
        return "je suis un Client je veux jouer une partie avec un score maximum de " + this.scoreMax + " points";
    }

    public int getMessage() {
        return getScoreMax();
    }

    public int getScoreMax() {
        return scoreMax;
    }

}
