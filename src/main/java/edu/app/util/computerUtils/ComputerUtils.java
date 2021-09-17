package edu.app.util.computerUtils;

import edu.app.model.Order.Order;
import edu.app.model.phone.Computer;
import edu.app.model.user.User;
import edu.app.service.IService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "phoneUtils")
public class ComputerUtils {

    private final IService<Order> orderIService;
    private final IService<User> userIService;

    public ComputerUtils(IService<Order> orderIService, IService<User> userIService) {
        this.orderIService = orderIService;
        this.userIService = userIService;
    }

    public void addImage(Computer computer, String imgLink) {
        computer.getImages().add(imgLink);
    }

    public void findUsersAndOrders(long id) {
        List<Order> orders = orderIService.findAll();
        for (Order order : orders) {
            order.getComputers().removeIf(phone -> phone.getId() == id);
            orderIService.save(order);
        }
        List<User> users = userIService.findAll();
        for (User user : users) {
            user.getBookmarks().removeIf(phone -> phone.getId() == id);
            userIService.save(user);
        }
    }
}