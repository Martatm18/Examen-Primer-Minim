package edu.upc.dsa.models;

public class Operation
{
    private String studentId;    // És l'identificador de l'alumne que fa l'operació
    private String instituteId;  // És l'identificador de l'institut on es realitza
    private String expression;   // És l'expressió matemàtica
    private Double result;       // És el resultat del càlcul

    // Constructor per registrar una nova operació
    public Operation(String studentId, String instituteId, String expression) {
        this.studentId = studentId;
        this.instituteId = instituteId;
        this.expression = expression;
    }

    // Mètodes Getter per consultar la informació de l'operació
    public String getStudentId() //Mètode Getter per consultar l'id de l'estudiant
    {
        return studentId;
    }

    public String getInstituteId() //Mètode Getter per consultar l'id de l'institut
    {
        return instituteId;
    }

    public String getExpression() //Mètode Getter per consultar l'expressió matemàtica
    {
        return expression;
    }

    public Double getResult() //Mètode Getter per consultar el resultat del càlcul
    {
        return result;
    }

    // Mètode per assignar el resultat un cop calculat
    public void setResult(Double result)
    {
        this.result = result;
    }
}
