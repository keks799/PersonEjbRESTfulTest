package controllers;

import models.Person;
import ru.ejb.MyEJavaBRemote;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

/**
 * Created by Business_Book on 11.09.2015.
 */

@Stateful
@Path("/person")
public class PersonRestResource {
    @EJB
    private MyEJavaBRemote myEJavaB;

    @Context
    private HttpHeaders headers;

    @GET
    @Path("/")
    @Produces("text/plain")
    public String getRoot(){
        System.out.println("this is Root!!");
        return "root";
    }

    @GET
    @Path( "/all" )
    @Produces( MediaType.APPLICATION_JSON )
    public List<Person> listPersons() {
        /// test
        Person person = new Person();
        person.setId(new Long(0));
        person.setBirthday(new Date());
        person.setFirstname("ddd");
        person.setLastname("ssss");
        person.setMiddlename("rrrr");
        myEJavaB.listOfPersons().add(person);
        // test
        return myEJavaB.listOfPersons();
    }
}
