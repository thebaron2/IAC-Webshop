import Model.Address;
import Model.Order;
import Model.OrderLine;
import Model.Product;
import Persistance.AddressController;
import Persistance.OrderController;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        OrderController oc = new OrderController();
        AddressController ac = new AddressController();
        Address address = new Address("straat", 25, "stad", "potcode");

        Product apple = new Product( "appel", 12.50, "omschrijving");
        Product peanut = new Product("peanut", 19.50, "omschrijving");

        OrderLine olApple = new OrderLine(5,5.25, apple);
        OrderLine olPeanut = new OrderLine(5,5.85, apple);

        Set<OrderLine> lines = new HashSet<OrderLine>();
        lines.add(olApple);
        lines.add(olPeanut);

        Order order = new Order(address, lines);
        oc.insert(order);

        System.out.print("wat");
    }
}