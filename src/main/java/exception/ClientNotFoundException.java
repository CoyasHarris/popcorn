/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;


/**
 *
 * @author Harris
 */
public class ClientNotFoundException extends RuntimeException{
   private static final long serialVersionUID = 1L;

    public ClientNotFoundException() {
        super();
    }

    public ClientNotFoundException(String customMessage) {
        super(customMessage);
    }
}
