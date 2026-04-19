package edu.upc.dsa.models;

//Classe que representa un alumne al sistema.
public class Student
{
    private String id;     // Aquest és l'identificador únic de l'alumne
    private String name;   // Aquest és el nom de l'alumne

    public Student() {} //Constructor buit necessari per JSON.

    // Constructor per inicialitzar un nou alumne
    public Student(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    // Mètodes Getter i Setter per consultar i modificar la informació de l'operació

    //Mètode per consultar l'id de l'estudiant.
    public String getId()
    {
        return id;
    }

    //Mètode per assignar l'id de l'estudiant.
    public void setId(String id)
    {
        this.id = id;
    }

    //Mètode per consultar el nom de l'estudiant
    public String getName()
    {
        return name;
    }

    //Mètode per assignar el nom de l'estudiant
    public void setName(String name)
    {
        this.name = name;
    }
}
