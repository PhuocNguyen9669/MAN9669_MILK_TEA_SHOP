package menu.view;

import menu.Main;
import menu.utils.AppUtils;

import javax.swing.plaf.LabelUI;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductViewLaucher {
    public static ProductView productView = new ProductView();

    public static void launch() {
        int option = -1;
        do {
            Scanner scanner = new Scanner(System.in);
            ProductView productView = new ProductView();
            menuManager();

            try {
                System.out.println("\n Chon chức năng: ");
                System.out.print(" ⭆ ");
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        productView.addProduct();
                        break;
                    case 2:
                        productView.updateProduct();
                        break;
                    case 3:
                        productView.deleteProduct();
                        break;
                    case 4:
                        productView.showProduct(InputOption.SHOW);
                        break;
                    case 5:
                        productView.sortByPriceOrderByASC();
                        break;
                    case 6:
                        AppUtils.exit();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        launch();
                }
            } catch (InputMismatchException e) {
                System.out.println("Nhập sai! Vui lòng nhập lại.");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        } while (option != 0);
    }

    public static void menuManager() {
        System.out.println("❤❤❤❤❤❤❤QUẢN LÝ TRÀ SỮA MAN9669❤❤❤❤❤❤❤❤");
        System.out.println("❤                                            ❤");
        System.out.println("❤    1. Thêm trà sữa                         ❤");
        System.out.println("❤    2. Sửa thông tin trà sữa                ❤");
        System.out.println("❤    3. Xóa trà sữa                          ❤");
        System.out.println("❤    4. Hiển thị tất cả các loại trà sữa     ❤");
        System.out.println("❤    5. Hiển thị tất cả các trà sữa theo giá  ❤");
        System.out.println("❤    6. Thoát || 0. để quay lại menu chính   ❤");
        System.out.println("❤                                            ❤");
        System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤  ❤");

    }
}