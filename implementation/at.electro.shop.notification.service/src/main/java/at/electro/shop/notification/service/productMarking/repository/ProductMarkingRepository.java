package at.electro.shop.notification.service.productMarking.repository;

import at.electro.shop.notification.service.productMarking.dao.models.ProductMarkingEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMarkingRepository extends JpaRepository<ProductMarkingEntity, Long> {
    List<ProductMarkingEntity> getByProduct(String product);

    List<ProductMarkingEntity> getByUser(String user);

    Optional<ProductMarkingEntity> getByUuid(String uuid);
}
