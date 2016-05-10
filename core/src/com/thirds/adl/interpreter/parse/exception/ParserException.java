package com.thirds.adl.interpreter.parse.exception;

import com.thirds.adl.exception.ADLException;
import com.thirds.adl.interpreter.parse.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public abstract class ParserException extends ADLException {

    private final Parser parser;

    public ParserException(Parser parser) {

        this.parser = parser;
    }

    @Override
    public void printStackTrace() {

        super.printStackTrace();
        System.err.println("Parser caught error at line " + parser.line + " and column " + parser.column
                + " of file " + parser.fileName + ". Bad line:");

        BufferedReader reader = new BufferedReader(new StringReader(parser.fileContents));
        String result = "";
        try {
            for (int i=0; i<parser.line; i++) {
                result = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println("\t>>> " + result + " <<<");
    }
}
