package menu.services;

import menu.model.Product;
import menu.model.User;
import menu.utils.AppUtils;
import menu.utils.CSVUtils;
import menu.utils.InstantUtils;

import java.awt.image.ImageConsumer;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ProductService implements IProductService {
    public static String PATH = "data/products.csv";
    private static ProductService instance;

    private ProductService() {

    }

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        List<String> records = CSVUtils.read(PATH);
        for (String record : records) {
            products.add(Product.parseProduct(record));
        }
        return products;
    }

    @Override
    public void add(Product newProduct) {
        newProduct.setCreatedAT(Instant.now());
        List<Product> products = findAll();
        products.add(newProduct);
        CSVUtils.write(PATH, products);
    }

    @Override
    public void update(Product newProduct) {
        List<Product> products = findAll();
        for (Product product : products) {
            if (product.getId() == newProduct.getId()) {
                String nameProduct = newProduct.getNameProduct();
                if (nameProduct != null && !nameProduct.isEmpty())
                    product.setNameProduct(nameProduct);
                Integer quantity = newProduct.getQuantity();
                if (quantity > 0)
                    product.setQuantity(quantity);
                Double price = newProduct.getPrice();
                if (price > 0)
                    product.setPrice(price);
                product.setUpdatedAt(Instant.now());
                CSVUtils.write(PATH, products);
                break;
            }
        }
    }

    @Override
    public boolean existById(int id) {
        List<Product> products = findAll();
        for (Product product : products) {
            if (product.getId() == id)
                return true;
        }
        return false;
    }

    @Override
    public boolean exist(int id) {
        return findById(id) != null;
    }

    @Override
    public boolean existByName(String name) {
        List<Product> products = findAll();
        for (Product product : products) {
            if (product.getNameProduct().equals(name))
                return true;
        }
        return false;
    }

    @Override
    public Product findByName(String name) {
        List<Product> products = findAll();
        for (Product product : products) {
            if (product.getNameProduct().equals(name))
                return product;
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        List<Product> products = findAll();

        products.removeIf(new Predicate<Product>() {
            @Override
            public boolean test(Product product) {
                return product.getId() == id;
            }
        });
        CSVUtils.write(PATH, products);
    }

    @Override
    public Product findById(int id) {
        List<Product> products = findAll();
        for (Product product : products) {
            if (product.getId() == id)
                return product;
        }
        return null;
    }

    @Override
    public List<Product> findAllOrderByPriceASC() {
        List<Product> products = new ArrayList<>(findAll());
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                double result = o1.getPrice() - o2.getPrice();
                if (result == 0)
                    return 0;
                return result > 0 ? 1 : -1;
            }
        });
        return products;
    }

    @Override
    public void showProduct() {
        System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤  DANH SÁCH CÁC LOẠI TRÀ SỮA  ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ");
        System.out.printf("%-15s %-20s %-25s %-15s %-20s %-20s\n", "ID", "TÊN TRÀ SỮA", "SỐ LƯỢNG", "GIÁ TRÀ SỮA ", "NGÀY TẠO", "NGÀY CẬP NHẬT");
        for (Product product : findAll()) {
            System.out.printf("%-15d %-20s %-25d %-15s %-20s %-20s\n",
                    product.getId(),
                    product.getNameProduct(),
                    product.getQuantity(),
                    AppUtils.doubleToVND(product.getPrice()),
                    InstantUtils.instantToString(product.getCreatedAT()),
                    product.getUpdatedAt() == null ? "" : InstantUtils.instantToString(product.getUpdatedAt())
            );
        }
        System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤  ");

    }

    @Override
    public List<Product> findAllOrderByPriceDESC() {
        List<Product> products = new ArrayList<>(findAll());
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                double result = o2.getPrice() - o1.getPrice();
                if (result == 0)
                    return 0;
                return result > 0 ? 1 : -1;
            }
        });
        return products;
    }
}
