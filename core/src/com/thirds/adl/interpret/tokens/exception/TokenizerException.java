package com.thirds.adl.interpret.tokens.exception;

import com.thirds.adl.interpret.tokens.Tokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class TokenizerException extends Exception {

    private Tokenizer tokenizer;

    TokenizerException(Tokenizer tokenizer) {

        this.tokenizer = tokenizer;
    }

    @Override
    public void printStackTrace() {

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
        System.err.println(result);
    }
}
