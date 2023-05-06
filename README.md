# team-project-n2_1_1

## Ideal for Project
### Xử lý đơn hàng online
### Sử dụng mô hình REST service
Tự động hóa quy trình nghiệp vụ: Xử lí đơn hàng bao gồm:
+ tính giá cả 
+ xác nhận đơn hàng 
+ thanh toán
+ thông báo trạng thái
+ lập hóa đơn


![QuyTrinhXuLyDonHang](https://user-images.githubusercontent.com/105064305/236595637-5f6a4177-926c-47ca-839c-7b752384a7d2.png)

---
## Phân tích và Mô hình hóa dịch vụ
### 1. Phân tách quy trình thành các hành động chi tiết:
- Kiểm tra Item trong giỏ hàng
- Không có item trong giỏ hàng, kết thúc
- Nếu có item trong giỏ hàng, tính tổng giá các item
- Khách hàng click thanh toán
- Khách hàng chọn phương thức thanh toán
- Nếu chọn Thanh toán online, hệ thống sử dụng dịch vụ thanh toán online bên thứ 3
- Nếu thanh toán online thành công, hệ thống thông báo thành công và hệ thống đi xác nhận đơn hàng
- Nếu thanh toán online thất bại, hệ thống thông báo thất bại và quay lại bước 'Khách hàng chọn phương thức thanh toán'
- Nếu chọn thanh toán offline, hệ thống chuyển sang xác nhận đơn hàng
- Thông báo tạo đơn hàng thành công
- Sử dụng dịch vụ theo dõi đơn hàng
- Hệ thống lập hóa đơn 

### 2. Sau khi loại bỏ các hành động không phù hợp, ta còn các hành động
- Không có item trong giỏ hàng, kết thúc.
- Nếu có item trong giỏ hàng, tính tổng giá các item
- Nếu chọn Thanh toán online, hệ thống sử dụng dịch vụ thanh toán online bên thứ 3 
- Nếu thanh toán online thành công, hệ thống thông báo thành công và hệ thống đi xác nhận đơn hàng
- Nếu thanh toán online thất bại, hệ thống thông báo thất bại và quay lại bước 'Khách hàng chọn phương thức thanh toán'
- Nếu chọn thanh toán offline, hệ thống chuyển sang xác nhận đơn hàng
- Thông báo tạo đơn hàng thành công 
- Sử dụng dịch vụ theo dõi đơn hàng
- Hệ thống lập hóa đơn

### 3. Xác định ứng viên Dịch vụ thực thể
Xem xét các hành động còn lại từ Bước 2, ta xác định và phân loại những hành động được coi là bất khả tri (được in đậm):
- Không có item trong giỏ hàng, kết thúc
- __Nếu có item trong giỏ hàng, tính tổng giá các item__
- __Nếu chọn Thanh toán online, hệ thống sử dụng dịch vụ thanh toán online bên thứ 3__
- Nếu thanh toán online thành công, hệ thống thông báo thành công và hệ thống đi xác nhận đơn hàng
- Nếu thanh toán online thất bại, hệ thống thông báo thất bại và quay lại bước 'Khách hàng chọn phương thức thanh toán'
- __Nếu chọn thanh toán offline, hệ thống chuyển sang xác nhận đơn hàng__
- Thông báo tạo đơn hàng thành công
- __Sử dụng dịch vụ theo dõi đơn hàng__
- __Hệ thống lập hóa đơn__

#### __Các ứng viên Dịch vụ thực thể được xác định__

- Ứng viên __CartItem__<br />
    ![C7_Hinh3_1](https://user-images.githubusercontent.com/105064305/236595670-9ef6b51a-cda9-413b-9247-1b43b219024a.png)

    - Hành động 'Nếu có item trong giỏ hàng, tính tổng giá các item' thiết lập ứng viên 'Get Item'

&nbsp;
- Ứng viên __Cart__<br />
    ![C7_Hinh3_2](https://user-images.githubusercontent.com/105064305/236595765-6fc56771-9930-40a9-9dd5-99e1e461103d.png)
    - Hành động 'Nếu có item trong giỏ hàng, tính tổng giá các item' thiết lập ứng viên 
        + 'Get CartItems'
        + 'Get Total Money'

&nbsp;
- Ứng viên __OrderItem__<br />
    ![C7_Hinh3_8](https://user-images.githubusercontent.com/105064305/236595783-c81f2c85-c186-428b-86ee-f6e15d1c7793.png)
    - Hành động 'Nếu thanh toán online thành công,...', 'Thông báo tạo đơn hàng thành công' thiết lập ứng viên 'Create OrderItem'

&nbsp;
- Ứng viên __Order__<br />
    ![C7_Hinh3_3](https://user-images.githubusercontent.com/105064305/236595796-7532fafc-113e-4543-a2b2-f79fe874ff6f.png)
    - Hành động 'Nếu thanh toán online thành công,...', 'Thông báo tạo đơn hàng thành công' thiết lập ứng viên 'Create Order'

&nbsp;
- Ứng viên __Bill__<br />
    ![C7_Hinh3_4](https://user-images.githubusercontent.com/105064305/236595809-e5e092a9-6f5c-4758-854f-17526d121714.png)
    - Hành động 'Nếu chọn Thanh toán online,...' thiết lập ứng viên 'Get Status'
    
### 4. Xác định logic cụ thể của quy trình
Những hành động được coi là không bất khả tri (được in đậm):
- __Không có item trong giỏ hàng, kết thúc__
- Nếu có item trong giỏ hàng, tính tổng giá các item
- Nếu chọn Thanh toán online, hệ thống sử dụng dịch vụ thanh toán online bên thứ 3
- Nếu thanh toán online thành công, hệ thống thông báo thành công và hệ thống đi xác nhận đơn hàng
- Nếu thanh toán online thất bại, hệ thống thông báo thất bại và quay lại bước 'Khách hàng chọn phương thức thanh toán'
- Nếu chọn thanh toán offline, hệ thống chuyển sang xác nhận đơn hàng
- __Thông báo tạo đơn hàng thành công__
- Sử dụng dịch vụ theo dõi đơn hàng
- Hệ thống lập hóa đơn
    
&nbsp;
- Ứng viên __OrderProcessing__<br />
    ![C7_Hinh3_7](https://user-images.githubusercontent.com/105064305/236595821-06231529-0a45-4a6a-9d17-20b0c30ac16b.png)
    - Khả năng 'Start' sẽ gọi bởi một chương trình bên thứ ba, 'Start' hoạt động như một chức năng khởi tạo dịch vụ Xử lý đơn hàng


### 5. Xác định tài nguyên
Một số tài nguyên được xác định:

    /CartItem/
    /Cart/
    /Bill/
    /OrderItem/
    /Order/

Các nguồn và các thực thể tương ứng

    /CartItem/      |     CartItem
    /Cart/          |     Cart
    /Bill/          |     Bill
    /OrderItem/     |     OrderItem
    /Order/         |     Order

### 6. Liên kết các dịch vụ với tài nguyên tương ứng
- __OrderProcessing Service__<br />
    <img src="./images/C7_Hinh6_7.png" style="width: 12rem">

&nbsp;
- __Item Service__<br />
    ![C7_Hinh6_7](https://user-images.githubusercontent.com/105064305/236595826-9a734092-da63-46f2-bb6a-b2d400259a94.png)

&nbsp;
- __Cart Service__<br />
    ![C7_Hinh6_2](https://user-images.githubusercontent.com/105064305/236595838-e42e86e6-c711-4ac7-a7a6-11778c313d4b.png)

&nbsp;
- __Order Service__<br />
    ![C7_Hinh6_3](https://user-images.githubusercontent.com/105064305/236595841-8ccf5c1f-0637-4afb-8b0f-b6ee62466377.png)

&nbsp;
- __Bill Service__<br />
    ![C7_Hinh6_4](https://user-images.githubusercontent.com/105064305/236595851-77d8afaf-3b1c-414c-903a-19a6cbed31ca.png)

### 7. Áp dụng Hướng dịch vụ

### 8. Xác định các ứng viên Tổ hợp dịch vụ
![C7_Hinh8_1](https://user-images.githubusercontent.com/105064305/236595889-a8bf4b51-77a2-442e-b708-f14685a25f38.png)

### 9. Phân tích yêu cầu xử lý
Không có yêu cầu xử lý tập trung vào tiện ích được xác định

### 10. Xác định ứng viên dịch vụ tiện ích
- __Notification Service__ <br />
    + Ứng viên <br />
        ![C7_Hinh10_1](https://user-images.githubusercontent.com/105064305/236595949-27fba8f6-8557-405e-b074-fc79b9846761.png)
    + Liên kết ứng viên với tài nguyên <br />
        ![C7_Hinh10_2](https://user-images.githubusercontent.com/105064305/236595983-e0a4decf-88e4-4749-a1d3-13f35280a310.png)

### 11. Xác định ứng viên Vi dịch vụ 
- Ứng viên __Payment__<br />
    ![C7_Hinh3_5](https://user-images.githubusercontent.com/105064305/236595988-35df29bf-041f-43bf-9bd4-c78f7a5ca229.png)
    - Khả năng 'Pay' sẽ gọi bởi một chương trình bên thứ 3 để tiến hành thanh toán

&nbsp; 
- __Payment Service__<br />
    ![C7_Hinh6_5](https://user-images.githubusercontent.com/105064305/236595992-93b9eaeb-75df-4775-8749-cefdef29e1e6.png)

&nbsp;
- Ứng viên __OrderStatus__<br />
    ![C7_Hinh3_6](https://user-images.githubusercontent.com/105064305/236595997-020917a2-39ea-49ed-8da9-8a69b0112c34.png)
    - Hành động 'Sử dụng dịch vụ theo dõi đơn hàng' thiết lập ứng viên 'Get Status'

&nbsp;
- __OrderStatus Service__<br />
    ![C7_Hinh6_6](https://user-images.githubusercontent.com/105064305/236596005-41438d93-9f28-4629-b62d-2ae3a59ebab9.png)

### 12. Áp dụng Hướng dịch vụ

### 13. Sửa đổi ứng viên Tổ hợp dịch vụ
![C7_Hinh13](https://user-images.githubusercontent.com/105064305/236596015-51fb4ed7-71af-480e-abbe-559ec54f1810.png)

&nbsp;
## API dịch vụ và Thiết kế Hợp đồng dịch vụ
---
### 1. Entity Service Design

__CartItem Service Contract__ <br />
    ![C9_Hinh1_1](https://user-images.githubusercontent.com/105064305/236596030-c1c21c23-baf7-4933-9348-c4df3535c52b.png)


&nbsp;
__Cart Service Contract__<br />
    ![C9_Hinh1_2](https://user-images.githubusercontent.com/105064305/236596036-050c8c16-eb6b-4bbb-93a3-c340ae9904df.png)


&nbsp;
__OrderItem Service Contract__<br />
    ![C9_Hinh1_8](https://user-images.githubusercontent.com/105064305/236596045-776e1f1f-89d4-4c97-a8af-4bd9d706cbcf.png)

&nbsp;
__Order Service Contract__<br />
    ![C9_Hinh1_3](https://user-images.githubusercontent.com/105064305/236596050-c908c0c3-564a-4b62-b304-8b812cc4f11c.png)

&nbsp;
__Bill Service Contract__<br />
    ![C9_Hinh1_4](https://user-images.githubusercontent.com/105064305/236596056-03951e34-1363-488a-8a19-74c077246ee2.png)

__OrderStatus Service Contract__<br />
![image](https://user-images.githubusercontent.com/90273323/231349267-efcf9a6a-e038-4cff-887b-7894ae8e8bff.png)<br />

&nbsp;
### 2. Utility Service Design
&nbsp;
__Notification Service Contract__<br />
    ![C9_Hinh1_10](https://user-images.githubusercontent.com/105064305/236596062-3f9705e7-9a35-4d38-8755-67c4f778ad00.png)

&nbsp;
### 3. Microservice Design

&nbsp;
__Payment Service Contract__ <br />
    ![C9_Hinh1_5](https://user-images.githubusercontent.com/105064305/236596070-70c1229e-a930-460f-b69b-7c77297a9add.png)

Payment Service hỗ trợ việc thanh toán của Khách hàng bằng cách sử dụng API bên thứ 3. Sau khi lấy được thông tin của Khách hàng sẽ, thông tin sẽ được mã hóa và chuyển tiếp tới bên thứ 3 để tiến hành giao dịch. <br />
Phương thức DELETE chỉ có thể được sử dụng bởi Khách hàng trong trường hợp Khách hàng muốn hủy giao dịch.


&nbsp;
__OrderStatus Service Contract__ <br />
    ![C9_Hinh1_6](https://user-images.githubusercontent.com/105064305/236596074-99e29a87-6708-4988-bc0a-ea1b1a155645.png)
OrderStatus Service hỗ trợ việc theo dõi đơn hàng của Khách hàng bằng cách sử dụng API bên thứ 3

&nbsp;
### 4. Task Service Design

__OrderProcessing Service Contract__ <br />
    ![C9_Hinh1_7](https://user-images.githubusercontent.com/105064305/236596078-129934bd-07d4-4c22-b5a8-73cc9cb1fb1f.png)

&nbsp;
## Môi trường phát triển
__1. Phiên bản:__<br />
    + [python version 3.9.6](https://www.python.org/downloads/release/python-396/)<br />
    + Django: 4.1.7<br />
    + Django Rest Framewwork 3.14.0<br />

__Các thành phần của Django:__ <br />
__a. Models:__ Models là một thành phần quan trọng trong Django và đóng vai trò là đại diện cho các đối tượng trong cơ sở dữ liệu. Trong mô hình SOA, models có thể được sử dụng để định nghĩa các dữ liệu cần thiết cho các dịch vụ web hoặc để lưu trữ các thông tin liên quan đến các dịch vụ web.<br />

__b. Views:__ Views là thành phần xử lý logic trong Django và thường được sử dụng để tạo ra các trang web hoặc API cho các dịch vụ web. Trong mô hình SOA, views có thể được sử dụng để triển khai các dịch vụ web và xử lý các yêu cầu từ các dịch vụ web khác.<br />

__c. URLs:__ URLs trong Django được sử dụng để xác định các đường dẫn đến các views. Trong mô hình SOA, URLs có thể được sử dụng để xác định các đường dẫn đến các dịch vụ web và cung cấp các API endpoint cho các dịch vụ web khác.<br />

__d. Middleware:__ Middleware là một thành phần của Django được sử dụng để xử lý các yêu cầu trước khi chúng được gửi đến views. Trong mô hình SOA, middleware có thể được sử dụng để xử lý các yêu cầu từ các dịch vụ web khác và thực hiện các chức năng như xác thực người dùng, xử lý lỗi, hoặc giám sát.<br />

__Các thành phần của Django Rest Framework:__ <br />
__a. Serializer__: Serializer là một thành phần quan trọng của Django Rest Framework, được sử dụng để chuyển đổi các đối tượng Python sang định dạng dữ liệu có thể được truyền qua mạng, chẳng hạn như JSON hoặc XML. Nó cũng có thể được sử dụng để chuyển đổi định dạng dữ liệu đầu vào từ yêu cầu HTTP của khách hàng sang đối tượng Python.<br />

__b. View:__ View là một thành phần quan trọng khác của Django Rest Framework, được sử dụng để xử lý các yêu cầu HTTP đến từ khách hàng và trả về các phản hồi HTTP. Nó cung cấp các chức năng xử lý yêu cầu HTTP như GET, POST, PUT, DELETE, PATCH và OPTIONS<br />

__2. Các thành phần của quy trình nghiệp vụ__<br />
![image](https://user-images.githubusercontent.com/90273323/234453286-e4b92217-53ab-4a6d-a624-09aad8e0b57e.png)<br />

- Khi người dùng chọn 1 Item vào giỏ hàng thì Cart service sẽ tạo ra 1 CartItem
- Khi người dùng đặt hàng thì Order service sẽ lấy thông tin từ CartItem để tạo ra 1 Order, trong đó là các OrderItem được chọn 
- Khi người dùng thanh toán thì OrderProcessing service sẽ cho người dùng lựa chọn:
	+ nếu thanh toán online gọi API bên thứ 3 để giúp người dùng thanh toán 
	sau đó gọi tới Payment service để check xem người dùng đã thanh toán thành công 
	sau đó gọi tới Bill service để tạo hóa đơn 
	+ thanh toán offline: sẽ gọi tới Bill để tạo hóa đơn  
- OrderStatus service sẽ gọi tới Bill service để lấy thông tin, trạng thái về đơn hàng sau đó gửi và cập nhật thông tin đơn hàng tương ứng cho người dùng.


__3. Cách chạy project:__<br />
    - Tạo môi trường ảo: ```> python -m venv venv```<br />
    - Kích hoạt môi trường ảo: ```> venv/Scripts/activate```<br />
    - import thư viện cần thiết: <br />
        ```> pip install -r requirements.txt```<br />
    - Chạy chương trình: ```> py manage.py runserver```<br />
