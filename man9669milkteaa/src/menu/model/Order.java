package menu.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private String fullName;
    private String mobile;
    private double grandTotal;
    private String address;
    private Instant createdAt;
//    private Instant updatedAt;
    List<Product> orderItems = new ArrayList<>();

    public Order(){

    }

    public Order(Long id, String fullName, String mobile, String address) {
        this.id = id;
        this.fullName = fullName;
        this.mobile = mobile;
        this.address = address;
    }

    public static Order parse(String record) {
        Order order = new Order();
        String[] field = record.split(",");
        order.id = Long.parseLong(field[0]);
        order.fullName = field[1];
        order.mobile = field[2];
        order.address = field[3];
        String temp = field[4];
        order.createdAt = Instant.parse(temp);
//        order.updatedAt = null;
//        if (temp != null && !temp.equals("null"))
//            order.updatedAt = Instant.parse(temp);
        return order;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }


    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<Product> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Product> orderItems) {
        this.orderItems = orderItems;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

//    public Instant getUpdatedAt() {
//        return updatedAt;
//    }

//    public void setUpdatedAt(Instant updatedAt) {
//        this.updatedAt = updatedAt;
//    }

//    public List<Product> getOrderItems() {
//        return orderItems;
//    }
//
//    public void setOrderItems(List<Product> orderItems) {
//        this.orderItems = orderItems;
//    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s",
                id,
                fullName,
                mobile,
                address,
                createdAt
        );
    }
}
