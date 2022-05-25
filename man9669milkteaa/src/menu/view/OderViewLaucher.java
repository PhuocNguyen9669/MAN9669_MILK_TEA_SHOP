package menu.view;

import menu.utils.AppUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OderViewLaucher {
    public static void launch() {
        do {
            menuManager();
            try {
                Scanner scanner = new Scanner(System.in);
                OrderView orderView = new OrderView();
                System.out.println("\n Chọn chức năng: ");
                System.out.print("⭆ ");
                int choose = Integer.parseInt(scanner.nextLine());

                switch (choose) {
                    case 1:
                        orderView.addOder();
                        break;
                    case 2:
                        orderView.showAllOrder();
                        break;
                    case 3:
                        MainMenu.menuOption();
                        break;
                    case 4:
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Chọn sai chức năng! Vui lòng chọn lại.");
                        launch();
                }
            }catch (Exception e){
                System.out.println("Nhập sai vui lòng nhập lại");
            }
        } while (true);
    }

    public static void menuManager() {
        System.out.println("❤❤❤❤❤❤❤❤MENU MILKTEA MANAGER❤❤❤❤❤❤❤❤❤");
        System.out.println("❤                                            ❤");
        System.out.println("❤     1. Thêm đơn hàng                       ❤");
        System.out.println("❤     2. Hiển thị danh sách đơn hàng         ❤");
        System.out.println("❤     3. Quay lại Menu chính                 ❤");
        System.out.println("❤     4. Để thoát                            ❤");
        System.out.println("❤                                            ❤");
        System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤  ❤");

    }
}
