package at.electro.shop.service.suppliers;

import at.electro.shop.service.api.models.Address;
import at.electro.shop.service.shipment.dao.models.AddressEntity;

public class AddressSupplier {

    private AddressSupplier() {

    }

    public static Address addressMock() {
        Address address = new Address();
        address.setFirstName("Homer");
        address.setLastName("Simpson");
        address.setEmail("homer.simpson@nuclearpower.com");
        address.setStreet("Immergrüngasse");
        address.setStreetNumber("742");
        address.setDoorNumber("12");
        address.setFloor("1");
        address.setCity("Vienna");
        address.setZipCode("1011");
        address.setCountry("AT");

        return address;
    }

    public static AddressEntity addressEntityMock() {
        AddressEntity address = new AddressEntity();
        address.setFirstName("Homer");
        address.setLastName("Simpson");
        address.setEmail("homer.simpson@nuclearpower.com");
        address.setStreet("Immergrüngasse");
        address.setStreetNumber("742");
        address.setDoorNumber("12");
        address.setFloor("1");
        address.setCity("Vienna");
        address.setZipCode("1011");
        address.setCountry("AT");

        return address;
    }
}
