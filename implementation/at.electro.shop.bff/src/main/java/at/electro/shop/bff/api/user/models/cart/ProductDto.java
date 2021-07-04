package at.electro.shop.bff.api.user.models.cart;

public class ProductDto {
    int count;
    String id;

    public ProductDto(String id) {
        this.id = id;
        count = 1;
    }

    public ProductDto(String id, int count) {
        this.id = id;
        this.count = count;
    }

    public ProductDto() {
        count = 1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
