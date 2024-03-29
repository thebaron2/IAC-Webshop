package Webservices;

import Model.OrderLine;

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


@Path("/orderline")
public class OrderLineResource {


    @GET
//    @RolesAllowed({"customer", "admin"})
    @Produces("application/json")
    public Response getAllOrderLines() {
        try {

            List<OrderLine> orderLineList = Resource.ORDER_LINE_CONTROLLER.findAll();
            JsonArray jsonArray = Resource.objectsToJsonArrayBuilder(orderLineList).build();
            return Response.ok(jsonArray.toString()).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }


    }

    @GET
    @Path("{id}")
//    @RolesAllowed({"customer", "admin"})
    @Produces("application/json")
    public Response getOrderLineById(@PathParam("id") int id) {
        try {

            OrderLine orderLine = Resource.ORDER_LINE_CONTROLLER.findById(id);
            JsonObject jsonObject = Resource.objectToJsonObjectBuilder(orderLine).build();
            return Response.ok(jsonObject.toString()).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }


    }


}