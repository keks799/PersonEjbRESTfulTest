package controllers;

import ru.ejb.SessionBean;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by Business_Book on 11.09.2015.
 */

@Path("/person")
//@Stateless
public class PersonController {
    @EJB
    private SessionBean sessionBean;

    @Path("/all")
    @GET
    @Produces("text/plain")
    public String getPersons(){
        System.out.println("gotit!!");
        return sessionBean.firstMethod();
    }
}
