package DAO;

import Model.Address;
import org.jdbi.v3.core.Jdbi;

public class AddressDAO {
    private final Jdbi jdbi = Database.DatabaseConnection.getJdbi();

    public Address getAddressById(int addressId) {
        String sql = "SELECT * FROM address WHERE AddressID = :addressID LIMIT 1";
        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("addressID", addressId)
                        .mapToBean(Address.class)
                        .findOne()
                        .orElse(null)
        );
    }

    public int insertAddressReturnId(Address address) {
        String sql = "INSERT INTO address (Street, WardOrcommune, District, ProvinceOrCity) " +
                "VALUES (:street, :wardOrCommune, :district, :provinceOrCity)";
        return jdbi.withHandle(handle ->
                handle.createUpdate(sql)
                        .bindBean(address)
                        .executeAndReturnGeneratedKeys("AddressID")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    public boolean insertAddress(Address address) {
        return insertAddressReturnId(address) > 0;
    }

    public boolean updateAddress(Address address) {
        String sql = "UPDATE address SET Street = :street, WardOrcommune = :wardOrCommune, " +
                "District = :district, ProvinceOrCity = :provinceOrCity WHERE AddressID = :addressID";
        return jdbi.withHandle(handle ->
                handle.createUpdate(sql)
                        .bindBean(address)
                        .execute() > 0
        );
    }

    public boolean saveOrUpdate(Address address) {
        if (address.getAddressID() > 0) {
            return updateAddress(address);
        } else {
            int id = insertAddressReturnId(address);
            address.setAddressID(id);
            return id > 0;
        }
    }
}
