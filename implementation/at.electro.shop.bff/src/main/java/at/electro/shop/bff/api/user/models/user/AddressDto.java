package at.electro.shop.bff.api.user.models.user;

public class AddressDto {

    private String street;
    private String streetNumber;
    private int doorNumber;
    private String floor;
    private String city;
    private int zipCode;
    private String country;


    public AddressDto(String street, String streetNumber, int doorNumber, String floor, String city, int zipCode, String country) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.doorNumber = doorNumber;
        this.floor = floor;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public AddressDto() {

    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(int doorNumber) {
        this.doorNumber = doorNumber;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", doorNumber=" + doorNumber +
                ", floor='" + floor + '\'' +
                ", city='" + city + '\'' +
                ", zipCode=" + zipCode +
                ", country='" + country + '\'' +
                '}';
    }


}
