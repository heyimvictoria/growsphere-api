package org.launchcode.growsphere.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(int id) {
        super("Could not find the user with ID " + id);
    }
}
