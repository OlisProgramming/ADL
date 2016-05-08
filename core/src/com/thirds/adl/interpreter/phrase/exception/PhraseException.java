package com.thirds.adl.interpreter.phrase.exception;

import com.thirds.adl.exception.ADLException;
import com.thirds.adl.interpreter.phrase.PhraseGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public abstract class PhraseException extends ADLException {

    private PhraseGenerator phraseGenerator;

    PhraseException(PhraseGenerator phraseGenerator) {

        this.phraseGenerator = phraseGenerator;
    }

    @Override
    public void printStackTrace() {

        super.printStackTrace();
        System.err.println("PhraseGenerator caught error at line " + phraseGenerator.line + " and column " + phraseGenerator.column
                + " of file " + phraseGenerator.fileName + ". Bad line:");

        BufferedReader reader = new BufferedReader(new StringReader(phraseGenerator.fileContents));
        String result = "";
        try {
            for (int i=0; i<phraseGenerator.line; i++) {
                result = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println("\t>>> " + result + " <<<");
    }
}
