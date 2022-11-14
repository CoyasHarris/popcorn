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
public class UserNotFoundException extends RuntimeException { 

    public UserNotFoundException(Long id) {
        super("The user id '" + id + "' does not exist in our records");
    }
    
}
