package edu.upc.dsa.models;

//Classe que representa un institut i fa el seguiment de les seves operacions.
public class Institute
{
    private String id;             // Aquest és l'identificador únic de l'institut
    private String name;           // Aquest és el nom de l'institut
    private int numOperations;     // Això és el comptador acumulat d'operacions realitzades

    // Constructor per inicialitzar l'institut
    public Institute(String id, String name)
    {
        this.id = id;
        this.name = name;
        this.numOperations = 0; // Començarem amb zero operacions
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
    public int getNumOperations() //Mètode Getter per consultar el numero d'operacions
    {
        return numOperations;
    }

    // Mètode per incrementar en una unitat el comptador d'operacions de l'institut.
    public void incOperations()
    {
        this.numOperations++;
    }
}
