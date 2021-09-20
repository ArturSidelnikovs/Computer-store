package edu.app.util.userUtils;

import edu.app.model.Order.Order;
import edu.app.model.Order.OrderState;
import edu.app.model.computer.Computer;
import edu.app.util.orderUtils.OrderUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component(value = "userUtils")
public class UserUtils {
    private final OrderUtils orderUtils;

    public UserUtils(OrderUtils orderUtils) {
        this.orderUtils = orderUtils;
    }


    public void editOrder(List<Order> orders, Order order) {
        orders.forEach(o -> {
            if (o.getId() == order.getId()) {
                o.setState(order.getState());
            }
        });
    }


    public Order findOrder(List<Order> orders, long id) {
        return orders.stream().filter(order -> order.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public void addOrder(List<Order> orders, Order order) {
        orders.add(order);
    }


    public boolean containsComputerInPreparatoryOrder(List<Order> orders, long id) {

        return orders.stream().filter(order2 -> OrderState.PREPARATORY.equals(order2.getState()))
                .findFirst()
                .map(order1 -> orderUtils.containsComputer(order1.getComputers(), id))
                .orElse(false);
    }


    public List<Order> getOrdersWithoutPreparatory(List<Order> orders) {
        return orders.stream().filter(order -> order.getState() != OrderState.PREPARATORY)
                .filter(order -> order.getState() != OrderState.DELETED)
                .collect(Collectors.toList());
    }


    public Order getPreparatoryOrder(List<Order> orders) {
        return orders.stream().filter(order -> order.getState().toString().equals("PREPARATORY"))
                .findFirst()
                .orElse(null);
    }

    public void deleteOrder(List<Order> orders, long id) {
        orders.removeIf(order -> order.getId() == id);
    }

    public void addToBookMarks(List<Computer> computers, Computer computer) {
        computers.add(computer);
    }

    public void deleteFromBookmarks(List<Computer> computers, long id) {
        computers.removeIf(computer -> computer.getId() == id);
    }

    public boolean containsComputerInBookmark(List<Computer> computers, long id) {
        return computers.stream().anyMatch(computer -> computer.getId() == id);
    }
}