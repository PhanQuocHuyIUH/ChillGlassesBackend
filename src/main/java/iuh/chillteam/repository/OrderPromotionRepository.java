package iuh.chillteam.repository;

import iuh.chillteam.entity.OrderPromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderPromotionRepository extends JpaRepository<OrderPromotion, Long> {

    List<OrderPromotion> findByOrderId(Long orderId);
}
