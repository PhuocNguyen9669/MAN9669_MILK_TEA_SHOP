package menu.view;


import menu.model.Product;
import menu.services.IProductService;
import menu.services.ProductService;
import menu.utils.AppUtils;
import menu.utils.InstantUtils;

import java.time.Instant;
import java.util.List;
import java.util.Scanner;

public class ProductView {
    private final IProductService productService;
    private static Scanner scanner = new Scanner(System.in);

    public ProductView() {
        productService = ProductService.getInstance();
    }

    public String inputNameProduct(InputOption option) {
        String nameProduct = "";
        switch (option) {
            case ADD:
                System.out.println("Nhập tên trà sữa: ");
                break;
            case UPDATE:
                System.out.println("Nhập tên trà sữa bạn muốn sửa: ");
                break;
        }
//        System.out.print(" ⭆ ");
        do {
            nameProduct = AppUtils.retryString("Tên sản phẩm");
        } while (nameProduct.isEmpty());
        return nameProduct;
    }

    public void addProduct() {
        do {
            long id = System.currentTimeMillis() / 1000;
            String nameProduct = inputNameProduct(InputOption.ADD);
            int quantity = inputQuantity(InputOption.ADD);
            double price = inputPrice(InputOption.ADD);
            Product product = new Product(id, nameProduct, quantity, price);
            productService.add(product);
            System.out.println("Bạn đã thêm sản phẩm thành công!\n");
            showProduct(InputOption.ADD);
        } while (AppUtils.isRetry(InputOption.ADD));
    }

    public void updateProduct() {
        boolean isRetry;
        do {
            showProduct(InputOption.UPDATE);
            long id = inputId(InputOption.UPDATE);
            System.out.println("❤ ❤ ❤ ❤ ❤ UPDATE MIlKTEA ❤ ❤ ❤ ❤ ❤");
            System.out.println("❤       1. Sửa tên trà sữa            ❤");
            System.out.println("❤       2. Sửa số lượng trà sữa       ❤");
            System.out.println("❤       3. Sửa giá trà sữa            ❤");
            System.out.println("❤       4. Quay lại MENU              ❤");
            System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤❤ ❤");
            System.out.println("Chọn chức năng :");
            int option = AppUtils.retryChoose(1, 4);
            Product newProduct = new Product();
            newProduct.setId(id);
            switch (option) {
                case 1:
                    String nameProduct = inputNameProduct(InputOption.UPDATE);
                    newProduct.setNameProduct(nameProduct);
                    productService.update(newProduct);
                    System.out.println("Tên bánh đã cập nhật thành công!");
                    showProduct(InputOption.UPDATE);
                    break;
                case 2:
                    int quantity = inputQuantity(InputOption.UPDATE);
                    newProduct.setQuantity(quantity);
                    productService.update(newProduct);
                    System.out.println("Số lượng trà sữa đã được cập nhật thành công!");
                    showProduct(InputOption.UPDATE);
                    break;
                case 3:
                    double price = inputPrice(InputOption.UPDATE);
                    newProduct.setPrice(price);
                    productService.update(newProduct);
                    System.out.println("Bạn đã sửa giá trà sửa thành công!");
                    showProduct(InputOption.UPDATE);
                    break;
            }
            isRetry = option != 4 && AppUtils.isRetry(InputOption.UPDATE);
        } while (isRetry);
    }

    public void showProduct(InputOption inputOption) {
        System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤  DANH SÁCH CÁC LOẠI TRÀ SỮA  ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ");
        System.out.printf("%-15s %-20s %-25s %-15s %-20s %-20s\n", "ID", "TÊN TRÀ SỮA", "SỐ LƯỢNG", "GIÁ TRÀ SỮA ", "NGÀY TẠO", "NGÀY CẬP NHẬT");
        for (Product product : productService.findAll()) {
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
        if (inputOption == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);
    }

    public void deleteProduct() {
        showProduct(InputOption.DELETE);
        int id;
        while (!productService.exist(id = inputId(InputOption.DELETE))) {
            System.out.println("Không tìm thấy sản phẩm cần xóa!");
            System.out.println("Nhấn 'y' để nhập lại \t|\t 'q' để quay lại \t|\t 't' để thoát chương trình");
            System.out.print(" ⭆ ");
            String option = scanner.nextLine();
            switch (option) {
                case "y":
                    break;
                case "q":
                    return;
                case "t":
                    AppUtils.exit();
                    break;
                default:
                    System.out.println("Chọn chức năng không đúng! Vui lòng nhập lại.");
                    break;
            }
        }
        System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤DELETE MILKTEA ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤");
        System.out.println("❤             1. Nhấn 1 để xóa                            ❤");
        System.out.println("❤             2. Nhấn 2 để quay lại                       ❤");
        System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤❤ ❤ ❤ ❤");
        int option = AppUtils.retryChoose(1, 2);
        if (option == 1) {
            productService.deleteById(id);
            System.out.println("Đã xóa sản phẩm thành công!");
            showProduct(InputOption.DELETE);
            AppUtils.isRetry(InputOption.DELETE);
        }
    }

    public int inputId(InputOption option) {
        int id;
        switch (option) {
            case ADD:
                System.out.println("Nhập Id: ");
                break;
            case UPDATE:
                System.out.println("Nhập Id bạn muốn sửa: ");
                break;
            case DELETE:
                System.out.println("Nhập Id bạn muốn xóa: ");
                break;
        }
        boolean isRetry = false;
        do {
            id = AppUtils.retryParseInt();
            boolean exist = productService.existById(id);
            switch (option) {
                case ADD:
                    if (exist) {
                        System.out.println("Id này đã tồn tại! Vui lòng nhập lại.");
                    }
                    isRetry = exist;
                    break;
                case UPDATE:
                    if (!exist) {
                        System.out.println("Không tìm thấy Id! Vui lòng nhập lại.");
                    }
                    isRetry = !exist;
                    break;
            }
        } while (isRetry);
        return id;
    }

    private int inputQuantity(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập số lượng: ");
                break;
            case UPDATE:
                System.out.println("Nhập số lượng muốn sửa: ");
                break;
        }
        int quantity;
        do {
            quantity = AppUtils.retryParseInt();
            if (quantity <= 0)
                System.out.println("(Số lượng phải lớn hơn 0)");
        } while (quantity <= 0);
        return quantity;
    }

    private double inputPrice(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập giá sản phẩm: ");
                break;
            case UPDATE:
                System.out.println("Nhập giá sản phẩm muốn sửa: ");
                break;
        }
        double price;
        do {
            price = AppUtils.retryParseDouble();
            if (price <= 0)
                System.out.println("Giá phải lớn hơn 0");
        } while (price <= 0);
        return price;
    }

    public void showProductsSort(InputOption inputOption, List<Product> products) {
        System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤");
        System.out.printf("%-15s %-20s %-20s %-15s %-20s %-20s\n", "ID", "TÊN TRÀ SỮA", "SỐ LƯỢNG", "GIÁ BÁN", "NGÀY TẠO", "NGÀY CẬP NHẬT");
        for (Product product : products) {
            System.out.printf("%-15d %-20s %-20d %-15s %-20s %-20s\n",
                    product.getId(),
                    product.getNameProduct(),
                    product.getQuantity(),
                    AppUtils.doubleToVND(product.getPrice()),
                    InstantUtils.instantToString(product.getCreatedAT()),
                    product.getUpdatedAt() == null ? "" : InstantUtils.instantToString(product.getUpdatedAt())
            );
        }
        System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤");
        if (inputOption == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);

    }

    public void sortByPriceOrderByASC() {
        showProductsSort(InputOption.SHOW, productService.findAllOrderByPriceASC());
    }

    public void sortByPriceOrderByDESC() {
        showProductsSort(InputOption.SHOW, productService.findAllOrderByPriceDESC());
    }
}
