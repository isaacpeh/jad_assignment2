package webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ToursWS")
public class ToursWS {

	// Tour categories with tours listed inside
	@GET
	@Path("/tourCategories")
	@Produces(MediaType.APPLICATION_JSON)
	public Response CtoF() {
		String jsonOutput = "{}";
		return Response.status(Response.Status.OK).entity(jsonOutput).build();
	}

	// Individual Tour Information
	@GET
	@Path("/tourInformation")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FtoC(@QueryParam("tourid") double fahrenheit) {
		String jsonOutput = "{}";
		return Response.status(Response.Status.OK).entity(jsonOutput).build();
	}

	// Update slot
	/*
	 * @POST
	 * 
	 * @Path("updateTourSlot")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response addByJSON(Member m) {
	 * // be careful, if the input JSON data is not properly setup, // then the
	 * conversion to the Member object might fail String name = m.getName(); String
	 * password = m.getPassword();
	 * 
	 * MemberManager memberManager = new MemberManager(); int rowsAffected =
	 * memberManager.create(name, password);
	 * 
	 * String jsonOutput = "{" + "\"rowsAffected\" : " + rowsAffected + "}";
	 * 
	 * return Response.status(Response.Status.OK).entity(jsonOutput).build(); }
	 */
}
