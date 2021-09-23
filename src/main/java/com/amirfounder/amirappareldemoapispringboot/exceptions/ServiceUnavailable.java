package com.amirfounder.amirappareldemoapispringboot.exceptions;

public class ServiceUnavailable extends RuntimeException {

    public ServiceUnavailable() {}
    public ServiceUnavailable(String message) {super(message);}

}
