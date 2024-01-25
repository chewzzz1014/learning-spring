package sia.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.model.TacoOrder;
import sia.tacocloud.security.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
