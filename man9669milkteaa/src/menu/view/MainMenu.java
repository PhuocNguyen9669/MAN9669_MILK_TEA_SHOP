package menu.view;

import menu.utils.AppUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
    public static void launch() {
           AdminView adminView = new AdminView();
           adminView.adminLogin();
        menuOption();
    }

    public static void menuOption() {
        do {
            mainMenu();
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print(" ⭆ ");
                int number = Integer.parseInt(scanner.nextLine());
                switch (number){
                    case 1:
                        UserViewLaucher.lauch();
                        break;
                    case 2:
                        ProductViewLaucher.launch();
                        break;
                    case 3:
                        OderViewLaucher.launch();
                        break;
                    case 0:
                        AppUtils.exit();
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Nhập sai! Vui lòng nhập lại.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public static void mainMenu() {
        System.out.println("❤❤❤CHÀO MỪNG BẠN ĐẾN VỚI CỦA HÀNG TRÀ SỮA MAN9669❤❤❤");
        System.out.println("❤                                                     ❤");
        System.out.println("❤             1. Quản lý người dùng                   ❤");
        System.out.println("❤             2. Quản lý các loại trà sữa             ❤");
        System.out.println("❤             3. Quản lý hóa đơn                      ❤");
        System.out.println("❤             0. Thoát                                ❤");
        System.out.println("❤                                                     ❤");
        System.out.println("❤❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤❤❤");
    }

}
