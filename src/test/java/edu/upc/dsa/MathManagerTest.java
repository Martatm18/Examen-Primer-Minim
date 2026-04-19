package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class MathManagerTest
{

    // Aquí guardem el gestor per poder-lo fer servir a tots els tests
    private MathManager mm;

    // Això s'executa abans de cada test per preparar el terreny
    @Before
    public void setUp()
    {
        // Agafem la instància del Singleton per començar a provar coses
        this.mm = MathManagerImpl.getInstance();
    }

    // Això ho executem quan acaba cada test
    @After
    public void tearDown() {
        // Netegem les llistes perquè no es barregin les dades d'un test amb el següent
        this.mm.clear();
    }

    // Ara anem a provar si el sistema sap afegir i calcular operacions
    @Test
    public void testAddAndProcessOperation() {
        // Afegim un parell d'operacions a la cua per veure si les guarda bé
        mm.addOperation("estudiante1", "BCN-1", "5 1 2 + 4 * + 3 -"); // Hauria de donar 14
        mm.addOperation("estudiante2", "BCN-2", "10 2 /");           // Hauria de donar 5

        // Processem la primera que hem posat (la de 14.0, perquè va en ordre)
        Operation op1 = mm.processOperation();

        // Mirem que no sigui null i que el resultat sigui realment 14
        Assert.assertNotNull(op1);
        Assert.assertEquals(14.0, op1.getResult(), 0.001);
        Assert.assertEquals("BCN-1", op1.getInstituteId());

        // Ara processem la següent i mirem que doni 5
        Operation op2 = mm.processOperation();
        Assert.assertEquals(5.0, op2.getResult(), 0.001);
    }

    // Provem si el rànquing d'instituts s'ordena bé
    @Test
    public void testGetInstitutesSorted() {
        // Afegim operacions de diferents instituts, en aquest cas el B en tindrà dues
        mm.addOperation("s1", "INSTI-A", "2 2 +");
        mm.addOperation("s2", "INSTI-B", "2 2 +");
        mm.addOperation("s3", "INSTI-B", "2 2 +"); // El B tendrá 2 operaciones

        // Les processem totes perquè si no, no sumen al comptador
        mm.processOperation();
        mm.processOperation();
        mm.processOperation();

        // Obtenim la llista d'instituts a veure com han quedat
        List<Institute> sorted = mm.getInstitutesSorted();

        // El primer ha de ser el B perquè és el que té més "moviment"
        Assert.assertEquals("INSTI-B", sorted.get(0).getId());
        Assert.assertEquals(2, sorted.get(0).getNumOperations());
    }

    // Mirem si podem trobar les operacions que ha fet un alumne concret
    @Test
    public void testGetOperationsByStudent()
    {
        // Afegim una operació i la processem
        mm.addOperation("juan", "BCN-1", "10 10 +");
        mm.processOperation();

        // Busquem a veure què ha fet el juan
        List<Operation> ops = mm.getOperationsByStudent("juan");

        // Hauria de sortir només una operació i que l'expressió sigui la que hem posat
        Assert.assertEquals(1, ops.size());
        Assert.assertEquals("10 10 +", ops.get(0).getExpression());
    }
}
