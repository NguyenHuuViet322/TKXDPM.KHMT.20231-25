# TKXDPM.KHMT.20231-25

This is a Capstone's source code for Software Design and Construction project

## Team member

| Name                   | Role        |
| :----------------------| :---------- |
| Nguyễn Hữu Việt        | Team Leader |
| Chu Văn Việt           | Member      |
| Nguyễn Thị Hồng Vân    | Member      |
| Ninh Thành Vinh        | Member      |
| Nguyễn Thế Vũ          | Member      |

## Report Content

<details>
  <summary>W13: 27/11/2023~03/12/2023 </summary>
<br>

<details>
<summary>Nguyễn Hữu Việt</summary>
<br>
- Assigned tasks:
  - Task 1: Bổ sung Coupling cho package controller
- Implementation details:
  - Pull Request: 
  - Specific implementation details:
    - Phương thức **checkMediaInCart(Media media)** thuộc **Control Coupling** do phục thuộc vào hàm **checkMediaInCart** của đối tượng Cart.
    - Phương thức **makePayment(...)** thuộc **Control Coupling** do phụ thuộc vào cấu trúc nội bộ của **VnPaySubSystem** và kiểm soát luồng của đối tượng này thông qua **save** và **makePaymentTransaction**
    - Phương thức **getUrlPay** thuộc **Data Coupling** do sử dụng và gọi phương thức **generatePayUrl()** của  **vnPayService**
    - Phương thức **emptyCart()** thuộc **Control Coupling** do phương thức này thay đổi phần dữ liệu của Cart.
    - Phương thức **createOrder()** thuộc **Control Coupling** do phương thức này ảnh hưởng đến luồng và trạng thái của đối tượng **order**
    - Phương thức **createInvoice()** tương tự **createOrder()**
    - Các phương thức **validate...** thuộc **Control Coupling** do bên trong tồn tại các đoạn code phụ thuộc lẫn nhau và phụ thuộc vào tham số truyền vào
    - Phương thức **validatePlaceRushOrderData** thuộc **Common Coupling** do truy cập vào biến global trong util.
    - Một số phương thức không có gì truyền hoặc không thực hiện điều gì thuộc là **không xác định Coupling**
</details>

<details>
<summary>Member 2</summary>
<br>
- Assigned tasks:
 
- Implementation details:
  
</details>
=======
</details>

<details>
<summary>Chu Văn Việt</summary>
<br>
- Assigned tasks:
  - Task 1: 
- Implementation details:
</details>

<details>
<summary>Nguyễn Thị Hồng Vân</summary>
<br>
- Assigned tasks:
  - Task 1: 
- Implementation details:
</details>

<details>
<summary>Ninh Thành Vinh</summary>
<br>
- Assigned tasks:
  - Task 1: 
- Implementation details:
</details>

<details>
<summary>Nguyễn Thế Vũ</summary>
<br>
- Assigned tasks:
  - Task 1: 
- Implementation details:
</details>
</details>

