package com.thirds.adl.interpreter.variable;

import java.util.HashMap;

public class VariableHandler {

    private HashMap<String, Variable> variableMap;

    public VariableHandler() {

        variableMap = new HashMap<>();
    }

    public void addGlobalVariable(String name, Object variableObject) {

        variableMap.put(name, new Variable(variableObject));
    }

    public Object getGlobalVariableValue(String name) {

        return variableMap.get(name).getValue();
    }
}
