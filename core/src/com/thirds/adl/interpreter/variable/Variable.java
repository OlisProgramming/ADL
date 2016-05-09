package com.thirds.adl.interpreter.variable;

/**
 * The basic variable class. All ADL Variables are instances of this class or its children.
 */
public class Variable {

    private Object value;

    Variable(Object value) {

        setValue(value);
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
