package com.thirds.adl.test;

public class UnexpectedVariableValueTestException extends TestException {

    private String variableName;
    private Object actualValue;
    private Object expectedValue;

    public UnexpectedVariableValueTestException(String variableName, Object actualValue, Object expectedValue) {
        this.variableName = variableName;
        this.actualValue = actualValue;
        this.expectedValue = expectedValue;
    }

    @Override
    public void printStackTrace() {

        super.printStackTrace();
        System.err.println("Variable " + variableName + " was " + actualValue.toString() + " rather than expected value " + expectedValue + ".");
    }

    @Override
    public String getExceptionName() {
        return "UnexpectedVariableValueTestException";
    }
}
