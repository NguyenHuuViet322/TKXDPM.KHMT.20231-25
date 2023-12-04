# TKXDPM.KHMT.20231-25

This is a Capstone's source code for Software Design and Construction project

## Team member

| Name                | Role        |
| :------------------ | :---------- |
| Nguyễn Hữu Việt     | Team Leader |
| Chu Văn Việt        | Member      |
| Nguyễn Thị Hồng Vân | Member      |
| Ninh Thành Vinh     | Member      |
| Nguyễn Thế Vũ       | Member      |

## Report Content

<details>
  <summary>W13: 27/11/2023~03/12/2023 </summary>
<br>

<details>
<summary>Nguyễn Hữu Việt</summary>
<br>

-   Assigned tasks:
    -   Task 1: Bổ sung Coupling cho package controller
-   Implementation details:
    -   Pull Request:
    -   Specific implementation details: - Phương thức **checkMediaInCart(Media media)** thuộc **Control Coupling** do phục thuộc vào hàm **checkMediaInCart** của đối tượng Cart. - Phương thức **makePayment(...)** thuộc **Control Coupling** do phụ thuộc vào cấu trúc nội bộ của **VnPaySubSystem** và kiểm soát luồng của đối tượng này thông qua **save** và **makePaymentTransaction** - Phương thức **getUrlPay** thuộc **Data Coupling** do sử dụng và gọi phương thức **generatePayUrl()** của **vnPayService** - Phương thức **emptyCart()** thuộc **Control Coupling** do phương thức này thay đổi phần dữ liệu của Cart. - Phương thức **createOrder()** thuộc **Control Coupling** do phương thức này ảnh hưởng đến luồng và trạng thái của đối tượng **order** - Phương thức **createInvoice()** tương tự **createOrder()** - Các phương thức **validate...** thuộc **Control Coupling** do bên trong tồn tại các đoạn code phụ thuộc lẫn nhau và phụ thuộc vào tham số truyền vào - Phương thức **validatePlaceRushOrderData** thuộc **Common Coupling** do truy cập vào biến global trong util. - Một số phương thức không có gì truyền hoặc không thực hiện điều gì thuộc là **không xác định Coupling**
    </details>

<details>
<summary>Nguyễn Thế Vũ</summary>
<br>
  
- Assigned tasks:
- Task: Bổ sung Coupling cho package subSystem
 - Pull Request: 
  - Specific implementation details:
    - Phương thức **generatePayUrl(int amount, String contents)** trong VnPaySubsystem thuộc **Data Coupling** do chỉ phục thuộc vào hàm **generatePayUrl(int amount, String contents)** của đối tượng VnPaySubsystemController.
    - Phương thức **makePaymentTransaction(Map<String, String> response)** trong VnPaySubsystem thuộc **Data Coupling** do chỉ phụ thuộc vào **makePaymentTransaction** của **VnPaySubsystemController**

    **File VnPaySubsystemController:**

    Hàm **generatePayOrderUrl**:
    - Data Coupling:
    Phương thức này sử dụng dữ liệu từ Config như `getRandomNumber` và `getIpAddress`. Các giá trị này được truyền vào để tạo các tham số thanh toán.
    Dữ liệu được truyền giữa **VnPaySubsystemController** và **Config** thông qua việc gọi các hàm tiện ích của **Config**.

    Hàm **makePaymentTransaction**:
    - Control Coupling:
    Phương thức này chứa một khối switch để xử lý các trạng thái của giao dịch (errorCode). Điều này có thể được coi là control coupling vì **VnPaySubsystemController** định nghĩa cách xử lý các loại lỗi và trạng thái cụ thể.
    - Một số phương thức không có gì truyền hoặc không thực hiện điều gì thuộc là **không xác định Coupling**

</details>
