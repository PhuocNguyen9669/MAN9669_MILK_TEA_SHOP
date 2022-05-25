package menu.services;

import menu.model.OrderItem;

import java.util.List;

public interface IOderItemService {
    List<OrderItem> findAll();

    void add(OrderItem newOrderItem);

    void update(OrderItem newOrderItem);

    OrderItem getOrderItemById(int id);
}
