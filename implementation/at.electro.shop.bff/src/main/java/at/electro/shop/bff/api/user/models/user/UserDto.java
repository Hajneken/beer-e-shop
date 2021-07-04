package at.electro.shop.bff.api.user.models.user;


import at.electro.shop.bff.api.user.models.cart.CartDto;

public class UserDto {

    private String uuid;
    private String firstName;
    private String lastName;
    private AddressDto addressDto;
    private CartDto cartDto;

    public UserDto(String uuid, String firstName, String lastName, AddressDto addressDto, CartDto cartDto) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressDto = addressDto;
        this.cartDto = cartDto;
    }

    public UserDto(){}

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AddressDto getAddress() {
        return addressDto;
    }

    public void setAddress(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public CartDto getCart() {
        return cartDto;
    }

    public void setCart(CartDto cartDto) {
        this.cartDto = cartDto;
    }
}
