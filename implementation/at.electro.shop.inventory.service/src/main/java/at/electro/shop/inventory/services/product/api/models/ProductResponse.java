package at.electro.shop.inventory.services.product.api.models;

import java.math.BigDecimal;

//what you GET
public class ProductResponse {
    private String uuid;
    private String name;
    private String brand;
    private String img;
    private BigDecimal rating;
    private Integer ratingsCount;
    private String description;
    private BigDecimal alcoholPercent;
    private BigDecimal price;
    private String currency;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Integer getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(Integer ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAlcoholPercent() {
        return alcoholPercent;
    }

    public void setAlcoholPercent(BigDecimal alcoholPercent) {
        this.alcoholPercent = alcoholPercent;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
