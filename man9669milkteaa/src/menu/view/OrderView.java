package menu.view;

import menu.model.Order;
import menu.model.OrderItem;
import menu.model.Product;
import menu.services.*;
import menu.utils.AppUtils;
import menu.utils.InstantUtils;
import menu.utils.ValidateUtils;

import java.util.List;
import java.util.Scanner;

public class OrderView {
    private final IProductService productService;
    private final IOderService oderService;
    private final IOderItemService oderItemService;
    private final Scanner scanner = new Scanner(System.in);

    public OrderView() {
        productService = ProductService.getInstance();
        oderService = OrderService.getInstance();
        oderItemService = OrderItemService.getInstance();
    }

    public OrderItem addOrderItems(long orderId) {
        do {
            try {
                oderItemService.findAll();
                ProductView productView = new ProductView();
                productView.showProduct(InputOption.ADD);
                long id = System.currentTimeMillis() / 1000;
                System.out.println("Nhập id trà sữa: ");
                System.out.print(" ⭆ ");
                int milkTeaId = Integer.parseInt(scanner.nextLine());
                while (!productService.existById(milkTeaId)) {
                    System.out.println("Id trà sữa không tồn tại");
                    System.out.println("Nhập id trà sữa: ");
                    System.out.print(" ⭆ ");
                    milkTeaId = Integer.parseInt(scanner.nextLine());
                }
                Product product = productService.findById(milkTeaId);
                double price = product.getPrice();
                int oldQuantity = product.getQuantity();
                System.out.println("Nhập số lượng");
                System.out.print("⭆ ");
                int quantity = Integer.parseInt(scanner.nextLine());

                while (!checkQuantityMilkTea(product, quantity)) {
                    System.out.println("Vượt quá số lượng! Vui lòng nhập lại");
                    System.out.println("Nhập số lượng");
                    System.out.print(" ⭆ ");
                    quantity = Integer.parseInt(scanner.nextLine());
                }
                String milkTeaName = product.getNameProduct();
                double total = quantity * price;
                int currentQuantity = oldQuantity - quantity;
                product.setQuantity(currentQuantity);
                OrderItem orderItem = new OrderItem(id, price, quantity, orderId, milkTeaId, milkTeaName, total);
                return orderItem;

            } catch (Exception ex) {
                System.out.println("Nhập sai! Vui lòng nhập lại.");
            }
        } while (true);
    }

    public boolean checkQuantityMilkTea(Product product, int quantity) {
        if (quantity <= product.getQuantity())
            return true;
        else
            return false;
    }

    public void addOder() {
        try {
            oderService.findAll();
            long orderId = System.currentTimeMillis() / 1000;
            System.out.println("Nhập họ và tên (vd: Nguyen Van A) ");
            System.out.print(" ⭆ ");
            String name = scanner.nextLine();
            while (!ValidateUtils.isFullNameValid(name)) {
                System.out.println("Tên " + name + " không đúng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu và không dấu)");
                System.out.println("Nhập tên (vd: Nguyen Van A) ");
                System.out.print(" ⭆ ");
                name = scanner.nextLine();
            }
            System.out.println("Nhập số điện thoại ");
            System.out.print(" ⭆ ");
            String phone = scanner.nextLine();
            while (!ValidateUtils.isPhoneValid(phone)) {
                System.out.println("Số " + phone + " của bạn không đúng. Vui lòng nhập lại! " + "(Số điện thoại bao gồm 10 số và bắt đầu là số 0)");
                System.out.println("Nhập số điện thoại (vd: 0345129876)");
                System.out.print(" ⭆ ");
                phone = scanner.nextLine();
            }
            System.out.println("Nhập địa chỉ");
            System.out.print(" ⭆ ");
            String address = scanner.nextLine();
            while (address.trim().isEmpty()) {
                System.out.println("Không được để trống");
                System.out.println("Nhập địa chỉ");
                System.out.print("=> ");
                address = scanner.nextLine();
            }
            OrderItem orderItem = addOrderItems(orderId);
            Order order = new Order(orderId, name, phone, address);
            oderItemService.add(orderItem);
            oderService.add(order);
            System.out.println("Tạo đơn hàng thành công!");

            System.out.println(" ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ");
            System.out.println("❤                                               ❤");
            System.out.println("❤     1. Nhấn để tạo tiếp đơn hàng              ❤");
            System.out.println("❤     2. Nhấn để in hóa đơn                     ❤");
            System.out.println("❤     3. Nhấn để trở lại                        ❤");
            System.out.println("❤     4. Nhấn để thoát                          ❤");
            System.out.println("❤                                               ❤");
            System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ");
            System.out.print(" ⭆ ");
            do {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addOder();
                        break;
                    case 2:
                        showPaymentInfo(orderItem, order);
                        break;
                    case 3:
                        OderViewLaucher.menuManager();
                        break;
                    case 4:
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Nhập không hợp lệ! Vui lòng nhập lại");
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Nhập sai! vui lòng nhập lại!");
        }
    }


    public void showPaymentInfo(OrderItem orderItem, Order order) {
        try {
            System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ HOÁ ĐƠN ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤");
            System.out.printf("❤ %-20s", "Ngày tạo đơn hàng: ");
            System.out.printf(": %-68s ❤\n", InstantUtils.instantToString(order.getCreatedAt())+" ");
            System.out.printf("❤ %-20s", "Tên khách hàng: " );
            System.out.printf(": %-68s ❤\n",order.getFullName() + " ");
            System.out.printf("❤ %-20s", "Địa chỉ: ");
            System.out.printf(": %-68s ❤\n", order.getAddress() + " ");
            System.out.printf("❤ %-20s", "Điện thoại: ");
            System.out.printf(": %-68s ❤\n", order.getMobile() +" ");
            System.out.println("❤============================================================================================❤");
            System.out.printf("❤ %-20s %-20s %-20s %-27s ❤\n", "TÊN SẢN PHẨM", "SỐ LƯỢNG", "GIÁ TIỀN", "TỔNG TIỀN");
            System.out.printf("❤ %-20s %-20d %-20s %-27s ❤\n",
                    orderItem.getProductName(),
                    orderItem.getQuantity(),
                    AppUtils.doubleToVND(orderItem.getPrice()),
                    AppUtils.doubleToVND(orderItem.getPrice() * orderItem.getQuantity()));
            System.out.println("❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤ ❤❤");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                System.out.println("Nhấn ");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OderViewLaucher.menuManager();
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Nhấn không đúng! vui lòng chọn lại");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void showAllOrder() {
        List<Order> orders = oderService.findAll();
        List<OrderItem> orderItems = oderItemService.findAll();
        OrderItem newOrderItem = new OrderItem();
        try {
            double result = 0;
            double sum = 0;
            System.out.println("----------------------------------------------------------LIST ORDER------------------------------------------------------------------------|");
            System.out.println("|                                                                                                                                           |");
            System.out.printf("|%-15s %-20s %-16s %-20s %-15s %-10s %-15s %-19s  |\n", "    Id", "Tên khách hàng", "  SĐT", "Địa chỉ", "Tên trà sữa", "Số lượng", "   Giá", "   Tổng");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------|");
            for (Order order : orders) {
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getOrderId() == order.getId()) {
                        newOrderItem = orderItem;
                        break;
                    }
                }
                result = newOrderItem.getQuantity() * newOrderItem.getPrice();
                sum += result;
                System.out.printf("%-15d %-20s %-16s %-20s %-15s %-10d %-15s %-21s \n|",
                        order.getId(),
                        order.getFullName(),
                        order.getMobile(),
                        order.getAddress(),
                        newOrderItem.getProductName(),
                        newOrderItem.getQuantity(),
                        AppUtils.doubleToVND(newOrderItem.getPrice()),
                        AppUtils.doubleToVND(result));
                System.out.println("===========================================================================================================================================|");
                System.out.println("|                                                                                                                                           |");
            }
            System.out.println("|-------------------------------------------------------------------------------- Tổng tiền: \t  " + AppUtils.doubleToVND(sum));
            System.out.println("|                                                                                                                                           |");
            System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OderViewLaucher.menuManager();
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Nhấn không đúng! vui lòng chọn lại");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            System.out.println("Nhập sai! Vui lòng nhập lại.");
        }
    }

}
