package sample1.rest;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import sample1.model.BarGrow;
import sample1.model.Cerveza;
import sample1.service.StockService;


@Path("/sample")
@Produces({ MediaType.APPLICATION_JSON+ ";charset=utf-8" })
//@Produces("application/json;charset=utf-8")
public class SampleRest {

	private final static Logger LOGGER = Logger.getLogger(SampleRest.class.getName());

	@Inject
	StockService stockService ;
	
    @GET
    @Path("/ping/{param}")
    public Response getCheck(@PathParam("param") String id) {
    	LOGGER.info("ping GET param:"+id);
    	return Response.status(Status.ACCEPTED).build();
    }
	
    /**
     * da todos los tipos de cervezas
     * @return
     */
    @GET
    @Path("/cervezas")
    public Response getCervezas() {
    	LOGGER.info("getExistentes");
    	
    	Gson gsonInstance = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
    	String jsonResultante = gsonInstance.toJson(stockService.getCervezas());
    	return Response.ok(jsonResultante, MediaType.APPLICATION_JSON)
    			.header("Content-Type", "application/json;charset=UTF-8")
    			.status(Status.ACCEPTED).build();
    }
    
    /**
     * da las cervezas disponibles segun si es bar o growler
     * @param idSesion de la sesion del cliente web.
     * @param bargrow bar o grow , para decir si esta en un bar o en un growler
     * @return
     */
    @GET
    @Path("/cervezas/{idSesion}/{bargrow}")
    public Response getCervezasDisponibilidad(@PathParam("idSesion") String idSesion, @PathParam("bargrow") String bargrow) {
    	LOGGER.info("getCervezasDisponibilidad");
    	
    	Gson gsonInstance = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
    	
    	BarGrow selected = BarGrow.getBarGrow(bargrow);
    	
    	String jsonResultante = gsonInstance.toJson(stockService.getCervezasDisponibilidad(selected));
    	return Response.ok(jsonResultante, MediaType.APPLICATION_JSON)
    			.header("Content-Type", "application/json;charset=UTF-8")
    			.status(Status.ACCEPTED).build();
    }
    
    /**
     * para devolver todos los lugares que hay disponibles
     * @return
     */
    @GET
    @Path("/lugares")
    public Response getLugares() {
    	LOGGER.info("getLugares");
    	
    	Gson gsonInstance = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();

    	String jsonResultante = gsonInstance.toJson(stockService.getLugares());
    	return Response
    			.ok(jsonResultante, MediaType.APPLICATION_JSON)
    			.header("Content-Type", "application/json;charset=UTF-8")
    			.status(Status.ACCEPTED).build();
    }
    
//	que bares tienen growler para la ubicacion: growl + lugar a partir del: ubicacion.
//	getLugaresGrowlerByUbicacion(x,y){
//		
//	}
    @GET
    @Path("/lugaresgrowl/{x}/{y}/{tipoCervezas}")
    public Response getLugaresGrowlerByUbicacion(@PathParam("x") Double x, @PathParam("y") Double y,@PathParam("tipoCervezas") String tipoCervezas) {
    	LOGGER.info("getLugaresGrowlerByUbicacion");
    	
    	Gson gsonInstance = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
    	
    	// dame la lista de cervezas segun si estan en la query.
    	// FIXME:: desventaja si no esta en la query no va a devolver 
//    	> lo cual seria obvio que tiene que devolver porque las cervezas estan siempre en la lista !
//    	> sino devuelve es potencial otro origen que nos ea lo que esperamos
    	List<Cerveza> tipoCervezasParsed = stockService.getCervezas().stream()
    			.filter(beer-> Arrays.asList(tipoCervezas.split("-")).contains(beer.getCodigo())).collect(Collectors.toList());

    	String jsonResultante = gsonInstance.toJson(stockService.getLugaresGrowlerByUbicacion(x,y, tipoCervezasParsed));
    	return Response.ok(jsonResultante, MediaType.APPLICATION_JSON)
    			.header("Content-Type", "application/json;charset=UTF-8")
    			.status(Status.ACCEPTED).build();
    }
	
	
//	que bares tienen las cervezas para la ubicacion en el momento.
    @GET
    @Path("/lugares/{x}/{y}/{tipoCervezas}")
    public Response getLugaresByUbicacion(@PathParam("x") Double x, @PathParam("y") Double y,@PathParam("tipoCervezas") String tipoCervezas) {
    	LOGGER.info("getLugares");
    	
    	Gson gsonInstance = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
    	
    	// dame la lista de cervezas segun si estan en la query.
    	// FIXME:: desventaja si no esta en la query no va a devolver 
//    	> lo cual seria obvio que tiene que devolver porque las cervezas estan siempre en la lista !
//    	> sino devuelve es potencial otro origen que nos ea lo que esperamos
    	List<Cerveza> tipoCervezasParsed = stockService.getCervezas().stream()
    			.filter(beer-> Arrays.asList(tipoCervezas.split("-")).contains(beer.getCodigo())).collect(Collectors.toList());

    	String jsonResultante = gsonInstance.toJson(stockService.getLugaresGrowlerByUbicacion(x,y, tipoCervezasParsed));
    	return Response.ok(jsonResultante, MediaType.APPLICATION_JSON)
    			.header("Content-Type", "application/json;charset=UTF-8")
    			.status(Status.ACCEPTED).build();
    }    
	
}
