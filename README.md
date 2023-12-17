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
    -   Phương thức **checkMediaInCart(Media media)** thuộc **Control Coupling** do phục thuộc vào hàm **checkMediaInCart** của đối tượng Cart.
    -   Phương thức **makePayment(...)** thuộc **Control Coupling** do phụ thuộc vào cấu trúc nội bộ của **VnPaySubSystem** và kiểm soát luồng của đối tượng này thông qua **save** và **makePaymentTransaction**
    -   Phương thức **getUrlPay** thuộc **Data Coupling** do sử dụng và gọi phương thức **generatePayUrl()** của **vnPayService** - Phương thức **emptyCart()** thuộc **Control Coupling** do phương thức này thay đổi phần dữ liệu của Cart.
    -   Phương thức **createOrder()** thuộc **Control Coupling** do phương thức này ảnh hưởng đến luồng và trạng thái của đối tượng **order**
    -   Phương thức **createInvoice()** tương tự **createOrder()**
    -   Các phương thức **validate...** thuộc **Control Coupling** do bên trong tồn tại các đoạn code phụ thuộc lẫn nhau và phụ thuộc vào tham số truyền vào - Phương thức **validatePlaceRushOrderData** thuộc **Common Coupling** do truy cập vào biến global trong util.
    -   Một số phương thức không có gì truyền hoặc không thực hiện điều gì thuộc là **không xác định Coupling**
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

-   Assigned tasks:
    -   Task: Bổ sung Coupling cho view.screen.cart và view.screen.invoice
-   Pull Request:
    -   Specific implementation details: - Phương thức **CartScreenHandler(Stage stage, String screenPath)** thuộc **Control Coupling** do sử dụng event **setOnMouseClicked**. - Phương thức **requestOrder()** thuộc **Control Coupling** do phụ thuộc vào **placeOrderController** kiểm soát luồng. - Phương thức **displayCartWithMediaAvailability()** thuộc **Control Coupling** do gọi và sử dụng các phương thức từ **MediaHandler**. - Phương thức **setMediaInfo()** thuộc **Control Coupling** do sử dụng các event từ button. - Phương thức **setInvoiceInfo()** thuộc **Control Coupling** do sử dụng thông tin lấy từ **invoice** ảnh hưởng đến luồng. - Phương thức **confirmInvoice(MouseEvent event)** thuộc **Control Coupling** do phương thức thay đổi đối tượng **paymentScreen** - Phương thức **setMediaInfo()** thuộc **Control Coupling** do phụ thuộc vào phương thức của **orderMedia**. - Phương thức **MediaHandler(String screenPath, CartScreenHandler cartScreen)** thuộc **Data Coupling** do sử dụng đủ params được truyền vào. - Phương thức **setCartMedia(CartMedia cartMedia)** thuộc **Data Coupling** do sử dụng đủ params được truyền vào. - Phương thức **InvoiceScreenHandler(Stage stage, String screenPath, Invoice invoice)** thuộc **Data Coupling** do sử dụng đủ params được truyền vào. - Phương thức **MediaInvoiceScreenHandler(String screenPath)** và **setOrderMedia(OrderMedia orderMedia)** thuộc **Data Coupling** do sử dụng vừa đủ params được truyền vào. - Một số phương thức không có gì truyền hoặc không thực hiện điều gì thuộc là **không xác định Coupling** .
    </details>

<details>
<summary>Nguyễn Thị Hồng Vân</summary>
<br>

-   Assigned tasks:
    -   Task 1: Bổ sung Coupling cho package Views.Screen.Home/Payment/Shipping
-   Implementation details:

    -   Pull Request:
    -   Specific implementation details:
    -   Phương thức **initialize** thuộc **Control Coupling** do phục thuộc vào hàm **HomeController** để lấy danh sách các phương tiện.
    -   Phương thức **addMediaHome** thuộc **Data Coupling** phụ thuộc vào dữ liệu từ danh sách **homeItems**, **hboxMedia**, và các trường trong **MediaHandler**
    -   Phương thức **addMenuItem** thuộc **Data Coupling** phụ thuộc vào dữ liệu từ **splitMenuBtnSearch** để thêm các mục menu vào **MenuButton**
    -   Phương thức **setBController** và **getBController** thuộc **Control Coupling** do phụ thuộc vào HomeController để gán và lấy **HomeController**

    -   Phương thức **displayWebView** thuộc **Control Coupling** phụ thuộc vào **PaymentController** để lấy URL thanh toán **(getUrlPay)** và **handleUrlChanged** để xử lý sự kiện thay đổi URL
    -   Phương thức **handleUrlChanged** thuộc **Control Coupling** phụ thuộc vào cấu trúc URI để lấy query string và phương thức **payOrder** để xử lý kết quả thanh toán
    -   Phương thức **payOrder** thuộc **Control Coupling** phụ thuộc vào **PaymentController** để thực hiện thanh toán và **ResultScreenHandler** để hiển thị kết quả

    -   Phương thức **submitDeliveryInfo** thuộc **Control Coupling** phụ thuộc vào **PlaceOrderController** để thực hiện xác nhận thông tin giao hàng, tính phí vận chuyển,..
    -   Sự kiện **(submitDeliveryInfo(MouseEvent event))** thuộc **Control Coupling** phụ thuộc vào phương thức **submitDeliveryInfo** để xử lý sự kiện khi người dùng nhấn nút "Submit".
    -   Trường biến **(name, phone, address, instructions, province)**thuộc **Data Coupling** phụ thuộc vào phương thức chứa dữ liệu nhập từ người dùng và được sử dụng để xác nhận thông tin giao hàng.
    -   Một số phương thức không có gì truyền hoặc không thực hiện điều gì thuộc là **không xác định Coupling**
    </details>

</details>
  <details>
<summary>Chu Văn Việt</summary>
<br>

-   Assigned tasks:
-   Task: Bổ sung Coupling cho views.screen.Popup, views.screen.BaseScreenHandler, views.screen.FXMLScreenHandler, views.screen.SplashScreenHandler
-   Pull Request:

    -   Specific implementation details:
        **File PopupScreen:**

        -   Data Coupling:
            Hàm **popup(String message, String imagepath, Boolean undecorated)**: Hàm này có data coupling với các tham số đầu vào như `message`, `imagepath`, và `undecorated`.
            Hàm **setImage(String path)**: Data coupling xuất hiện khi `path` được truyền vào hàm để đặt hình ảnh cho thành phần `tickicon`.
            Hàm **success(String message)** và **error(String message)**: Data coupling xuất hiện thông qua tham số `message` được truyền vào hàm để đặt nội dung thông báo của popup.

        -   Control Coupling:
            Hàm **show(Boolean autoclose)** và **show(double time)**: Cả hai hàm này sử dụng biến **stage**, là một thành phần kiểm soát để hiển thị cửa sổ popup.

        **File BaseScreenHandler:**

        -   Data Coupling:
            Hàm **setPreviousScreen(BaseScreenHandler prev)**: Data coupling xuất hiện khi tham chiếu đến màn hình trước đó (`prev`) được truyền vào hàm.
            Hàm **forward(Hashtable messages)**: Data coupling xuất hiện khi `messages` được truyền vào hàm, làm thay đổi trạng thái của thuộc tính `messages` trong class.
            Hàm **setHomeScreenHandler(HomeScreenHandler HomeScreenHandler)**: Data coupling xuất hiện khi **HomeScreenHandler** được truyền vào hàm. Dữ liệu liên kết giữa **BaseScreenHandler** và **HomeScreenHandler** thông qua việc thiết lập thuộc tính `homeScreenHandler`.
            Hàm **setScreenTitle(String string)**: Data coupling xuất hiện khi `string` được truyền vào hàm để đặt tiêu đề của cửa sổ.
            Hàm **setBController(BaseController bController)**: Data coupling xuất hiện khi `bController` được truyền vào hàm để thiết lập thuộc tính `bController`.

        -   Control Coupling:
            Hàm **show()**: Control coupling xuất hiện khi hàm này sử dụng `this.stage` để hiển thị cửa sổ.

        **File SplashScreenHandler:**

        -   Data Coupling:
            Hàm **initialize(URL location, ResourceBundle resources)**: Data coupling xuất hiện khi đường dẫn hình ảnh `"assets/images/Logo.png"` được chọn một cách cụ thể. Nếu đường dẫn thay đổi, cần sửa lại trực tiếp trong code. Điều này tạo ra một mức độ phụ thuộc vào dữ liệu cụ thể trong mã nguồn.

        -   Control Coupling:
            Hàm **initialize(URL location, ResourceBundle resources)**: Có mức độ control coupling khi sử dụng trực tiếp `logo`, một thành phần FXML (JavaFX) được định nghĩa trong file FXML của splash screen. Hàm này sử dụng `logo` để đặt hình ảnh cho `ImageView`.

        -   Một số phương thức không có gì truyền hoặc không thực hiện điều gì thuộc là **không xác định Coupling** .

</details>
</details>

<details>
  <summary>W14: 04/12/2023~10/12/2023 </summary>
<br>
<details>
<summary>Nguyễn Thế Vũ</summary>
<br>
  
- Assigned tasks:
- Task: Bổ sung Cohesion cho package subSystem, refactor lại package subSystem
 - Pull Request: 
  - Specific implementation details:

    **File VnPaySubsystemController:**

    Hàm **generatePayUrl**:
    - Data Coupling:
    Phương thức này thực hiện một nhiệm vụ duy nhất và liên quan đến việc tạo URL thanh toán dựa trên các tham số cung cấp. Các bước trong hàm tất cả đều hướng về một mục tiêu chung, không có sự phân tách rõ ràng giữa các chức năng. Điều này tăng tính đơn nhất và dễ bảo trì của hàm.

    Hàm **makePaymentTransaction**:
    - Functional Cohesion:
    Vì nó tập trung vào một nhiệm vụ cụ thể và liên quan: tạo đối tượng PaymentTransaction từ dữ liệu đầu vào là một bản đồ (Map<String, String> response). Sau đó, hàm xử lý các trường hợp lỗi khác nhau liên quan đến giao dịch thanh toán. Chức năng của hàm này rõ ràng và đồng nhất, giúp cải thiện khả năng bảo trì và hiểu mã nguồn.

</details>

<details>
<summary>Nguyễn Hữu Việt</summary>
<br>  
 - Task: Bổ sung Cohesion cho package controller
 - Pull Request: 
  - Specific implementation details:

    **File BaseController:**

    Hàm **checkMediaInCart(Media media)**:
    - Functional Cohesion:
    Hàm này có mục đích cụ thể là kiểm tra xem một đối tượng Media có trong giỏ hàng (Cart) hay không và trả về đối tượng CartMedia nếu có, ngược lại trả về null. Nó thực hiện một nhiệm vụ cụ thể và liên quan chặt chẽ đến chức năng của giỏ hàng.

    Hàm **getListCartMedia()**:
    - Functional Cohesion:
    Hàm này có mục đích là trả về danh sách các mục trong giỏ hàng. Nó thực hiện một chức năng cụ thể và liên quan chặt chẽ đến chức năng của giỏ hàng.

    **File HomeController:**

    Hàm **getAllMedia()**:
    - Coincidental Cohesion:
    Hàm này có mục đích là lấy tất cả các đối tượng Media từ cơ sở dữ liệu và trả về danh sách để hiển thị. Tuy nhiên, mặc dù chức năng này liên quan đến đối tượng Media, nó không liên quan chặt chẽ đến chức năng của HomeController như quản lý giỏ hàng. Hàm này chỉ là một phần nhỏ của chức năng tổng thể của HomeController.

    **File PaymentController:**

    Hàm **makePayment(Map<String, String> res, int orderId)**:
    - Functional Cohesion:
    Hàm này có mục đích thực hiện quá trình thanh toán. Nó gọi đến một đối tượng VnPaySubsystem để thực hiện giao dịch thanh toán và lưu trữ thông tin giao dịch. Chức năng này liên quan chặt chẽ đến quá trình thanh toán và quản lý đơn hàng.
    Hàm **getUrlPay(int amount, String content)**:
    - Functional Cohesion:
    Hàm này có mục đích là tạo URL thanh toán từ VnPaySubsystem. Chức năng này liên quan chặt chẽ đến việc tạo URL thanh toán từ subsystem VnPay.
    Hàm **emptyCart()**:
    - Functional Cohesion:
    Hàm này có mục đích là làm rỗng giỏ hàng. Chức năng này liên quan chặt chẽ đến quá trình quản lý giỏ hàng.

    **File PaymentController:**

    Hàm **placeOrder()**:
    - Coincidental Cohesion:
    Hàm này kiểm tra sự có sẵn của sản phẩm khi người dùng nhấn nút Place Order. Tuy nhiên, chức năng này không liên quan chặt chẽ đến các chức năng khác của PlaceOrderController, như tạo đơn đặt hàng hay tạo Invoice.
    Hàm **createOrder()**:
    - Functional Cohesion:
    Hàm này tạo đơn đặt hàng dựa trên thông tin từ giỏ hàng (Cart). Chức năng này liên quan chặt chẽ đến việc tạo đơn đặt hàng.
    Hàm **createInvoice(Order order)**:
    - Functional Cohesion:
    Hàm này tạo hóa đơn dựa trên thông tin từ đơn đặt hàng. Chức năng này liên quan chặt chẽ đến việc tạo hóa đơn từ đơn đặt hàng.
    Hàm **processDeliveryInfo(HashMap info)**:
    - Functional Cohesion:
    Hàm này xử lý thông tin giao hàng, nhưng gọi một hàm khác validateDeliveryInfo(HashMap<String, String> info) để thực hiện việc kiểm tra.
    Hàm **validatePhoneNumber(String phoneNumber)** và **validateContainLetterAndNoEmpty(String name)** và **validateAddressPlaceRushOrder(String province, String address)** và **validateMediaPlaceRushorder()**:
    - Functional Cohesion:
    Hàm **getProductAvailablePlaceRush(Order order)**:
    - Functional Cohesion:
    Hàm này tạo hóa đơn dựa trên thông tin từ đơn đặt hàng. Chức năng này liên quan chặt chẽ đến việc tạo hóa đơn từ đơn đặt hàng.
    - Functional Cohesion:

    **File PlaceRushOrderController:**

    Hàm **validatePlaceRushOrderData(Shipment deliveryData)**:
    - Coincidental Cohesion:
    Hàm này có mục đích kiểm tra dữ liệu của đơn hàng đặt gấp. Chức năng này liên quan chặt chẽ đến việc xác thực dữ liệu của đơn hàng đặt gấp.

    **File ViewCartController:**

    Hàm **checkAvailabilityOfProduct()**:
    - Functional  Cohesion:
    Hàm này có mục đích kiểm tra sự có sẵn của sản phẩm trong giỏ hàng. Chức năng này liên quan chặt chẽ đến việc kiểm tra sản phẩm trong giỏ hàng.

</details>
<details>
<summary>Nguyễn Thị Hồng Vân</summary>
<br>
  
- Assigned tasks:
- Task: Bổ sung Cohesion cho package package Views.Screen.Home/Payment/Shipping
 - Pull Request: 
  - Specific implementation details:

    **File HomeScreenHandler:**
    Hàm **initialize**:
    - Control Cohesion:
    Phương thức này có control cohesion vì nó chịu trách nhiệm thiết lập controller và tạo danh sách các mục trên màn hình chủ.

    Hàm **setImage**:
    - Data Cohesion:
    Phương thức này có data cohesion vì nó chỉ liên quan đến việc đặt hình ảnh cho Logo và Icon giỏ hàng.

    Hàm **addMediaHome**:
    - Data Cohesion:
    Phương thức này có data cohesion vì nó chỉ thêm các mục truyền vào vào màn hình chủ.

    Hàm **addMenuItem**:
    - Data Cohesion:
    Phương thức này có data cohesion vì nó chỉ thêm mục menu vào **splitMenuBtnSearch** dựa trên tham số được cung cấp.


    **File PaymentScreenHandler**
    Hàm **displayWebView**:
    - Control Cohesion:
    Phương thức này có control cohesion vì nó liên quan chặt chẽ đến việc hiển thị WebView và xử lý sự kiện khi URL thay đổi.

    Hàm **parseQueryString**:
    - Functional Cohesion:
    Phương thức này có functional cohesion vì nó chỉ thực hiện một nhiệm vụ cụ thể: chuyển đổi query string thành một đối tượng Map<String, String>.

    Hàm **handleUrlChanged**:
    - Control Cohesion:
    Phương thức này có control cohesion vì nó chịu trách nhiệm xử lý sự kiện khi URL thay đổi và chuyển đổi query thành Map.

    Hàm **payOrder**:
    - Control Cohesion:
    Phương thức này có control cohesion vì nó chịu trách nhiệm xử lý thanh toán và hiển thị màn hình kết quả.

    **File ShippingScreenHandler:**
    Hàm **initialize**:
    - Data Cohesion:
    Phương thức này có data cohesion vì nó chỉ liên quan đến việc thiết lập trạng thái ban đầu của giao diện người dùng.
    Hàm **submitDeliveryInfo**:

    - Control Cohesion:
    Phương thức này có control cohesion vì nó chịu trách nhiệm xử lý sự kiện khi người dùng nhấn nút "submitDeliveryInfo" và liên quan chặt chẽ đến việc kiểm tra và xử lý thông tin giao hàng.

    Hàm **getBController**:
    - Data Cohesion:
    Phương thức này có data cohesion vì nó chỉ trả về một đối tượng **PlaceOrderController** (controller cho việc đặt hàng)

</details>
</details>

<details>
  <summary>W15: 11/12/2023~17/12/2023 </summary>
<br>
<details>
<summary>Nguyễn Thế Vũ</summary>

-   Assigned tasks:
-   Task: Đánh giá SOLID cho package subSystem, thiết kế cancelOrder
-   Pull Request:
-   Specific implementation details:
    -   Đánh giá subSystem: Khó mở rộng nếu cần triển khai thêm phương thức thanh toán không cần URL
    -   Thiết kế thêm entity RefundTransaction
    -   Thiết kế lại database cho các lớp order, paymentTransaction, thêm lớp refundTransaction
    -   Thiết kế trang xem danh sách đơn hàng
    -   Kết nối vnPay, gọi được use-case refund của vnPay

</details>

<details>
<summary>Nguyễn Hữu Việt</summary>

-   Assigned tasks:
-   Task: Đánh giá SOLID cho package controller
-   Pull Request:
-   Specific implementation details:
    -   Đánh giá package controller:
	-   Tuân thủ một số nguyên tắc trong SOLID, (chủ yếu là SRP)
	-   Có thể cải thiện bằng cách tách nhỏ một số function, do có một số function đang hoạt động quá nhiều chức năng
	-   Một số lớp phụ thuộc quá nhiều vào lớp bên ngoài, cần giảm bớt sự phụ thuộc


</details>

<details>
<summary>Nguyễn Thị Hồng Vân</summary>

-   Assigned tasks:
-   Task: Đánh giá SOLID cho package Views Home/Payment/Shipping
-   Pull Request:
-   Specific implementation details:
    -   Đánh giá package Home:
	- Một số phương thức trong HomeScreenHandler có thể được chia nhỏ thành các phương thức nhỏ hơn để giảm độ phức tạp và tăng độ rõ ràng.
	- Có thể giảm sự phụ thuộc bằng cách áp dụng Dependency Injection và giảm kết nối chặt chẽ giữa các lớp.
    -   Đánh giá package Payment:
    	- Có thể cải thiện: Phương thức displayWebView và handleUrlChanged có thể được chia nhỏ thành các phương thức nhỏ hơn để tăng độ rõ ràng.
	- Có thể giảm sự phụ thuộc bằng cách áp dụng Dependency Injection.
    -   Đánh giá package Shipping:
   	- Phương thức submitDeliveryInfo có thể được chia nhỏ để giảm độ phức tạp và tăng tính tái sử dụng.
	- Có thể giảm sự phụ thuộc bằng cách áp dụng Dependency Injection và giảm kết nối chặt chẽ giữa các lớp.
</details>
</details>

