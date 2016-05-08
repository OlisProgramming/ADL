package com.thirds.adl.exception;

/**
 * The base Exception class for ADL, inheriting Exception.
 * @see Exception
 */
public abstract class ADLException extends Exception {

    @Override
    public void printStackTrace() {
        System.err.println("*****\nException " + getExceptionName() + "\n*****");
    }

    /**
     * Shows the name of the exception (in UpperCamelCase)
     * @return the name
     */
    public abstract String getExceptionName();
}
