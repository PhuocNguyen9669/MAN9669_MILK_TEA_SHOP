package menu.services;

import menu.model.Order;
import menu.view.OrderView;

import java.util.List;

public interface IOderService {
    List<Order> findAll();

    void add(Order newOrder);

    void update(OrderView newOrder);

    Order findById(int id);

    boolean existById(long id);

    List<Order> findByUserId(long id);


//    List<OrderView> findAllOrderByPriceASC();

//    List<OrderView> findAllOrderByPriceDESC();


}
