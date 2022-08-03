package webservice;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParsingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dbaccess.Category;
import dbaccess.CategoryDAO;
import dbaccess.Tour;
import dbaccess.TourDAO;


@Path("/ToursWS")
public class ToursWS {

	// ALL TOURS
	@GET
	@Path("/getTours")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTours(@HeaderParam("API") String api) {
		if(api == null || !api.equals("generatedapi")) {
			return Response
					.status(Response.Status.UNAUTHORIZED)
					.entity("{\"Message\" : \"invalid api key\"}")
					.build();
		}
		
		List<Tour> allTours = new ArrayList<Tour>();
		String[] ToursJSONString = new String[0];
		String jsonOutput = "";

		try {
			TourDAO db = new TourDAO();
			allTours = db.showTours();
			ToursJSONString = new String[allTours.size()];
			
			if (allTours.size() == 0) {
				return Response
						.status(Response.Status.OK)
						.entity("{\"Message\" : \"no tours available\"}")
						.build();
			}
			
			for (int i = 0; i < allTours.size(); i++) {
				ToursJSONString[i] = allTours.get(i).getCustomJSON();
			}
			

			jsonOutput = "{" +
						"\"Tours\" : [" + 
											String.join(",", ToursJSONString) + 
										"]," +
						"\"Total Tours\" : " + allTours.size() +
					"}";
			
			return Response
					.status(Response.Status.OK)
					.entity(jsonOutput)
					.build();

		} catch (Exception ex) {
			System.out.println("Error :" + ex);
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity("{\"Error\" : " + ex + "}")
					.build();
		}
	}

	// ALL CATEGORIES
	@GET
	@Path("/getCategories")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategories(@HeaderParam("API") String api) {
		/*
		 * if(api == null || !api.equals("generatedapi")) { return Response
		 * .status(Response.Status.UNAUTHORIZED)
		 * .entity("{\"Message\" : \"invalid api key\"}") .build(); }
		 */
		
		List<Category> allCategories = new ArrayList<Category>();
		String[] CategoryJSONString = new String[0];
		String jsonOutput = "";

		try {
			CategoryDAO db = new CategoryDAO();
			allCategories = db.showCategories();
			CategoryJSONString = new String[allCategories.size()];

			if (allCategories.size() == 0) {
				return Response
						.status(Response.Status.OK)
						.entity("{\"Message\" : \"no categories available\"}")
						.build();
			}

			/*
			 * for (int i = 0; i < allCategories.size(); i++) { CategoryJSONString[i] =
			 * allCategories.get(i).getCustomJSON(); }
			 * 
			 * jsonOutput = "{" + "\"Categories\" : [" + String.join(",",
			 * CategoryJSONString) + "]," + "\"Total Categories\" : " + allCategories.size()
			 * + "}";
			 */

			return Response
					.status(Response.Status.OK)
					.entity(allCategories)
					.build();

		} catch (Exception ex) {
			System.out.println("Error :" + ex);
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity("{\"Error\" : " + ex + "}")
					.build();
		}
	}

	// TOURS BY CATEGORY
	@GET
	@Path("/getCategoryTours")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getToursByCategory(@QueryParam("catid") String cat_id, @HeaderParam("API") String api) {
		if(api == null || !api.equals("generatedapi")) {
			return Response
					.status(Response.Status.UNAUTHORIZED)
					.entity("{\"Message\" : \"invalid api key\"}")
					.build();
		}
		
		List<Tour> catTours = new ArrayList<Tour>();
		String[] ToursJSONString = new String[0];
		String cat_name = "";
		String jsonOutput = "";
		int cat_int = 0;


		try {
			cat_int = Integer.parseInt(cat_id);
			TourDAO db = new TourDAO();
			catTours = db.showToursCategory(cat_int);
			ToursJSONString = new String[catTours.size()];

			if (catTours.size() == 0) {
				return Response.status(Response.Status.OK).entity("{\"Message\" : \"no tours available\"}").build();
			}
			
			cat_name = catTours.get(1).getCategory();
			for (int i = 0; i < catTours.size(); i++) {
				ToursJSONString[i] = catTours.get(i).getCustomJSON();
			}

			jsonOutput = "{" +
					"\"Category ID\" : "+ cat_int + "," +
					"\"Category Name\" : \""+ cat_name + "\"," +
					"\"Tours\" : [" +
										String.join(",", ToursJSONString) + 
									"]," +
					"\"Total Tours\" : " + catTours.size() +
				"}";

			return Response.status(Response.Status.OK).entity(jsonOutput).build();

		} catch (NumberFormatException ex) {
			System.out.println("Error :" + ex);
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity("{\"Error\" : \"category ID must be a number and not empty\"}")
					.build();

		} catch (Exception ex) {
			System.out.println("Error :" + ex);
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity("{\"Error\" : " + ex + "}")
					.build();
		}

	}
	
	// UPDATE SLOT
	@POST
	@Path("/updateSlot")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSlot(String inputData, @HeaderParam("API") String api) {
		if(api == null || !api.equals("generatedapi")) {
			return Response
					.status(Response.Status.UNAUTHORIZED)
					.entity("{\"Message\" : \"invalid api key\"}")
					.build();
		}

		// validate bad JSON input, negative age, improper JSON format, missing data
		int rowsAffected = -1;
		String jsonOutput = "";

		try {
			JsonReader jsonReader = Json.createReader(new StringReader(inputData));

			// this statement can cause the JsonParsingException error
			JsonObject inputJSONObj = jsonReader.readObject();

			int tourid = inputJSONObj.getInt("tourid");
			int quantity = inputJSONObj.getInt("quantity");

			// --------------------------
			// data validation
			// --------------------------
			if (tourid <= 0 || quantity <= 0) {
				return Response
						.status(Response.Status.BAD_REQUEST)
						.entity("{\"Error\" : \"please input valid tour id and quantity\"}")
						.build();
			}

			// --------------------------
			// data now safe to use
			// --------------------------
			TourDAO db = new TourDAO();
			int tourQty = -1;
			tourQty = db.getSlot(tourid);

			if (tourQty != -1 && tourQty < quantity) {
				return Response
						.status(Response.Status.OK)
						.entity("{\"Message\" : \"Only " + tourQty + " slots left for tour id: " + tourid + "\"}")
						.build();
			}
			
			rowsAffected = db.updateTour(tourid, (tourQty - quantity));
			jsonOutput = "{" + 
							"\"Message\" : \"Successfully update slots\"," + 
							"\"affectedRows\" : " + rowsAffected 
						+ "}";

		} catch (NullPointerException ex) {
			// missing data
			String errorJSON = "{" + 
									"\"Error\" : \"missing data\"," + 
									"\"Details\" : \"" + ex.toString().replace("java.lang.NullPointerException", "") + "\"" 
								+ "}";
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(errorJSON)
					.build();

		} catch (JsonParsingException ex) {
			// malformed JSON
			String errorJSON = "{" + 
									"\"Error\" : \"bad input data\"" 
								+ "}";
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(errorJSON)
					.build();

		} catch (Exception ex) {
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity("{\"Error\" : \"" + ex.toString() + "\"" + "}")
					.build();
		}

		return Response
				.status(Response.Status.OK)
				.entity(jsonOutput)
				.build();
	}
	
}
