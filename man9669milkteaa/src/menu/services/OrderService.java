package menu.services;

import com.sun.org.apache.xpath.internal.operations.Or;
import menu.model.Order;
import menu.utils.CSVUtils;
import menu.view.OrderView;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOderService {
    public final static String PATH = "data/orders.csv";
    private static OrderService instance;

    private OrderService(){

    }
    //Singleton Design Pattern
    public static OrderService getInstance(){
        if (instance == null)
            instance = new OrderService();
        return instance;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        List<String> records = CSVUtils.read(PATH);

        for (String record : records) {
            orders.add(Order.parse(record));
        }
        return orders;
    }

    @Override
    public void add(Order newOrder) {
        List<Order> orders = findAll();
        newOrder.setCreatedAt(Instant.now());
        orders.add(newOrder);
        CSVUtils.write(PATH, orders);
    }

    @Override
    public void update(OrderView newOrder) {
        List<Order> orders = findAll();
        CSVUtils.write(PATH,orders);
    }

    @Override
    public Order findById(int id) {
        List<Order> orders = findAll();
        for (Order order : orders) {
            if (order.getId() == id)
                return order;
        }
        return null;
    }

    @Override
    public boolean existById(long id) {
        List<Order> products = findAll();
        for (Order product : products) {
            if (product.getId() == id)
                return true;
        }
        return false;
    }

    @Override
    public List<Order> findByUserId(long id) {
        List<Order> newOrders = findAll();
        for (Order order : findAll()){
            if(order.getId() == id){
                newOrders.add(order);
            }
        }
        return newOrders;
    }


//    @Override
//    public List<OrderView> findAllOrderByPriceASC() {
//        List<Order> orders = new ArrayList<>(findAll());
//        orders.sort(new Comparator<Order>() {
//            @Override
//            public int compare(Order o1, Order o2) {
//                double result = o1.getGrandTotal() - o2.getGrandTotal();
//                if (result == 0)
//                    return 0;
//                return result > 0 ? 1 : -1;
//            }
//        });
//        return orders;
//    }


}
