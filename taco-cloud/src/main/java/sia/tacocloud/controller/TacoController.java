package sia.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sia.tacocloud.model.Ingredient;
import sia.tacocloud.model.Taco;
import sia.tacocloud.model.TacoOrder;
import sia.tacocloud.repository.OrderRepository;
import sia.tacocloud.repository.TacoRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoController {

    private TacoRepository tacoRepository;
    private OrderRepository orderRepository;
    private RestTemplate restTemplate;

    public TacoController(TacoRepository tacoRepository, OrderRepository orderRepository, RestTemplate restTemplate) {
        this.tacoRepository = tacoRepository;
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
//        return tacoRepository.findAll(page).getContent();
        return tacoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepository.findById(id);
        if (optTaco.isPresent()) {
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }

    @PutMapping(path="/{orderId}", consumes="application/json")
    public TacoOrder putOrder(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder order) {
        order.setId(orderId);
        return orderRepository.save(order);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public TacoOrder patchOrder(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder patch) {
        Optional<TacoOrder> optOrder = orderRepository.findById(orderId);
        TacoOrder order = optOrder.orElseGet(TacoOrder::new);
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {

        }
    }

    // REST Template
    public Ingredient getIngredientById(String ingredientId) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);
        return restTemplate.getForObject("http://localhost:8080/ingredients/{id}", Ingredient.class, urlVariables);
    }

    public Ingredient getIngredientById2(String ingredientId) {
        ResponseEntity<Ingredient> responseEntity = restTemplate.getForEntity("http://localhost:8080/ingredients/{id}", Ingredient.class, ingredientId);
        log.info("Fetched time: {}", responseEntity.getHeaders().getDate());
        return responseEntity.getBody();
    }

    public void updateIngredient(Ingredient ingredient) {
        restTemplate.put("http://localhost:8080/ingredients/{id}", ingredient, ingredient.getId());
    }

    public void deleteIngredient(Ingredient ingredient) {
        restTemplate.delete("http://localhost:8080/ingredients/{id}", ingredient.getId());
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return restTemplate.postForObject("http://localhost:8080/ingredients/{id}", ingredient, Ingredient.class);
    }

    public Ingredient createIngredient2(Ingredient ingredient) {
        ResponseEntity<Ingredient> responseEntity = restTemplate.postForEntity("http://localhost:8080/ingredients/{id}", ingredient, Ingredient.class);
        log.info("New resource created at {}", responseEntity.getHeaders().getLocation());
        return responseEntity.getBody();
    }


}
