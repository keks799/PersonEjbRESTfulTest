package controllers;

import models.Person;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Business_Book on 11.09.2015.
 */

@Path("/person")
@Stateful
@SessionScoped
public class PersonRestResource implements Serializable {
    @EJB
    private PersonController personController;

    @GET
    @Path( "/all" )
    @Produces( {MediaType.APPLICATION_JSON} )
    public List<Person> listPersons() {
        return personController.listOfPersons();
    }

    @POST
    @Produces( {MediaType.APPLICATION_JSON} )
    @Path("/new")
    public Response newPerson(
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("middleName") String middleName,
            @FormParam("birthDate") String birthDateString
    ){
        Date birthDate = getDate(birthDateString);
        Person person = new Person(firstName, lastName, middleName, birthDate);
        Person result = personController.savePerson(person);
        return Response.status(201).entity(Collections.singletonList(result)).build();
    }

    private Date getDate(@FormParam("birthDate") String birthDateString) {
        Date birthDate = null;
        try {
            birthDate = new SimpleDateFormat("dd.MM.yyyy").parse(birthDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return birthDate;
    }

    @POST
    @Produces( MediaType.APPLICATION_JSON)
    @Path("/edit")
    public Response editPerson(
            @FormParam("id") Long id,
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("middleName") String middleName,
            @FormParam("birthDate") String birthDateString
    ) {
        Person person = new Person(id, firstName, lastName, middleName, getDate(birthDateString));
        person = personController.savePerson(person);
        return Response.status(201).entity(person).build();
    }


    @DELETE
    @Produces( {MediaType.TEXT_PLAIN} )
    @Path("/delete/{id}")
    public Response dropPerson(@PathParam("id") String id){
        return Response.status(200).entity(checkAndDrop(id)).build();
    }

    private boolean checkAndDrop(String id) {
        if(id.matches("\\d+")) {
            return personController.dropPerson(Long.decode(id));
        }
        return false;
    }
}
