package com.thirds.adl.interpreter.variable;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.thirds.adl.interpreter.tokens.Token;
import com.thirds.adl.interpreter.tokens.TokenType;
import com.thirds.adl.interpreter.tokens.Tokenizer;
import com.thirds.adl.interpreter.tokens.exception.InvalidMathTokenException;

public class MathExpression {

    private Tokenizer tokenizer;
    private Queue<Token> tokensPostfix;

    public MathExpression(Tokenizer tokenizer, Queue<Token> tokensInfix) {

        this.tokenizer = tokenizer;
        tokensPostfix = shuntingYard(tokensInfix);
    }

    /**
     * Uses the Shunting Yard Algorithm to change order of tokens from infix into postfix.
     * @param tokensInfix the tokens before modification
     * @return the postfix tokens
     */
    private Queue<Token> shuntingYard(Queue<Token> tokensInfix) {

        Array<Token> operatorStack = new Array<>();
        Queue<Token> outputStack = new Queue<>();

        for (Token tk: tokensInfix) {
            try {

                int precedence = getOperatorPrecedence(tk);
                if (precedence != -1) {
                    if (operatorStack.size == 0) {
                        operatorStack.add(tk);
                    } else {
                        while (operatorStack.size != 0) {

                            int precedenceStack = getOperatorPrecedence(operatorStack.peek());
                            if (precedence < precedenceStack
                                    || (precedence == precedenceStack && isLeftAssociative(tk))) {
                                outputStack.addLast(operatorStack.pop());
                            }
                        }
                        operatorStack.add(tk);
                    }
                }
                else outputStack.addLast(tk);

            } catch (InvalidMathTokenException e) {
                e.printStackTrace();
                return new Queue<>();
            }
        }
        while (operatorStack.size != 0) {
            outputStack.addLast(operatorStack.pop());
        }

        return outputStack;
    }

    /**
     * Get the operator precedence of a token. If it is not an operator, it has precedence -1.
     * @param token the token to test for precedence.
     * @return the precedence value.
     */
    private int getOperatorPrecedence(Token token) throws InvalidMathTokenException {

        switch (token.getTokenType()) {

            case VAL_INT:
                return -1;
            case OPR_PLUS:
                return 2;
            default:
                throw new InvalidMathTokenException(tokenizer, token.getTokenType());
        }
    }

    /**
     * Get whether a token is left associative. If it is not an operator, it has 'right' associativity (false).
     * @param token the token to test for associativity.
     * @return the associativity value.
     */
    private boolean isLeftAssociative(Token token) throws InvalidMathTokenException {

        switch (token.getTokenType()) {

            case VAL_INT:
                return false;
            case OPR_PLUS:
                return true;
            default:
                throw new InvalidMathTokenException(tokenizer, token.getTokenType());
        }
    }

    @Override
    public String toString() {

        String result = "MathExpression\n";
        for (Token tk: tokensPostfix) {
            result += "\t\t" + tk.toString() + "\n";
        }
        return result;
    }

    public Number evaluate(VariableHandler variableHandler) {

        Array<Token> stack = new Array<>(tokensPostfix.size);
        for (Token tk: tokensPostfix) {
            switch (tk.getTokenType()) {

                case OPR_PLUS:
                    stack.add(new Token(TokenType.VAL_INT,
                            (int)(stack.pop().getValue()) + (int)(stack.pop().getValue()),
                            0, 0));
                    break;

                default: /* No need to catch random extra tokens here: InvalidMathExpressionException is caught above */
                    stack.add(tk);
            }
        }

        return (Number)stack.pop().getValue();
    }
}
