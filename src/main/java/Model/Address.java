package Model;

public class Address {
    private int addressID; // AddressID trong database
    private String street; // Tên đường
    private String wardOrCommune; // Phường/Xã
    private String district; // Quận/Huyện
    private String provinceOrCity; // Tỉnh/Thành phố

    // Getters and Setters
    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWardOrCommune() {
        return wardOrCommune;
    }

    public void setWardOrCommune(String wardOrCommune) {
        this.wardOrCommune = wardOrCommune;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvinceOrCity() {
        return provinceOrCity;
    }

    public void setProvinceOrCity(String provinceOrCity) {
        this.provinceOrCity = provinceOrCity;
    }
}
