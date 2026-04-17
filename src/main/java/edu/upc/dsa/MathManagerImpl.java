package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;
import java.util.*;

public class MathManagerImpl implements MathManager {

    private final static Logger log = Logger.getLogger(MathManagerImpl.class);

    private static MathManagerImpl instance;

    private List<Operation> pending;
    private List<Operation> processed;
    private Map<String, Institute> institutes;

    private ReversePolishNotation rpn;

    private MathManagerImpl() {
        this.pending = new LinkedList<>();
        this.processed = new ArrayList<>();
        this.institutes = new HashMap<>();
        this.rpn = new ReversePolishNotationImpl();
    }

    public static MathManagerImpl getInstance() {
        if (instance == null) instance = new MathManagerImpl();
        return instance;
    }

    @Override
    public void addOperation(String studentId, String instituteId, String expression) {
        log.info("Inici addOperation - Paràmetres: studentId=" + studentId + ", instituteId=" + instituteId + ", expression=" + expression);

        if (!institutes.containsKey(instituteId)) {
            institutes.put(instituteId, new Institute(instituteId, "Institut " + instituteId));
        }

        Operation op = new Operation(studentId, instituteId, expression);
        pending.add(op);

        log.info("Fi addOperation - Operacions a la cua: " + pending.size());
    }

    @Override
    public Operation processOperation() {
        log.info("Inici processOperation");

        if (pending.isEmpty()) {
            log.error("Error en processOperation: No hi ha operacions pendents");
            return null;
        }

        Operation op = pending.remove(0);

        try {
            double valorResultat = rpn.evaluate(op.getExpression());
            op.setResult(valorResultat);

            processed.add(op);
            institutes.get(op.getInstituteId()).incOperations();

            log.info("Fi processOperation - Resultat: " + valorResultat);
            return op;
        } catch (Exception e) {
            log.fatal("Falla crítica en processar RPN: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Operation> getOperationsByInstitute(String instituteId) {
        log.info("Inici getOperationsByInstitute - Paràmetre: instituteId=" + instituteId);
        List<Operation> res = new ArrayList<>();

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
        List<Institute> llista = new ArrayList<>(institutes.values());

        llista.sort((a, b) -> Integer.compare(b.getNumOperations(), a.getNumOperations()));

        log.info("Fi getInstitutesSorted - Instituts retornats: " + llista.size());
        return llista;
    }

    @Override
    public void clear() {
        log.info("Inici clear - Reiniciant dades del sistema");
        pending.clear();
        processed.clear();
        institutes.clear();
        log.info("Fi clear - Sistema net");
    }
}

