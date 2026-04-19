package edu.upc.dsa.services;

import edu.upc.dsa.MathManager;
import edu.upc.dsa.MathManagerImpl;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//L'anotació @Api és CLAU perquè Swagger detecti aquest fitxer i ens munti la interfície d'usuari.
@Api(value = "/math", description = "Servei REST de Càlcul Matemàtic")
@Path("/math") //Aquesta és la ruta base per on anirem
public class MathService
{

    private MathManager mm;

    public MathService() {
        //Utilitzem la instància del Singleton que vam fer a la part 1 de l'examen
        this.mm = MathManagerImpl.getInstance();

        //Afegim unes dades inicials perquè quan obrim el Swagger
        // hi hagi alguna cosa per defecte i no sembli que està buit o que no funciona.
        if (this.mm.getInstitutesSorted().isEmpty()) {
            this.mm.addOperation("estudiant_prova", "BCN-1", "5 1 2 + 4 * + 3 -");
            this.mm.addOperation("estudiant_prova", "BCN-1", "10 2 /");
        }
    }

    //Per afegir una operació, utilitzem el POST perquè enviem dades noves
    @POST
    @ApiOperation(value = "Afegir una nova operació a la cua")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Operació afegida correctament")
    })
    @Path("/operation")
    @Consumes(MediaType.APPLICATION_JSON) //Aquí li diem que el que ens arriba és un JSON
    public Response addOperation(Operation op)
    {
        // Cridem al manager i li passem els camps del JSON
        this.mm.addOperation(op.getStudentId(), op.getInstituteId(), op.getExpression());
        return Response.status(201).build(); //Tornem un 201 perquè hem "creat" un recurs
    }

    //Per processar l'operació, utilitzem GET perquè demanem que calculi la següent que toca.
    @GET
    @ApiOperation(value = "Processar la següent operació de la cua", response = Operation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Processada correctament", response = Operation.class),
            @ApiResponse(code = 404, message = "No hi ha operacions a la cua")
    })
    @Path("/process")
    @Produces(MediaType.APPLICATION_JSON)//Tornem l'objecte calculat en format JSON
    public Response processOperation() {
        Operation op = this.mm.processOperation();
        // Si dona null és que la cua estava buida, per això, passem un 404.
        if (op == null) return Response.status(404).build();
        return Response.status(200).entity(op).build();//Si tot va bé, ens retorna un 200 OK i l'operació
    }

    //Per llistar operacions d'un institut, utilitzem un GET + PathParam.
    @GET
    @ApiOperation(value = "Llistar operacions per institut")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Llista obtinguda", response = Operation.class, responseContainer="List")
    })
    @Path("/institute/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOpsByInstitute(@PathParam("id") String id) {
        List<Operation> ops = this.mm.getOperationsByInstitute(id);
        //Utilitzem el GenericEntity, necessari perquè Jersey sàpiga enviar llistes
        GenericEntity<List<Operation>> entity = new GenericEntity<List<Operation>>(ops) {};
        return Response.status(200).entity(entity).build();
    }

    //Per llistar operacions d'un alumne
    @GET
    @ApiOperation(value = "Llistar operacions per estudiant")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Llista obtinguda", response = Operation.class, responseContainer="List")
    })
    @Path("/student/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOpsByStudent(@PathParam("id") String id) {
        List<Operation> ops = this.mm.getOperationsByStudent(id);
        GenericEntity<List<Operation>> entity = new GenericEntity<List<Operation>>(ops) {};
        return Response.status(200).entity(entity).build();
    }

    //Per trobar el rànquing d'instituts, és a dir, una llista d'instituts ordenada per activitat.
    @GET
    @ApiOperation(value = "Obtenir el rànquing d'instituts ordenats")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Rànquing obtingut", response = Institute.class, responseContainer="List")
    })
    @Path("/institutes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInstitutesSorted() {
        List<Institute> list = this.mm.getInstitutesSorted();
        GenericEntity<List<Institute>> entity = new GenericEntity<List<Institute>>(list) {};
        return Response.status(200).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "Obtenir informació d'un estudiant", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Dades de l'estudiant", response = Student.class)
    })
    @Path("/student/info/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentInfo(@PathParam("id") String id)
    {
        // Creem un estudiant fals només perquè el Swagger llegeixi la classe Student
        // i la renderitzi a la secció inferior de Models.
        Student s = new Student(id, "Nom d'exemple");
        return Response.status(200).entity(s).build();
    }
}