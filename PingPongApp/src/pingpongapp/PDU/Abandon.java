/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpongapp.pdu;

import java.io.Serializable;

/**
 *
 * @author clifhunger
 */
public class Abandon implements Serializable{
     private String message;
    public Abandon(String message)
    {
        this.message=message;
    }
    public String toString()
    {
        return message;
    }
}