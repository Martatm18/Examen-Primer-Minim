package edu.upc.dsa.util;

import edu.upc.dsa.ReversePolishNotation;
import java.util.Stack;

public class ReversePolishNotationImpl implements ReversePolishNotation {

    @Override
    public double evaluate(String expression) throws Exception {
        Stack<Double> stack = new Stack<>();

        // Separem l'expressió per espais
        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            if (token.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            }
            else if (token.equals("-")) {
                double b = stack.pop();
                stack.push(stack.pop() - b);
            }
            else if (token.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            }
            else if (token.equals("/")) {
                double b = stack.pop();
                stack.push(stack.pop() / b);
            }
            else {
                // Si no és un operador, intentem convertir-ho a número
                try {
                    stack.push(Double.parseDouble(token));
                } catch (NumberFormatException e) {
                    throw new Exception("Token no vàlid: " + token);
                }
            }
        }

        // El resultat final és l'últim element que queda a la pila
        return stack.pop();
    }
}