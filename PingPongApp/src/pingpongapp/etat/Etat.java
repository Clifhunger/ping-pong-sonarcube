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
public interface Etat {

    public String getMessage();

    public void init();

    public void attenteAck();

    public void echange();
}
