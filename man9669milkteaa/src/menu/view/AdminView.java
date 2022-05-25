package menu.view;

import menu.services.IUserService;
import menu.services.UserService;
import menu.utils.AppUtils;

import javax.print.attribute.standard.MediaSize;
import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class AdminView {
    private final IUserService userService;
    private final Scanner scanner = new Scanner(System.in);

    public AdminView() {
        userService = UserService.getInstance();
    }

    public void adminLogin() {
        boolean isRetry;
        System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ĐĂNG NHẬP HỆ THỐNG❤ ❤ ❤ ❤ ❤ ❤");
        do {
            System.out.println("Username");
            String username = AppUtils.retryString("Username");
            System.out.println("Mật khẩu");
            String password = AppUtils.retryString("Mật khẩu");
            if (userService.adminLogin(username, password) == null) {
                System.out.println("Tài khoản không hợp lệ, vui lòng nhập lại.");
                isRetry = isRetry();
            } else {
                System.out.println("Bạn đã đăng nhập thành công!");
                System.out.println("CHÀO MỪNG BẠN ĐÃ ĐÃ ĐẾN VỚI CỦA HÀNG TRÀ SỮA MAN9669\n");
                isRetry = false;
            }
        } while (isRetry);
    }

    private boolean isRetry() {
        do {
            try {
                System.out.println("❤ ❤ ❤ ❤ ❤ ❤CHỌN CHỨC NĂNG❤ ❤ ❤ ❤ ❤ ❤ ❤");
                System.out.println("❤                                           ❤");
                System.out.println("❤      1. Nhấn 'y' để đăng nhập lại         ❤");
                System.out.println("❤      2. Nhấn 'n' để thoát chương trình    ❤");
                System.out.println("❤                                           ❤");
                System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤");
                System.out.print(" ⭆ ");
                String option = scanner.nextLine();
                switch (option){
                    case "y":
                        return true;
                    case "n":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại.");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Nhập sai! Vui lòng nhâp lại.");
                ex.printStackTrace();
            }
        } while (true);
    }
}
