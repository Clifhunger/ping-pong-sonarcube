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
public class Service implements Serializable {
    private static final long serialVersionUID = 1L;
    private String message;

    public Service(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
