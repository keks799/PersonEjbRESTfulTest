package controllers;

import models.Person;
import ru.ejb.MyEJavaBRemote;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Business_Book on 11.09.2015.
 */

@Stateful
@Path("/person")
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
    @Path("/createPerson/{person}")
    public void newPerson(@PathParam("person") Person person){
        myEJavaB.createNewPerson(person);
    }
}
