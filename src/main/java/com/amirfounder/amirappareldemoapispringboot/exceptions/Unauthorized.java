package com.amirfounder.amirappareldemoapispringboot.exceptions;

public class Unauthorized extends RuntimeException {

    public Unauthorized() {
    }

    public Unauthorized(String message) { super(message); }
}
