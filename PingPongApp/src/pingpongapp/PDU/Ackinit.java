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
public class Ackinit implements Serializable {

    private static final long serialVersionUID = 1L;
    private String reponse;

    public Ackinit(String reponse) {
        this.reponse = reponse;
    }

    public String getMessage() {
        return reponse;
    }

    @Override
    public String toString() {
        if (reponse.equals("oui")) {
            return "j'accepte la partie";
        } else {
            return "je refuse la partie";
        }
    }
}
