package webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
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
	public Response getAllTours() {
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
						.entity("{\"Message\" : no tours available}")
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
	public Response getCategories() {
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
						.entity("{\"Message\" : no categories available}")
						.build();
			}

			for (int i = 0; i < allCategories.size(); i++) {
				CategoryJSONString[i] = allCategories.get(i).getCustomJSON();
			}

			jsonOutput = "{" +
					"\"Categories\" : [" + 
										String.join(",", CategoryJSONString) + 
									"]," +
					"\"Total Categories\" : " + allCategories.size() +
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

	// TOURS BY CATEGORY
	@GET
	@Path("/getCategoryTours")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getToursByCategory(@QueryParam("catid") String cat_id) {
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
				return Response.status(Response.Status.OK).entity("{\"Message\" : no tours available}").build();
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
	
	/*
	 * // Update slot
	 * 
	 * @POST
	 * 
	 * @Path("/updateSlot")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public Response createUser(String
	 * inputData) {
	 * 
	 * // validate bad json input, negative age, improper json format, missing data
	 * 
	 * int rowsAffected = -1; String jsonOutput = "";
	 * 
	 * try { JsonReader jsonReader = Json.createReader(new StringReader(inputData));
	 * 
	 * // this statement can cause the JsonParsingException error JsonObject
	 * inputJSONObj = jsonReader.readObject();
	 * 
	 * String uId = inputJSONObj.getString("userid"); String uGender =
	 * inputJSONObj.getString("gender"); int uAge = inputJSONObj.getInt("age");
	 * 
	 * // -------------------------- // data validation //
	 * -------------------------- uGender = uGender.toLowerCase();
	 * 
	 * if (!uGender.trim().equals("male") && !uGender.trim().equals("female")) {
	 * return Response .status(Response.Status.BAD_REQUEST)
	 * .entity("{\"error\" : \"bad gender data\"}") .build(); }
	 * 
	 * if (uAge < 0 || uAge > 120) { return Response
	 * .status(Response.Status.BAD_REQUEST)
	 * .entity("{\"error\" : \"bad age data\"}") .build(); }
	 * 
	 * // -------------------------- // data now safe to use //
	 * -------------------------- UserDAO db = new UserDAO(); rowsAffected =
	 * db.insertUser(uId, uAge, uGender); jsonOutput = "{" + "\"affectedRows\" : " +
	 * rowsAffected + "}";
	 * 
	 * System.out.println("...done create user..in UserService.");
	 * 
	 * } catch (NullPointerException ex) { // deal with missing data String
	 * someError = "{" + "\"error\" : \"bad input data\"" + "\"details\" : \"" +
	 * ex.toString().replace("java.lang.NullPointerException", "") + "\"" + "}";
	 * return Response .status(Response.Status.BAD_REQUEST) .entity(someError)
	 * .build();
	 * 
	 * } catch (JsonParsingException ex) { // deal with malformed json input data,
	 * missing comma, missing double quote String someError = "{" +
	 * "\"error\" : \"bad input data\"" + "}"; return Response
	 * .status(Response.Status.BAD_REQUEST) .entity(someError) .build();
	 * 
	 * } catch (Exception ex) { return Response.status(Response.Status.BAD_REQUEST)
	 * .entity("{\"error\" : \"" + ex.toString() + "\"" + "}") .build(); }
	 * 
	 * return Response .status(Response.Status.OK) .entity(jsonOutput) .build(); }
	 */
	
	
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
