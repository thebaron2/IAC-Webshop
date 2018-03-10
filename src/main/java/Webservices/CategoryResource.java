package Webservices;

import Model.Category;

import javax.annotation.security.RolesAllowed;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by nickw on 7-3-2018.
 */


@Path("/category")
public class CategoryResource {


    @GET
//    @RolesAllowed({"user"})
    @Produces("application/json")
    public Response getAllCategories() {
        try {

            List<Category> categoryList = Resource.categoryController.findAll();
            JsonArray jsonArray = Resource.objectsToJsonArrayBuilder(categoryList).build();
            return Response.ok(jsonArray.toString()).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }


    }

    @GET
    @Path("{id}")
//    @RolesAllowed({"user"})
    @Produces("application/json")
    public Response getAccountById(@PathParam("id") int id) {
        try {

            Category category = Resource.categoryController.findById(id);
            JsonObject jsonObject = Resource.objectToJsonObjectBuilder(category).build();
            return Response.ok(jsonObject.toString()).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }


    }


}