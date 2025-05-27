package service;

import DAO.UserAddressDAO;
import Model.UserAddress;

public class UserAddressService {
    private final UserAddressDAO userAddressDAO = new UserAddressDAO();

    public UserAddress getUserAddressByUserId(int userId) {
        return userAddressDAO.getUserAddressByUserId(userId);
    }

    public boolean saveOrUpdateUserAddress(UserAddress userAddress) {
        return userAddressDAO.saveOrUpdate(userAddress);
    }
}
