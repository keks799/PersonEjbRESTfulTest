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
            @FormParam("firstname") String firstname,
            @FormParam("lastname") String lastname,
            @FormParam("middlename") String middlename,
            @FormParam("birthdate") String birthdateString
    ){
        Date birthdate = null;
        try {
            birthdate = new SimpleDateFormat("dd.MM.yyyy").parse(birthdateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Person person = new Person(firstname, lastname, middlename, birthdate);
        Person result = myEJavaB.createNewPerson(person);
        return Response.status(201).entity(result).build();
    }
}
