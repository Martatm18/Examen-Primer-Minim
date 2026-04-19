package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;
import java.util.*;

public class MathManagerImpl implements MathManager {

    // Aquí fem servir el Logger per no fer cap System.out.println
    private final static Logger log = Logger.getLogger(MathManagerImpl.class);

    // Variable estàtica per guardar la instància única
    private static MathManagerImpl instance;

    // Estructures de dades: List per a les operacions i Map per buscar instituts ràpid
    private List<Operation> pending; //Cua per a les pendents.
    private List<Operation> processed; // Historial de les ja fetes
    private Map<String, Institute> institutes; // Per guardar els instituts per ID

    // Referència a la interfície del motor RPN
    private ReversePolishNotation rpn;

    // Constructor privat perquè ningú pugui fer 'new' des de fora
    private MathManagerImpl() {
        this.pending = new LinkedList<>();
        this.processed = new ArrayList<>();
        this.institutes = new HashMap<>();
        this.rpn = new ReversePolishNotationImpl(); // Inicialitzem la nostra lògica RPN
    }

    // Si no està creada la instància, la crea; si ja existeix, la retorna
    public static MathManagerImpl getInstance() {
        if (instance == null) instance = new MathManagerImpl();
        return instance;
    }

    @Override
    public void addOperation(String studentId, String instituteId, String expression) {
        // Traça d'inici amb paràmetres
        log.info("Inici addOperation - Paràmetres: studentId=" + studentId + ", instituteId=" + instituteId + ", expression=" + expression);

        // Si l'institut no existeix encara al sistema, el creem i el fiquem al Map
        if (!institutes.containsKey(instituteId)) {
            institutes.put(instituteId, new Institute(instituteId, "Institut " + instituteId));
        }

        // Creem l'objecte operació i el fiquem a la cua de pendents
        Operation op = new Operation(studentId, instituteId, expression);
        pending.add(op);

        log.info("Fi addOperation - Operacions a la cua: " + pending.size());
    }

    @Override
    public Operation processOperation() {
        log.info("Inici processOperation");

        // Si no hi ha operacions a la cua, enviem un error i retornem null
        if (pending.isEmpty()) {
            log.error("Error en processOperation: No hi ha operacions pendents");
            return null;
        }

        // Agafem la primera operació que va entrar
        Operation op = pending.remove(0);

        try {
            // Calculem el resultat utilitzant el mètode evaluate del nostre motor RPN
            double valorResultat = rpn.evaluate(op.getExpression());
            op.setResult(valorResultat);

            // Guardem l'operació a l'historial i sumem 1 al comptador de l'institut
            processed.add(op);
            institutes.get(op.getInstituteId()).incOperations();

            log.info("Fi processOperation - Resultat: " + valorResultat);
            return op;
        } catch (Exception e)
        {
            // Registrem un error greu si el motor de càlcul falla inesperadament
            log.fatal("Falla crítica en processar RPN: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Operation> getOperationsByInstitute(String instituteId) {
        log.info("Inici getOperationsByInstitute - Paràmetre: instituteId=" + instituteId);
        List<Operation> res = new ArrayList<>();

        // Recorrem totes les processades i filtrem pel id de l'institut
        for (Operation o : processed) {
            if (o.getInstituteId().equals(instituteId)) {
                res.add(o);
            }
        }

        log.info("Fi getOperationsByInstitute - Trobades: " + res.size());
        return res;
    }

    @Override
    public List<Operation> getOperationsByStudent(String studentId) {
        log.info("Inici getOperationsByStudent - Paràmetre: studentId=" + studentId);
        List<Operation> res = new ArrayList<>();

        // El mateix que l'anterior però buscant pel id de l'estudiant
        for (Operation o : processed) {
            if (o.getStudentId().equals(studentId)) {
                res.add(o);
            }
        }

        log.info("Fi getOperationsByStudent - Trobades: " + res.size());
        return res;
    }

    @Override
    public List<Institute> getInstitutesSorted() {
        log.info("Inici getInstitutesSorted");

        // Passem els valors del Map a una llista per poder ordenar-los
        List<Institute> llista = new ArrayList<>(institutes.values());

        // Ordenem la llista de més operacions a menys (ordre descendent)
        llista.sort((a, b) -> Integer.compare(b.getNumOperations(), a.getNumOperations()));

        log.info("Fi getInstitutesSorted - Instituts retornats: " + llista.size());
        return llista;
    }

    @Override
    public void clear()
    // Mètode per netejar-ho tot abans de cada test JUnit
    {
        log.info("Inici clear - Reiniciant dades del sistema");
        pending.clear();
        processed.clear();
        institutes.clear();
        log.info("Fi clear - Sistema net");
    }
}

