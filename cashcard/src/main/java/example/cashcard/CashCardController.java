package example.cashcard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cashcards")
public class CashCardController {

    private final CashCardRepository cashCardRepository;

    private CashCardController(CashCardRepository cashCardRepository) {
        this.cashCardRepository = cashCardRepository;
    }

    @GetMapping()
    private ResponseEntity<Iterable<CashCard>> findAll(Pageable pageable) {
        Page<CashCard> page = cashCardRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount")) // get sort or use default sort setting if any of 3 required params is not provided
                )
        );
        return ResponseEntity.ok(page.getContent());
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<CashCard> findById(@PathVariable Long requestedId) {
        Optional<CashCard> cashCardOptional = cashCardRepository.findById(requestedId);
        if (cashCardOptional.isPresent()) {
            return ResponseEntity.ok(cashCardOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    private ResponseEntity<Void> createCashCard(@RequestBody CashCard newCashCardRequest, UriComponentsBuilder ucb) {
        CashCard savedCashCard = cashCardRepository.save(newCashCardRequest);
        URI locationOfNewCashCard = ucb
                .path("/cashcards/{id}")
                .buildAndExpand(savedCashCard.id())
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
    }
}