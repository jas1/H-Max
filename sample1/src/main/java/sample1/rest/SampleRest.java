package sample1.rest;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


@Path("/sample")
@Produces({ MediaType.APPLICATION_JSON })
public class SampleRest {

	private final static Logger LOGGER = Logger.getLogger(SampleRest.class.getName());

	
    @GET
    @Path("/ping/{param}")
    public Response getCheck(@PathParam("param") String id) {
    	LOGGER.info("ping GET param:"+id);
    	return Response.status(Status.ACCEPTED).build();
    }
	
	
}
