package com.fogforge.backend.exception;

public class NoUniqueCombinationException extends RuntimeException{

    public NoUniqueCombinationException(String message){
        super(message);
    }
}
