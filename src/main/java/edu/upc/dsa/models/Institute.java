package edu.upc.dsa.models;

//Classe que representa un institut i fa el seguiment de les seves operacions.
public class Institute
{
    private String id;             // Aquest és l'identificador únic de l'institut
    private String name;           // Aquest és el nom de l'institut
    private int numOperations;     // Això és el comptador acumulat d'operacions realitzades

    public Institute() {} //Constructor buit necessari per JSON.

    // Constructor per inicialitzar l'institut
    public Institute(String id, String name)
    {
        this.id = id;
        this.name = name;
        this.numOperations = 0; // Començarem amb zero operacions
    }

    // Mètodes Getter i Setter per consultar i modificar la informació de l'operació

    //Mètode per consultar l'id de l'institut
    public String getId()
    {
        return id;
    }

    //Mètode que assigna l'identificador únic de l'institut
    public void setId(String id)
    {
        this.id = id;
    }

    //Mètode per consultar el nom
    public String getName()
    {
        return name;
    }

    //Mètode que assigna el nom de l'institut
    public void setName(String name)
    {
        this.name = name;
    }

    //Mètode per consultar el nombre d'operacions
    public int getNumOperations()
    {
        return numOperations;
    }

    //Mètode que assigna el nombre d'operacions de forma manual.
    public void setNumOperations(int numOperations)
    {
        this.numOperations = numOperations;
    }

    // Mètode per incrementar en una unitat el comptador d'operacions de l'institut.
    public void incOperations()
    {
        this.numOperations++;
    }
}
