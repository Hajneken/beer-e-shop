package com.example.user.service.usercart.model.cart;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    @Id
    String id;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.DETACH)
    private Set<Cart> carts = new HashSet<Cart>();

    public Product(String id) {
        this.id = id;
    }

    public Product() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\''+
                '}';
    }
}
