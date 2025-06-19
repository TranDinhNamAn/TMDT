package service;

import DAO.AddressDAO;
import Model.Address;

public class AddressService {
    private final AddressDAO addressDAO = new AddressDAO();

    public Address getAddressById(int addressId) {
        return addressDAO.getAddressById(addressId);
    }

    public boolean saveOrUpdateAddress(Address address) {
        return addressDAO.saveOrUpdate(address);
    }
}
