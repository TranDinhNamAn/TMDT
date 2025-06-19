package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import Model.User;
import Model.Address;
import Model.UserAddress;
import service.UserService;
import service.AddressService;
import service.UserAddressService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/UpdateProfileController")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class UpdateProfileController extends HttpServlet {
    private UserService userService;
    private AddressService addressService;
    private UserAddressService userAddressService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        addressService = new AddressService();
        userAddressService = new UserAddressService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
            return;
        }

        Integer userId = (Integer) session.getAttribute("userId");
        User user = userService.getUserById(userId);
        session.setAttribute("user", user);

        UserAddress userAddress = userAddressService.getUserAddressByUserId(userId);
        Address address = null;

        if (userAddress != null && userAddress.getAddressID() > 0) {
            address = addressService.getAddressById(userAddress.getAddressID());
        }

        request.setAttribute("user", user);
        request.setAttribute("address", address);
        request.setAttribute("userAddress", userAddress);

        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
            return;
        }

        Integer userId = (Integer) session.getAttribute("userId");
        User user = userService.getUserById(userId);
        if (user == null) {
            request.setAttribute("message", "Người dùng không tồn tại");
            request.getRequestDispatcher("/profile.jsp").forward(request, response);
            return;
        }

        String name = request.getParameter("name");
        String userName = request.getParameter("username");
        String phoneNumber = request.getParameter("phone");

        // Xử lý upload ảnh
        Part filePart = request.getPart("profile_picture");
        String profilePicture = user.getProfilePicture();
        if (filePart != null && filePart.getSize() > 0 && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName();
            String uploadPath = getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            String fullSavePath = uploadPath + File.separator + fileName;
            filePart.write(fullSavePath);
            profilePicture = "uploads/" + fileName;
        }

        String street = request.getParameter("street");
        String wardOrCommune = request.getParameter("wardOrCommune");
        String district = request.getParameter("district");
        String provinceOrCity = request.getParameter("provinceOrCity");
        String fullnameReceiver = request.getParameter("fullnameReceiver");
        String phoneReceiver = request.getParameter("phoneReceiver");
        String addressType = request.getParameter("addressType");

        boolean isUserChanged =
                (name != null && !name.equals(user.getName() != null ? user.getName() : "")) ||
                        (userName != null && !userName.equals(user.getUserName() != null ? user.getUserName() : "")) ||
                        (phoneNumber != null && !phoneNumber.equals(user.getPhoneNumber() != null ? user.getPhoneNumber() : "")) ||
                        (profilePicture != null && !profilePicture.equals(user.getProfilePicture() != null ? user.getProfilePicture() : ""));

        boolean userUpdated = false;
        if (isUserChanged) {
            user.setName(name);
            user.setUserName(userName);
            user.setPhoneNumber(phoneNumber);
            user.setProfilePicture(profilePicture);
            userUpdated = userService.updateUser(user);
        }

        UserAddress userAddress = userAddressService.getUserAddressByUserId(userId);
        Address address = null;

        if (userAddress == null) {
            userAddress = new UserAddress();
            userAddress.setUserID(userId);
            userAddress.setIsPrimary(true);
            String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            userAddress.setCreateDate(currentDate);
            userAddress.setLastUpdateDate(currentDate);
        } else {
            address = addressService.getAddressById(userAddress.getAddressID());
        }

        if (address == null) {
            address = new Address();
            address.setStreet(street);
            address.setWardOrCommune(wardOrCommune);
            address.setDistrict(district);
            address.setProvinceOrCity(provinceOrCity);
            addressService.saveOrUpdateAddress(address);
            userAddress.setAddressID(address.getAddressID());
        } else {
            address.setStreet(street);
            address.setWardOrCommune(wardOrCommune);
            address.setDistrict(district);
            address.setProvinceOrCity(provinceOrCity);
            addressService.saveOrUpdateAddress(address);
            userAddress.setAddressID(address.getAddressID());
        }

        userAddress.setFullnameReceiver(fullnameReceiver);
        userAddress.setPhoneReceiver(phoneReceiver);
        userAddress.setAddressType(addressType);
        userAddress.setLastUpdateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        boolean userAddressUpdated = userAddressService.saveOrUpdateUserAddress(userAddress);

        if (userUpdated || userAddressUpdated) {
            request.setAttribute("message", "Cập nhật thành công!");
        } else {
            request.setAttribute("message", "Không có thay đổi nào được lưu.");
        }

        User updatedUser = userService.getUserById(userId);
        Address updatedAddress = addressService.getAddressById(userAddress.getAddressID());

        session.setAttribute("user", updatedUser);
        request.setAttribute("user", updatedUser);
        request.setAttribute("address", updatedAddress);
        request.setAttribute("userAddress", userAddress);
        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }
}
