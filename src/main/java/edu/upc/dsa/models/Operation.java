package edu.upc.dsa.models;

public class Operation
{
    private String studentId;    // És l'identificador de l'alumne que fa l'operació
    private String instituteId;  // És l'identificador de l'institut on es realitza
    private String expression;   // És l'expressió matemàtica
    private Double result;       // És el resultat del càlcul

    public Operation() {} //Constructor buit necessari per JSON.

    // Constructor per registrar una nova operació
    public Operation(String studentId, String instituteId, String expression)
    {
        this.studentId = studentId;
        this.instituteId = instituteId;
        this.expression = expression;
    }

    // Mètodes Getter i Setter per consultar i modificar la informació de l'operació

    //Mètode per consultar l'id de l'estudiant
    public String getStudentId()
    {
        return studentId;
    }

    //Mètode que assigna l'ID de l'alumne.
    public void setStudentId(String studentId)
    {
        this.studentId = studentId;
    }

    //Mètode per consultar l'id de l'institut
    public String getInstituteId()
    {
        return instituteId;
    }

    // Mètode per assignar l'id de l'institut associat a aquesta operació.
    public void setInstituteId(String instituteId)
    {
        this.instituteId = instituteId;
    }

    //Mètode per consultar l'expressió matemàtica
    public String getExpression()
    {
        return expression;
    }

    //Mètode que permet modificar l'expressió
    public void setExpression(String expression)
    {
        this.expression = expression;
    }

    //Mètode per consultar el resultat del càlcul
    public Double getResult()
    {
        return result;
    }

    // Mètode per assignar el resultat un cop calculat
    public void setResult(Double result)
    {
        this.result = result;
    }
}
