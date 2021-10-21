package com.amirfounder.amirappareldemoapispringboot.utils;

/**
 * Base validator for the backend API
 */
public class BaseValidator {

    public boolean isNotEmpty(String value) {
        return value.length() > 0;
    }

}
