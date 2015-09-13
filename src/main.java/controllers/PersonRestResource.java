package controllers;

import ru.ejb.SessionBean;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

/**
 * Created by Business_Book on 11.09.2015.
 */

@Path("/person")
public class PersonRestResource {
    @EJB
    private SessionBean sessionBean;

    @Context
    private HttpHeaders headers;

    @GET
    @Produces( {MediaType.TEXT_PLAIN} )
    public Response root( @Context UriInfo uriInfo ) {

        System.out.println( "URI INFO = " + uriInfo.getRequestUri() );
        String uri = uriInfo.getRequestUri().toString();
        uri = ( uri.matches( ".*/$" ) ? uri.substring( 0, uri.length() - 1 ) : uri );

        return Response.status( Response.Status.SEE_OTHER ).header( HttpHeaders.LOCATION, uri + "/status" )
                .build();
    }

    @GET
    @Path("/all")
    @Produces("text/plain")
    public String getPersons(){
        System.out.println("gotit!!");
        return sessionBean.firstMethod();
    }
}
