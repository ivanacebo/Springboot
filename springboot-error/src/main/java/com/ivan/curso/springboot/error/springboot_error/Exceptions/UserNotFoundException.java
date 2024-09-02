package com.ivan.curso.springboot.error.springboot_error.Exceptions;

public class UserNotFoundException extends RuntimeException{

   public UserNotFoundException (String message) {
        super(message);
   }
}
