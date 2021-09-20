package edu.app.util.computerUtils;

import edu.app.model.Order.Order;
import edu.app.model.computer.Computer;
import edu.app.model.user.User;
import edu.app.service.IService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "computerUtils")
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
            order.getComputers().removeIf(computer -> computer.getId() == id);
            orderIService.save(order);
        }
        List<User> users = userIService.findAll();
        for (User user : users) {
            user.getBookmarks().removeIf(computer -> computer.getId() == id);
            userIService.save(user);
        }
    }
}