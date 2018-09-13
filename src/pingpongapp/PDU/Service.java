/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.PDU;

import java.io.Serializable;

/**
 *  Classe qui représente le PDU du Service
 * @author clifhunger
 */
public class Service implements Serializable {
    private String message;
    public Service(String message)
    {
        this.message=message;
    }
    public String toString()
    {
        return message;
    }
}
