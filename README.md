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
    -   Specific implementation details:
      - Phương thức **checkMediaInCart(Media media)** thuộc **Control Coupling** do phục thuộc vào hàm **checkMediaInCart** của đối tượng Cart.
      - Phương thức **makePayment(...)** thuộc **Control Coupling** do phụ thuộc vào cấu trúc nội bộ của **VnPaySubSystem** và kiểm soát luồng của đối tượng này thông qua **save** và **makePaymentTransaction**
      - Phương thức **getUrlPay** thuộc **Data Coupling** do sử dụng và gọi phương thức **generatePayUrl()** của **vnPayService** - Phương thức **emptyCart()** thuộc **Control Coupling** do phương thức này thay đổi phần dữ liệu của Cart.
      - Phương thức **createOrder()** thuộc **Control Coupling** do phương thức này ảnh hưởng đến luồng và trạng thái của đối tượng **order**
      - Phương thức **createInvoice()** tương tự **createOrder()**
      - Các phương thức **validate...** thuộc **Control Coupling** do bên trong tồn tại các đoạn code phụ thuộc lẫn nhau và phụ thuộc vào tham số truyền vào - Phương thức **validatePlaceRushOrderData** thuộc **Common Coupling** do truy cập vào biến global trong util.
      - Một số phương thức không có gì truyền hoặc không thực hiện điều gì thuộc là **không xác định Coupling**
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

<details>
<summary>Ninh Thành Vinh</summary>
<br>  

- Assigned tasks:
  - Task: Bổ sung Coupling cho view.screen.cart và view.screen.invoice 
- Pull Request:
  - Specific implementation details:
    - Phương thức **CartScreenHandler(Stage stage, String screenPath)** thuộc **Control Coupling** do sử dụng event **setOnMouseClicked**.
    - Phương thức **requestOrder()** thuộc **Control Coupling** do phụ thuộc vào **placeOrderController** kiểm soát luồng.
    - Phương thức **displayCartWithMediaAvailability()** thuộc **Control Coupling** do gọi và sử dụng các phương thức từ **MediaHandler**.
    - Phương thức **setMediaInfo()** thuộc **Control Coupling** do sử dụng các event từ button.
    - Phương thức **setInvoiceInfo()** thuộc **Control Coupling** do sử dụng thông tin lấy từ **invoice** ảnh hưởng đến luồng.
    - Phương thức **confirmInvoice(MouseEvent event)** thuộc **Control Coupling** do phương thức thay đổi đối tượng **paymentScreen**
    - Phương thức **setMediaInfo()** thuộc **Control Coupling** do phụ thuộc vào phương thức của **orderMedia**.
    - Phương thức **MediaHandler(String screenPath, CartScreenHandler cartScreen)** thuộc **Data Coupling** do sử dụng đủ params được truyền vào.
    - Phương thức **setCartMedia(CartMedia cartMedia)** thuộc **Data Coupling** do sử dụng đủ params được truyền vào.
    - Phương thức **InvoiceScreenHandler(Stage stage, String screenPath, Invoice invoice)** thuộc **Data Coupling** do sử dụng đủ params được truyền vào.
    - Phương thức **MediaInvoiceScreenHandler(String screenPath)** và **setOrderMedia(OrderMedia orderMedia)** thuộc **Data Coupling** do sử dụng vừa đủ params được truyền vào.
    - Một số phương thức không có gì truyền hoặc không thực hiện điều gì thuộc là **không xác định Coupling** .
</details>

<details>
<summary>Nguyễn Thị Hồng Vân</summary>
<br>

-   Assigned tasks:
    -   Task 1: Bổ sung Coupling cho package Views.Screen.Home/Payment/Shipping
-   Implementation details:
    -   Pull Request:
    -   Specific implementation details:
      - Phương thức **initialize** thuộc **Control Coupling** do phục thuộc vào hàm **HomeController** để lấy danh sách các phương tiện.
      - Phương thức **addMediaHome** thuộc **Data Coupling** phụ thuộc vào dữ liệu từ danh sách **homeItems**, **hboxMedia**, và các trường trong **MediaHandler**
      - Phương thức **addMenuItem** thuộc **Data Coupling** phụ thuộc vào dữ liệu từ **splitMenuBtnSearch** để thêm các mục menu vào **MenuButton**
      - Phương thức **setBController** và **getBController** thuộc **Control Coupling** do phụ thuộc vào HomeController để gán và lấy **HomeController**

      - Phương thức **displayWebView** thuộc **Control Coupling** phụ thuộc vào **PaymentController** để lấy URL thanh toán **(getUrlPay)** và **handleUrlChanged** để xử lý sự kiện thay đổi URL
      - Phương thức **handleUrlChanged** thuộc **Control Coupling** phụ thuộc vào cấu trúc URI để lấy query string và phương thức **payOrder** để xử lý kết quả thanh toán
      - Phương thức **payOrder** thuộc **Control Coupling** phụ thuộc vào **PaymentController** để thực hiện thanh toán và **ResultScreenHandler** để hiển thị kết quả

      - Phương thức **submitDeliveryInfo** thuộc **Control Coupling** phụ thuộc vào **PlaceOrderController** để thực hiện xác nhận thông tin giao hàng, tính phí vận chuyển,..
      - Sự kiện **(submitDeliveryInfo(MouseEvent event))** thuộc **Control Coupling** phụ thuộc vào phương thức **submitDeliveryInfo** để xử lý sự kiện khi người dùng nhấn nút "Submit".
      - Trường biến **(name, phone, address, instructions, province)**thuộc **Data Coupling** phụ thuộc vào phương thức chứa dữ liệu nhập từ người dùng và được sử dụng để xác nhận thông tin giao hàng.
      - Một số phương thức không có gì truyền hoặc không thực hiện điều gì thuộc là **không xác định Coupling**
    </details>
