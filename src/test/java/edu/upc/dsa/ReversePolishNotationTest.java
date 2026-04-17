package edu.upc.dsa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReversePolishNotationTest {

    private ReversePolishNotation rpn;

    @Before
    public void setUp() {
        this.rpn = new ReversePolishNotationImpl();
    }

    @Test
    public void testEvaluateExamplePDF() throws Exception {
        double result = this.rpn.evaluate("5 1 2 + 4 * + 3 -");
        Assert.assertEquals(14.0, result, 0.001); // Comprova que el resultat és 14
    }

    @Test(expected = Exception.class)
    public void testEvaluateError() throws Exception {
        // Prova que llança excepció si l'expressió és incorrecta
        this.rpn.evaluate("5 +");
    }
}
