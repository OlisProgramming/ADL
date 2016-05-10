package com.thirds.adl.interpreter.variable;

import java.util.HashMap;

public class VariableHandler {

    private final HashMap<String, Variable> variableMap;

    public VariableHandler() {

        variableMap = new HashMap<>();
    }

    public void addVariable(String name, Object variableObject) {

        variableMap.put(name, new Variable(variableObject));
    }

    public Object getVariableValue(String name) {

        return variableMap.get(name).getValue();
    }
}
