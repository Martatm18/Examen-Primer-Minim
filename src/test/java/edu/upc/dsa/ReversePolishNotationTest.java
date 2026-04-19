package edu.upc.dsa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReversePolishNotationTest {

    // Variable per guardar la referència al motor de càlcul
    private ReversePolishNotation rpn;

    // Aquest mètode s'executa abans de cada test per tenir el motor net
    @Before
    public void setUp()
    {
        // Inicialitzem la nostra implementació
        this.rpn = new ReversePolishNotationImpl();
    }

    // Test principal en el que fem servir l'exemple exacte que surt a l'enunciat
    @Test
    public void testEvaluateExamplePDF() throws Exception
    {
        // Provem l'expressió "5 1 2 + 4 * + 3 -" que hauria de donar 14
        double result = this.rpn.evaluate("5 1 2 + 4 * + 3 -");
        // Comprovem que el resultat és 14 amb un marge d'error petit per als decimals
        Assert.assertEquals(14.0, result, 0.001);
    }

    // Test per comprovar que el nostre codi detecta errors
    @Test(expected = Exception.class)
    public void testEvaluateError() throws Exception
    {
        // Aquesta expressió està malament perquè li falten números per l'operador '+'
        // El test passarà si el mètode realment llança una excepció
        this.rpn.evaluate("5 +");
    }
}
