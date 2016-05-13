package com.thirds.adl.interpreter.variable;

import com.thirds.adl.interpreter.parse.Parser;
import com.thirds.adl.interpreter.parse.exception.VariableDoesNotExistException;

import java.util.HashMap;

public class VariableHandler {

    private Parser parser;
    private final HashMap<String, Variable> variableMap;

    public VariableHandler(Parser parser) {

        this.parser = parser;
        variableMap = new HashMap<>();
    }

    public void addVariable(String name, Object variableObject) {

        variableMap.put(name, new Variable(variableObject));
    }

    public void deleteVariable(String name) throws VariableDoesNotExistException {

        if (variableMap.containsKey(name))
            variableMap.remove(name);
        else throw new VariableDoesNotExistException(parser, name);
    }

    public Object getVariableValue(String name) {

        if (variableMap.containsKey(name))
            return variableMap.get(name).getValue();
        else return null;
    }
}
