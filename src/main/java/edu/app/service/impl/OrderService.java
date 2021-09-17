package edu.app.service.impl;


import edu.app.model.Order.Order;
import edu.app.repository.orderRepository.OrderRepository;
import edu.app.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("orderService")
@Transactional
public class OrderService implements IService<Order> {

    private final OrderRepository orderRepository;
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Order findById(long id) {
        Order order = orderRepository.findById(id).orElse(null);
        logger.info("Order {} was successfully found", order.getId());
        return order;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        List<Order> orders = orderRepository.findAll();
        logger.info("All orders in db was successfully found");
        return orders;
    }

    @Override
    public Order save(Order order) {
        Order orderToBd = orderRepository.save(order);
        logger.info("Order {} was successfully added to db", orderToBd.getId());

        return orderToBd;
    }

    @Override
    public void deleteById(long id) {
        orderRepository.deleteById(id);
        logger.info("Order with id {} was successfully deleted form db", id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Order> findAllWithPagination(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        logger.info("All orders in db was successfully found");
        return orders;
    }
}