package menu.model;

import java.time.Instant;

public class Product {
    private long id;
    private String nameProduct;
    private int quantity;
    private double price;
    private Instant createdAT;
    private Instant updatedAt;

    public Product() {
    }

    public static Product parseProduct(String record) {
        String[] fields = record.split(",");
        long id = Long.parseLong(fields[0]);
        String nameProduct = fields[1];
        int quantity = Integer.parseInt(fields[2]);
        double price = Double.parseDouble(fields[3]);
        Instant createdAT = Instant.parse(fields[4]);
        String temp = fields[5];
        Instant updatedAt = null;
        if (temp != null && !temp.equals("null"))
            updatedAt = Instant.parse(temp);
            return new Product(id, nameProduct, quantity, price, createdAT, updatedAt);
    }

    public Product(long id, String nameProduct, int quantity, double price) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(long id, String nameProduct, int quantity, double price, Instant createdAT, Instant updatedAt) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.price = price;
        this.createdAT = createdAT;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Instant getCreatedAT() {
        return createdAT;
    }

    public void setCreatedAT(Instant createdAT) {
        this.createdAT = createdAT;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s",
                id,
                nameProduct,
                quantity,
                price,
                createdAT,
                updatedAt);
    }
}
