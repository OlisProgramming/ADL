package com.thirds.adl.interpreter.parse.exception;

import com.thirds.adl.interpreter.parse.Parser;
import com.thirds.adl.interpreter.parse.exception.ParserException;

public class VariableDoesNotExistException extends ParserException {

    private Parser parser;
    private String name;

    public VariableDoesNotExistException(Parser parser, String name) {
        super(parser);
    }

    @Override
    public void printStackTrace() {

        super.printStackTrace();
        System.err.println("Variable " + name + " doesn't exist.");
    }

    @Override
    public String getExceptionName() {
        return "VariableDoesNotExistException";
    }
}
