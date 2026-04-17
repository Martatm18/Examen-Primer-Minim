package edu.upc.dsa;

import edu.upc.dsa.ReversePolishNotation;
import org.apache.log4j.Logger;
import java.util.Stack;

public class ReversePolishNotationImpl implements ReversePolishNotation {

    final static Logger logger = Logger.getLogger(ReversePolishNotationImpl.class);

    @Override
    public double evaluate(String expression) throws Exception {
        logger.info("Inici evaluate: expressió = " + expression);
        Stack<Double> stack = new Stack<>();

        // Separem l'expressió per espais per obtenir els tokens
        for (String token : expression.split(" ")) {
            if (token.matches("-?\\d+(\\.\\d+)?")) { // Si és un número
                stack.push(Double.parseDouble(token));
            } else {
                // Si és un operador, necessitem almenys dos números a la pila
                if (stack.size() < 2) {
                    logger.error("Expressió mal formada: " + expression);
                    throw new Exception("Expressió mal formada");
                }
                double b = stack.pop();
                double a = stack.pop();

                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/": stack.push(a / b); break;
                    default:
                        logger.error("Operador no vàlid: " + token);
                        throw new Exception("Operador no vàlid: " + token);
                }
            }
        }

        if (stack.size() != 1) {
            logger.error("L'expressió no s'ha pogut reduir correctament: " + expression);
            throw new Exception("Expressió mal formada");
        }

        double result = stack.pop();
        logger.info("Fi evaluate: resultat = " + result);
        return result;
    }
}