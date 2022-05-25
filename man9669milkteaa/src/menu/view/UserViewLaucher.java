package menu.view;

import java.util.Scanner;

public class UserViewLaucher {
    public static void lauch() {
        Scanner scanner = new Scanner(System.in);
        UserView userView = new UserView();
        int option = -1;
        do {
            menuUser();
            try {
                do {
                    System.out.println("Chọn chức năng: ");
                    System.out.print(" ⭆ ");
                    option = Integer.parseInt(scanner.nextLine());
                    if (option < 0 || option > 3 ) {
                        System.out.println("Chọn chức năng không đúng, vui lòng chọn lại!");
                    }
                } while (option > 4 || option < 0) ;

                switch (option){
                    case 1:
                        userView.addUser();
                        break;
                    case 2:
                        userView.updateUser();
                        break;
                    case 3:
                        userView.deleteUser();
                        break;
                    case 4:
                        userView.showUsers(InputOption.SHOW);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Nhập sai! Vui lòng nhập lại!");
            }
        } while (option != 0);
    }
    public static void menuUser(){
        System.out.println("❤❤❤❤❤❤❤❤QUẢN LÝ NGƯỜI DÙNG❤❤❤❤❤❤❤❤❤❤");
        System.out.println("❤                                            ❤");
        System.out.println("❤      1. Thêm người dùng                    ❤");
        System.out.println("❤      2. Sửa thông tin người dùng           ❤");
        System.out.println("❤      3. Xóa thông tin người dùng           ❤");
        System.out.println("❤      4. Hiện danh sách người dùng          ❤");
        System.out.println("❤      0. Quay lại                           ❤");
        System.out.println("❤                                            ❤");
        System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤  ❤");
    }
}
