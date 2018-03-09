package Webservices;

import Persistance.*;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by nickw on 7-3-2018.
 */
public class Resource {

    // Create a set of all Classes from package: "Model"
    private static final Reflections reflections = new Reflections("Model", new SubTypesScanner(false));
    private static final Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);

    public static final AccountController accountController = new AccountController();
    public static final AddressController addressController = new AddressController();
    public static final CustomerController customerController = new CustomerController();
    public static final OrderController orderController = new OrderController();
    public static final OrderLineController orderLineController = new OrderLineController();
    public static final ProductController productController = new ProductController();

    // Supports attributes: String, double, int, boolean, List, Date
    public static JsonObjectBuilder objectToJsonObjectBuilder(Object o) {

        // Load all fields in the class (private included)
        Field[] attributes =  o.getClass().getDeclaredFields();

        //Create JsonObjectBuilder
        JsonObjectBuilder job = Json.createObjectBuilder();

        for (Field field : attributes) {
            try {

                // Allow to read attribute value
                field.setAccessible(true);

                // if attribute has no value, don't add it
                if (field.get(o) == null) {
                    continue;
                }

                // Check if Object has another Object of the Model package
                if (allClasses.contains(field.getType())) {

                    // Add the Child Object attribute to the Parent Object and all of the Child's attributes
                    job.add(field.getName(), objectToJsonObjectBuilder(field.get(o)));

                } else {

                    //TODO make this more dynamic
                    // Cast attribute to specific type

                    try {

                        if (field.getType().equals(String.class)) {
                            job.add(field.getName(), (String) field.get(o));
                        } else if ((field.getType().equals(Integer.class) || field.getType().equals(int.class))) {
                            job.add(field.getName(), (int) field.get(o));
                        } else if ((field.getType().equals(double.class))) {
                            job.add(field.getName(), (double) field.get(o));
                        } else if ((field.getType().equals(boolean.class))) {
                            job.add(field.getName(), (boolean) field.get(o));
                        } else if ((field.getType().equals(List.class))) {
                            job.add(field.getName(), objectsToJsonArrayBuilder( (List<?>) field.get(o)));
                        } else if ((field.getType().equals(Date.class))) {
                            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                            job.add(field.getName(), df.format((Date) field.get(o)));
                        }

                        else {
                            System.out.println("ATTRIBUTE TYPE: " + field.getType() + " NOT DEFINED!");
                        }

                    } catch (Exception e) {
                        System.out.println("ATTRIBUTE TYPE: " + field.getType() + " CAUSED AN ERROR!");
                    }

                    //DEBUG
                    //System.out.println("ATTRIBUTE NAME: " + field.getName() + ", ATTRIBUTE VALUE: " + f.get(o) + ", TYPE: " + field.getType());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return job;

    }

    public static JsonArrayBuilder objectsToJsonArrayBuilder(List<?> objectList){

        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Object object : objectList) {
            jab.add(objectToJsonObjectBuilder(object));
        }
        return jab;

    }


}
