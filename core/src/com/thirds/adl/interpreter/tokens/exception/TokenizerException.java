package com.thirds.adl.interpreter.tokens.exception;

import com.thirds.adl.exception.ADLException;
import com.thirds.adl.interpreter.tokens.Tokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public abstract class TokenizerException extends ADLException {

    private Tokenizer tokenizer;

    TokenizerException(Tokenizer tokenizer) {

        this.tokenizer = tokenizer;
    }

    @Override
    public void printStackTrace() {

        super.printStackTrace();
        System.err.println("Tokenizer caught error at line " + tokenizer.line + " and column " + tokenizer.column
                + " of file " + tokenizer.fileName + ". Bad line:");

        BufferedReader reader = new BufferedReader(new StringReader(tokenizer.fileContents));
        String result = "";
        try {
            for (int i=0; i<tokenizer.line; i++) {
                result = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println("\t>>> " + result + " <<<");
    }
}
