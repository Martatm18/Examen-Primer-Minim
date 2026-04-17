package edu.upc.dsa.models;

//Classe que representa un alumne al sistema.
public class Student
{
    private String id;     // Aquest és l'identificador únic de l'alumne
    private String name;   // Aquest és el nom de l'alumne

    // Constructor per inicialitzar un nou alumne
    public Student(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    // Mètodes Getter per consultar les dades
    public String getId() //Mètode Getter per consultar l'id
    {
        return id;
    }
    public String getName() //Mètode Getter per consultar el nom
    {
        return name;
    }
}
