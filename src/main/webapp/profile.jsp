<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />

<!-- Breadcrumb Begin -->
<div class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__links">
                    <a href="index.jsp"><i class="fa fa-home"></i> TRANG CHỦ</a>
                    <span>HỒ SƠ CÁ NHÂN</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Breadcrumb End -->

<!-- User Profile Section Begin -->
<section class="profile spad">
    <div class="profile-container">
        <!-- Cột trái: Thông tin ngắn -->
        <div class="profile-left">
            <div class="avatar-wrapper">
                <img id="profileImagePreview" src="${pageContext.request.contextPath}/${user.profilePicture != null ? user.profilePicture : 'assets/img/default-avatar.png'}" alt="Ảnh đại diện"/>
            </div>
            <h3>${user != null ? user.name : ''}</h3>
            <p><strong>Tên đăng nhập:</strong> ${user != null ? user.userName : ''}</p>
            <p><strong>Email:</strong> ${user != null ? user.email : ''}</p>
            <p><strong>Số điện thoại:</strong> ${user != null ? user.phoneNumber : ''}</p>
            <p><strong>Địa chỉ chi tiết:</strong>
                ${address != null && address.street != null ? address.street : ''}
                ${address != null && address.wardOrCommune != null && !address.wardOrCommune.isEmpty() ? ', '.concat(address.wardOrCommune) : ''}
                ${address != null && address.district != null && !address.district.isEmpty() ? ', '.concat(address.district) : ''}
                ${address != null && address.provinceOrCity != null && !address.provinceOrCity.isEmpty() ? ', '.concat(address.provinceOrCity) : ''}
            </p>
        </div>

        <!-- Cột phải: Form cập nhật -->
        <div class="profile-right">
            <h2>Cập Nhật Thông Tin</h2>
            <form action="${pageContext.request.contextPath}/UpdateProfileController" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="name">Tên</label>
                    <input type="text" id="name" name="name" value="${user != null ? user.name : ''}" placeholder="Nhập họ và tên" required maxlength="100" />
                </div>
                <div class="form-group">
                    <label for="username">Tên đăng nhập</label>
                    <input type="text" id="username" name="username" value="${user != null ? user.userName : ''}" placeholder="Tên đăng nhập" required maxlength="50" />
                </div>
                <div class="form-group">
                    <label>Email (không thể thay đổi)</label>
                    <p style="padding: 10px; background: #eee; border-radius: 6px; color: #555;">${user != null ? user.email : ''}</p>
                </div>
                <div class="form-group">
                    <label for="phone">Số điện thoại</label>
                    <input type="text" id="phone" name="phone" value="${user != null ? user.phoneNumber : ''}" placeholder="Nhập số điện thoại" maxlength="15" />
                </div>
                <div class="form-group">
                    <label for="profile_picture">Tải ảnh đại diện</label>
                    <label class="custom-file-upload">
                        <input type ="file" id="profile_picture" name="profile_picture" accept="image/*" />
                    </label>
                </div>


                <h3 class="form-section-title">Thông tin địa chỉ</h3>
                <div class="form-group">
                    <label for="street">Đường</label>
                    <input type="text" id="street" name="street" value="${address != null ? address.street : ''}" placeholder="Tên đường" maxlength="100" />
                </div>
                <div class="form-group">
                    <label for="wardOrCommune">Phường/Xã</label>
                    <input type="text" id="wardOrCommune" name="wardOrCommune" value="${address != null ? address.wardOrCommune : ''}" placeholder="Phường hoặc xã" maxlength="100" />
                </div>
                <div class="form-group">
                    <label for="district">Quận/Huyện</label>
                    <input type="text" id="district" name="district" value="${address != null ? address.district : ''}" placeholder="Quận hoặc huyện" maxlength="100" />
                </div>
                <div class="form-group">
                    <label for="provinceOrCity">Tỉnh/Thành phố</label>
                    <input type="text" id="provinceOrCity" name="provinceOrCity" value="${address != null ? address.provinceOrCity : ''}" placeholder="Tỉnh hoặc thành phố" maxlength="100" />
                </div>
                <div class="form-group">
                    <label for="fullnameReceiver">Người nhận</label>
                    <input type="text" id="fullnameReceiver" name="fullnameReceiver" value="${userAddress != null ? userAddress.fullnameReceiver : ''}" placeholder="Tên người nhận" maxlength="100" />
                </div>
                <div class="form-group">
                    <label for="phoneReceiver">Số điện thoại người nhận</label>
                    <input type="text" id="phoneReceiver" name="phoneReceiver" value="${userAddress != null ? userAddress.phoneReceiver : ''}" placeholder="Số điện thoại người nhận" maxlength="15" />
                </div>
                <div class="form-group">
                    <label for="addressType">Loại địa chỉ</label>
                    <input type="text" id="addressType" name="addressType" value="${userAddress != null ? userAddress.addressType : ''}" placeholder="Loại địa chỉ" maxlength="50" />
                </div>

                <div class="btn-submit-wrapper">
                    <button class="btn-submit" type="submit">Lưu Thay Đổi</button>
                    <button type="button" class="btn-cancel" onclick="window.history.back()">Hủy</button>
                </div>
            </form>
        </div>
    </div>
</section>
<!-- User Profile Section End -->

<jsp:include page="footer.jsp" />

<script>
    const fileInput = document.getElementById('profile_picture');
    const previewImageLeft = document.getElementById('profileImagePreview');
    const fileNameLabel = document.getElementById('file-name-preview');

    fileInput.addEventListener('change', function () {
        const file = this.files[0];
        if (file) {
            if (fileNameLabel) fileNameLabel.textContent = file.name;

            const reader = new FileReader();
            reader.onload = function (e) {
                if (previewImageLeft) previewImageLeft.src = e.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            if (fileNameLabel) fileNameLabel.textContent = "";
        }
    });
</script>





<style>
    .profile-container {
        display: flex;
        background: #fff;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0,0,0,0.05);
        padding: 15px;
        gap: 40px;
        max-width: 1000px;
        margin: 0 auto;
        flex-wrap: wrap;
    }

    .profile-left {
        width: 30%;
        background-color: #fff4f4;
        border-radius: 8px;
        text-align: center;
        padding: 30px 15px;
        border: 1px solid #eee;
        box-sizing: border-box;
    }

    .avatar-wrapper {
        width: 120px;
        height: 120px;
        margin: 0 auto 15px;
        border-radius: 50%;
        overflow: hidden;
        border: 3px solid #ca1515;
    }

    .avatar-wrapper img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .profile-left h3 {
        margin-top: 0;
        color: #333;
    }

    .profile-left p {
        color: #555;
        margin: 6px 0;
        font-size: 15px;
    }

    .profile-right {
        flex: 1;
        min-width: 320px;
    }

    .profile-right h2 {
        color: #ca1515;
        margin-bottom: 15px;
        font-size: 22px;
    }

    .form-group {
        margin-bottom: 15px;
    }

    .form-group label {
        display: block;
        margin-bottom: 6px;
        font-weight: 600;
        color: #444;
    }

    .form-group input[type="text"],
    .form-group input[type="email"],
    .form-group input[type="password"],
    .form-group input[type="file"] {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 6px;
        font-size: 15px;
    }

    .btn-submit-wrapper {
        text-align: center;
        margin-top: 20px;
    }

    .btn-submit {
        background-color: #ca1515;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 6px;
        font-size: 16px;
        cursor: pointer;
        margin-right: 10px;
    }

    .btn-submit:hover {
        background-color: #b31313;
    }

    .btn-cancel {
        background-color: #777;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 6px;
        font-size: 16px;
        cursor: pointer;
    }

    .btn-cancel:hover {
        background-color: #555;
    }

    .form-section-title {
        font-weight: 700;
        font-size: 18px;
        margin-top: 25px;
        margin-bottom: 10px;
        border-bottom: 2px solid #ca1515;
        padding-bottom: 5px;
        color: #ca1515;
    }

    input::placeholder {
        color: #bbb;
        font-style: italic;
    }

    @media (max-width: 768px) {
        .profile-container {
            flex-direction: column;
            gap: 20px;
        }

        .profile-left, .profile-right {
            width: 100%;
        }
    }


</style>
