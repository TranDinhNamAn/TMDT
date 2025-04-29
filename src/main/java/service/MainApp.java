package service;

import Model.User;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        UserService userService = new UserService();

        // Lấy danh sách người dùng
        List<User> users = userService.getAllUsers();
        users.forEach(user -> {
            System.out.println("ID: " + user.getUserID());
            System.out.println("Username: " + user.getUserName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("------");
        });
    }
}