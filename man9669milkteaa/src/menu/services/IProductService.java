package menu.services;

import menu.model.Product;

import javax.swing.*;
import java.util.List;

public interface IProductService {
    List<Product> findAll();
    void add(Product newProduct);
    void update(Product product);
    boolean existById(int id);
    boolean exist(int id);
    boolean existByName(String name);
    Product findByName(String name);

    void deleteById(int id);
    Product findById(int id);
    List<Product> findAllOrderByPriceASC();
    void showProduct();
    List<Product> findAllOrderByPriceDESC();

}
