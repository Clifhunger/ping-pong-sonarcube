/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.etat;

/**
 *
 * @author ecossard
 */
public abstract class Etat {

    private static final String NOT_SUPPORTED = "Not supported yet.";

    public String getMessage() {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    public void init() {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    public void attenteAck() {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    public void echange() {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }
}
