package at.electro.shop.inventory.services.inventory.dao.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import at.electro.shop.inventory.services.product.dao.models.ProductEntity;

@Entity(name = "inventory")
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "inventory_uuid")
    private String inventoryUuid;

    @Column(name = "vendor")
    private String vendor;

    @OneToMany(mappedBy = "inventory")
    private List<ProductEntity> products;

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getinventoryUuid() {
        return inventoryUuid;
    }

    public void setinventoryUuid(String inventoryUuid) {
        this.inventoryUuid = inventoryUuid;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}