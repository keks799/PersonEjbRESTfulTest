package controllers;

import models.Person;
import ru.ejb.MyEJavaBRemote;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Business_Book on 11.09.2015.
 */

@Path("/person")
@Stateful
@SessionScoped
public class PersonRestResource implements Serializable {
    @EJB
    private MyEJavaBRemote myEJavaB;

    @GET
    public String getRoot(){
        System.out.println("this is Root!!");
        return "root";
    }

    @GET
    @Path( "/all" )
    @Produces( {MediaType.APPLICATION_JSON} )
    public List<Person> listPersons() {
        return myEJavaB.listOfPersons();
    }

    @POST
    @Produces( {MediaType.APPLICATION_JSON} )
    @Path("/new")
    public Response newPerson(
            @FormParam("firstName") String firstname,
            @FormParam("lastName") String lastname,
            @FormParam("middleName") String middlename,
            @FormParam("birthDate") String birthdateString
    ){
        Date birthdate = getDate(birthdateString);
        Person person = new Person(firstname, lastname, middlename, birthdate);
        Person result = myEJavaB.savePerson(person);
        return Response.status(201).entity(Collections.singletonList(result)).build();
    }

    private Date getDate(@FormParam("birthDate") String birthdateString) {
        Date birthdate = null;
        try {
            birthdate = new SimpleDateFormat("dd.MM.yyyy").parse(birthdateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return birthdate;
    }

    @POST
    @Produces( MediaType.APPLICATION_JSON)
    @Path("/edit")
    public Response editPerson(
            @FormParam("id") Long id,
            @FormParam("firstName") String firstname,
            @FormParam("lastName") String lastname,
            @FormParam("middleName") String middlename,
            @FormParam("birthDate") String birthdateString
    ) {
        Person person = new Person(id, firstname, lastname, middlename, getDate(birthdateString));
        person = myEJavaB.savePerson(person);
        return Response.status(201).entity(person).build();
    }


    @POST // TODO @DELETE ?
    @Produces( {MediaType.TEXT_PLAIN} )
    @Path("/delete/{id}")
    public Response dropPerson(@PathParam("id") String id){
        return Response.status(201).entity(checkAndDrop(id)).build(); //todo change status number to deleted
    }

    private boolean checkAndDrop(String id) {
        if(id.matches("\\d+")) {
            return myEJavaB.dropPerson(Long.decode(id));
        }
        return false;
    }
}
