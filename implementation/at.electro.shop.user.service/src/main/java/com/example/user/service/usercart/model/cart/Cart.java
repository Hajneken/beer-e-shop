package com.example.user.service.usercart.model.cart;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Cart {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;

    @ManyToMany
    @JoinTable(
            name = "carts_products",
            joinColumns = @JoinColumn(name = "carts_uuid"),
            inverseJoinColumns = @JoinColumn(name = "products_id")
    )
    private List<Product> products = new ArrayList<>();


    public Cart(List<Product> products) {
        this.products = products;
    }

    public Cart() {

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @ManyToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        boolean isAlreadyExist = false;
        for (Product p : products) {
            if (p.getId().equals(product.getId())) {
                isAlreadyExist = true;
                return;
            }
        }


        products.add(product);


    }

    public void removeProduct(Product product) {
        for (Product p : products) {
            if (p.getId().equals(product.getId())) {
                this.products.remove(p);
                break;
            }
        }
    }

}
