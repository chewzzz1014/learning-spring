package sia.tacocloud.tacos.data;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.TacoOrder;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

//    TacoOrder save(TacoOrder order);

//    List<TacoOrder> findByDeliveryZip(String deliveryZip);
}
