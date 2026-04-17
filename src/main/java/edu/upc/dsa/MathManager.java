package edu.upc.dsa;

import edu.upc.dsa.models.*;
import java.util.List;

public interface MathManager
{

    // Afegeix una petició d'operació realitzada per un alumne d'un institut.
    void addOperation(String studentId, String instituteId, String expression);

    // Processa la següent operació en ordre d'arribada i en calcula el valor.
    Operation processOperation();

    // Obté el llistat d'operacions realitzades per un institut concret.
    List<Operation> getOperationsByInstitute(String instituteId);

    // Obté el llistat d'operacions realitzades per un alumne concret.
    List<Operation> getOperationsByStudent(String studentId);

    // Obté la llista d'instituts ordenada descendentment per volum d'operacions.
    List<Institute> getInstitutesSorted();

    // Neteja totes les estructures de dades, això és necessari per reiniciar els tests JUnit.
    void clear();
}