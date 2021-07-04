package at.electro.shop.service.transaction.suppliers;

import at.electro.shop.service.api.models.AddressRequest;
import at.electro.shop.service.transaction.dao.models.AddressEntity;
import at.electro.shop.service.transaction.models.Address;

public class AddressSupplier {

  private AddressSupplier() {
  }

  public static AddressRequest addressMock() {
    AddressRequest address = new AddressRequest();
    address.setFirstName("Johannes");
    address.setLastName("Preisinger");
    address.setEmail("cnnwarthem@gmail.com");
    address.setCity("Vienna");
    address.setCountry("AT");
    address.setFloor("1. Kellergeschoss");
    address.setDoorNumber("7");
    address.setStreetNumber("111");
    address.setStreet("Cumberlandstraße");
    address.setZipCode("1140");
    return address;
  }

  public static Address address() {
    Address address = new Address();
    address.setFirstName("Johannes");
    address.setLastName("Preisinger");
    address.setEmail("cnnwarthem@gmail.com");
    address.setCity("Vienna");
    address.setCountry("AT");
    address.setFloor("1. Kellergeschoss");
    address.setDoorNumber("7");
    address.setStreetNumber("111");
    address.setStreet("Cumberlandstraße");
    address.setZipCode("1140");
    return address;
  }

  public static AddressEntity addressEntityMock() {
    AddressEntity address = new AddressEntity();
    address.setFirstName("Johannes");
    address.setLastName("Preisinger");
    address.setEmail("cnnwarthem@gmail.com");
    address.setCity("Vienna");
    address.setCountry("AT");
    address.setFloor("1. Kellergeschoss");
    address.setDoorNumber("7");
    address.setStreetNumber("111");
    address.setStreet("Cumberlandstraße");
    address.setZipCode("1140");
    return address;
  }
}
