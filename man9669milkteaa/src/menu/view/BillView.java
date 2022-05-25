package menu.view;

import java.util.Scanner;

public class BillView {
    public static Scanner scanner = new Scanner(System.in);
    public static void launch() {
        show();
        int option;
        do {
            System.out.println("\n select function ");
            System.out.print(" ➥ ");
            option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                    ProductViewLaucher.launch();
                    break;
                case 2:
                    ProductViewLaucher.launch();
                    break;
                case 3:
                    ProductViewLaucher.launch();
                    break;
                case 8:
                    System.exit(8);
                    break;
//                case 0:
//                    break;
                default:
                    System.out.println("You have selected the wrong function, please choose again !");
            }

        } while (option != 0);
    }
    public static void show(){

        System.out.println("❤❤❤❤❤❤❤❤❤❤Beverage Manager❤❤❤❤❤❤❤❤❤");
        System.out.println("❤                                            ❤");
        System.out.println("❤       1. Hiển thị hóa đơn                  ❤");
        System.out.println("❤       2. Nhập hóa đơn                      ❤");
        System.out.println("❤       3. Xuất hóa đơn                      ❤");
        System.out.println("❤       0. Thoát || 8. Để thoát chương trình ❤");
        System.out.println("❤                                            ❤");
        System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤  ❤");

    }

}
