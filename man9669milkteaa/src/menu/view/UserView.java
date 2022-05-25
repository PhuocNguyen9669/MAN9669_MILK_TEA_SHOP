package menu.view;

import menu.model.Role;
import menu.model.User;
import menu.services.IUserService;
import menu.services.UserService;
import menu.utils.AppUtils;
import menu.utils.InstantUtils;
import menu.utils.ValidateUtils;

import java.util.List;
import java.util.Scanner;


public class UserView {
    private final IUserService userService;
    private final Scanner scanner = new Scanner(System.in);

    public UserView() {
        userService = UserService.getInstance();
    }

    public void addUser() {
        do {
            try {
                long id = System.currentTimeMillis() / 1000;
                String userName = inputUsername();
                String password = inputPassword();
                String fullName = inputFullName(InputOption.ADD);
                String phone = inputPhone(InputOption.ADD);
                String address = inputAddress(InputOption.ADD);

                User user = new User(id, userName, password, fullName, phone, address, Role.USER);
                setRole(user);
                userService.add(user);
                System.out.println("Đã thêm thành công!");
                showUsers(InputOption.ADD);
            } catch (Exception ex) {
                System.out.println("Nhập sai! Vui lòng nhập lại.");
            }
        } while (AppUtils.isRetry(InputOption.ADD));
    }
    public void setRole(User user){
        System.out.println("❤ ❤ SET ROLE ❤ ❤");
        System.out.println("❤    1.USER     ❤");
        System.out.println("❤    2.ADMIN    ❤");
        System.out.println("❤ ❤ ❤❤ ❤❤ ❤ ❤");
        System.out.println("Chọn Role: ");
        System.out.print(" ⭆ ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1:
                user.setRole(Role.USER);
                break;
            case 2:
                user.setRole(Role.ADMIN);
                break;
            default:
                System.out.println("Nhập không đúng! Vui lòng nhập lại.");
                setRole(user);
        }
    }

    public void showUsers(InputOption inputOption){
        System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤  ❤ DANH SÁCH NGƯỜI DÙNG❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤");
        System.out.printf("%-15s %-22s %-15s %-20s %-20s %-20s %-20s\n","Id","Tên","Số điện thoại", "Địa chỉ", "Người dùng", "Ngày tạo","Ngày cập nhât");
        List<User> users = userService.findALl();
        for (User user : users) {
            System.out.printf("%-15d %-22s %-15s %-20s %-20s %-20s %-20s \n",
                    user.getId(),
                    user.getFullName(),
                    user.getPhone(),
                    user.getAddress(),
                    user.getRole(),
                    InstantUtils.instantToString(user.getCreatedAT()),
                    user.getUpdatedAT() == null ? "" : InstantUtils.instantToString(user.getUpdatedAT())
                    );
        }
        System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ");
        if (inputOption == InputOption.SHOW) AppUtils.isRetry(InputOption.SHOW);
    }

    public void updateUser(){
        boolean isRetry = false;
        do {
            try {
                showUsers(InputOption.UPDATE);
                // Nếu ID không tồn tại sẽ không thoát ra khỏi vòng lặp trong hàm InputId
                int id = inputId(InputOption.UPDATE);
                System.out.println("❤ ❤ ❤ ❤ ❤ SỬA THÔNG TIN NGƯỜI DÙNG ❤ ❤ ❤ ❤ ❤❤  ");
                System.out.println("❤                1. Đổi tên                       ❤ ");
                System.out.println("❤                2. Sửa số điện thoại             ❤ ");
                System.out.println("❤                3. Sửa địa chỉ                   ❤ ");
                System.out.println("❤                4. Quay lại                      ❤");
                System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ");

                int option = AppUtils.retryChoose(1,4);
                User newUser = new User();
                newUser.setId(id);
                switch (option){
                    case 1:
                        String name = inputFullName(InputOption.UPDATE);
                        newUser.setFullName(name);
                        userService.update(newUser);
                        System.out.println("Bạn đã đổi tên thành công!");
                        showUsers(InputOption.SHOW);
                        break;
                    case 2:
                        String phone = inputPhone(InputOption.UPDATE);
                        newUser.setPhone(phone);
                        userService.update(newUser);
                        System.out.println("Bạn đã đổi số điện thoại thành công!");
                        showUsers(InputOption.SHOW);
                        break;
                    case 3:
                        String address = inputAddress(InputOption.UPDATE);
                        newUser.setAddress(address);
                        userService.update(newUser);
                        System.out.println("Bạn đã đổi số điện thoại thành công!");
                        showUsers(InputOption.SHOW);
                        break;
                }
                isRetry = option != 4 && AppUtils.isRetry(InputOption.UPDATE);
            } catch (Exception ex){
                System.out.println("Nhập sai! Vui lòng nhập lại");
            }
        } while (isRetry);
    }

    public void deleteUser(){
        showUsers(InputOption.DELETE);
        int id;
        while (!userService.existsById(id = inputId(InputOption.DELETE))){
            System.out.println("Không tìm thấy người dùng cần xóa.");
            System.out.println("Nhấn 'y' để tìm lại người dùng \t|\t 'q' để quay lại \t|\t 't' để thoát chương trình");
            System.out.print(" ⭆ ");
            String option = scanner.nextLine();
            switch (option){
                case "y":
                    break;
                case "q":
                    return;
                case "t":
                    AppUtils.exit();
                    break;
                default:
                    System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại.");
                    break;

            }
        }

        System.out.println("❤ ❤ ❤ ❤ ❤ ❤ XÁC NHẬN XÓA❤ ❤ ❤ ❤ ❤ ❤");
        System.out.println("❤            1. Nhấn 1 để xóa            ❤");
        System.out.println("❤            2. Nhấn 2 để quay lại       ❤");
        System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤❤ ❤");
        int option = AppUtils.retryChoose(1,2);
        if (option == 1){
            userService.deleteById(id);
            System.out.println("Đã xóa người dùng thành công!");
            showUsers(InputOption.DELETE);
            AppUtils.isRetry(InputOption.DELETE);
        }
    }

    private int inputId(InputOption option) {
        int id;
        switch (option) {
            case ADD:
                System.out.println("Nhập Id: ");
                break;
            case UPDATE:
                System.out.println("Nhập id bạn muốn sửa: ");
                break;
            case DELETE:
                System.out.println("Nhập Id: ");
                break;
        }
        boolean isRetry = false;
        do {
            id = AppUtils.retryParseInt();
            boolean exist = userService.existsById(id);
            switch (option) {
                case ADD:
                    if (exist) {
                        System.out.println("Id nady đã tồn tại! Vui lòng nhập lại.");
                    }
                    isRetry = exist;
                    break;
                case UPDATE:
                    if (!exist) {
                        System.out.println("Không tìm thấy Id! Vui lòng nhập lại.");
                    }
                    isRetry = !exist;
                    break;
                case DELETE:
                    if (!exist){
                        System.out.println("Không tìm thấy Id! Vui lòng nhập lại.");
                    }
            }
        } while (isRetry);
        return id;
    }

    public String inputUsername() {
        System.out.println("Nhập Username (chỉ đc bắt đầu bằng chữ hoặc số, tối thiểu 3 đến 20 ký tự bao gồm số, chữ số, dấu chấm và dấu gạch ngang dưới)");
        String username;

        do {
            if (!ValidateUtils.isUsernameValid(username = AppUtils.retryString("Username"))){
                System.out.println(username + " của bạn không đúng định dạng! Vui lòng kiểm tra và nhập lại");
                System.out.print(" ⭆ ");
                continue;
            }
            if (userService.existsByUsername(username)){
                System.out.println("Username này đã tồn tại! Vui lòng nhập lại");
                System.out.print(" ⭆ ");
                continue;
            }
            break;
        } while (true);
        return username;
    }
    public String inputFullName(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập họ và tên (vd: Nguyen Van A)");
                break;
            case UPDATE:
                System.out.println("Nhập tên mà bạn muốn sửa đổi: ");
                break;
        }
        System.out.print(" ⭆ ");
        String fullName;
        while (!ValidateUtils.isFullNameValid(fullName = scanner.nextLine())) {
            System.out.println("(Tên " + fullName + " không đúng định dạng." + " Vui lòng nhập lại" +
                    " Tên phải viết hoa chữ cái đầu, chỉ điền được chữ và khoảng trắng)");
            System.out.println("Nhập tên (vd: Nguyen Van An)");
            System.out.print(" ⭆ ");
        }
        return fullName;
    }
    public String inputPhone(InputOption option){
        switch (option){
            case ADD:
                System.out.println("Nhập số điện thoại (vd: 0987654321): ");
                break;
            case UPDATE:
                System.out.println("Nhập số điện thoại bạn muốn đổi: ");
                break;
        }
        return AppUtils.retryInputPhone();
    }
    private String inputPassword(){
        System.out.println("Nhập mật khẩu (mật khẩu phải lớn hơn 8 ký tự )");
        System.out.print(" ⭆ ");
        String password;
        while (!ValidateUtils.isPasswordValid(password = scanner.nextLine())){
            System.out.println("Mật khẩu không đúng định dang! Vui lòng nhập lại.");
            System.out.print(" ⭆ ");
        }
        return password;
    }
    private String inputAddress(InputOption option){
        switch (option){
            case ADD:
                System.out.println("Nhập địa chỉ (vd: Huế)");
                break;
            case UPDATE:
                System.out.println("Nhập địa chỉ mà bạn muốn đổi: ");
                break;
        }
        System.out.print(" ⭆ ");
        String address = scanner.nextLine();
        while (address.trim().isEmpty()) {
            System.out.println("Không được để trống");
            System.out.println("Nhập địa chỉ");
            System.out.print(" ⭆ ");
            address = scanner.nextLine();
        }
        return address;
    }

}
