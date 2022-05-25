package menu.model;

import java.time.Instant;

public class OrderItem {
    private Long id;
    private double price;
    private int quantity;
    private long orderId;
    private int productId;
    private String nameProduct;

    public OrderItem(Long id, double price, int quantity, long orderId, int productId, String nameProduct, double total) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
        this.nameProduct = nameProduct;
//        this.total = total;

    }

    public OrderItem() {
    }


    public static OrderItem parse(String record) {
        OrderItem orderItem = new OrderItem();
        String[] fields = record.split(",");
        orderItem.id = Long.parseLong(fields[0]);
        orderItem.price = Double.parseDouble(fields[1]);
        orderItem.quantity = Integer.parseInt(fields[2]);
        orderItem.orderId = Long.parseLong(fields[3]);
        orderItem.productId = Integer.parseInt(fields[4]);
        orderItem.nameProduct = fields[5];
        return orderItem;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return nameProduct;
    }

    public void setProductName(String productName) {
        this.nameProduct = productName;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s",
                id,
                price,
                quantity,
                orderId,
                productId,
                nameProduct);
    }
}
