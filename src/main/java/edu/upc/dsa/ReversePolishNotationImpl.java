package edu.upc.dsa;

import edu.upc.dsa.ReversePolishNotation;
import org.apache.log4j.Logger;
import java.util.Stack;

public class ReversePolishNotationImpl implements ReversePolishNotation {

    // Fem servir el logger de log4j perquè no podem fer servir System.out
    final static Logger logger = Logger.getLogger(ReversePolishNotationImpl.class);

    @Override
    public double evaluate(String expression) throws Exception {
        // Posem una traça INFO al principi amb el paràmetre com demana el profe
        logger.info("Inici evaluate: expressió = " + expression);
        // Creem la pila per anar guardant els números
        Stack<Double> stack = new Stack<>();

        // Partim l'expressió per espais per anar mirant cada part (token)
        for (String token : expression.split(" "))
        {
            //Aquí analitzem si el que llegim és un número
            if (token.matches("-?\\d+(\\.\\d+)?"))
            {
                // Si és un número, cap a la pila
                stack.push(Double.parseDouble(token));
            }
            else
            {
                // Si és un operador, necessitem almenys dos números a la pila
                if (stack.size() < 2)
                {
                    logger.error("Expressió mal formada: " + expression); // Traça d'ERROR
                    throw new Exception("Expressió mal formada");
                }
                // Treiem els dos últims números de la pila
                double b = stack.pop();
                double a = stack.pop();

                // Mirem quin símbol és i fem l'operació que toca
                switch (token)
                {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/": stack.push(a / b); break;
                    default:
                        // Si ve un símbol que no entenem, avisem
                        logger.error("Operador no vàlid: " + token);
                        throw new Exception("Operador no vàlid: " + token);
                }
            }
        }

        // Al final de tot, a la pila només ha de quedar el resultat final
        if (stack.size() != 1) {
            logger.error("L'expressió no s'ha pogut reduir correctament: " + expression);
            throw new Exception("Expressió mal formada");
        }

        // Retornem el número que ha quedat
        double result = stack.pop();
        logger.info("Fi evaluate: resultat = " + result);
        return result;
    }
}