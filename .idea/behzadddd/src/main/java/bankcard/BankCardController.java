package bankcard;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bankcards")
public class BankCardController {

    private final BankCardService bankCardService;

    public BankCardController(BankCardService bankCardService) {
        this.bankCardService = bankCardService;
    }

    @PostMapping
    public ResponseEntity<BankCard> addBankCard(@RequestBody BankCard bankCard) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        BankCard savedBankCard = bankCardService.addBankCard(bankCard);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBankCard);
    }




    @PutMapping("/{id}")
    public ResponseEntity<BankCard> updateBankCard(@PathVariable Long id, @RequestBody BankCard bankCard) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        BankCard updatedBankCard = bankCardService.updateBankCard(id, bankCard);
        return ResponseEntity.ok(updatedBankCard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankCard(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        bankCardService.deleteBankCard(id);
        return ResponseEntity.noContent().build();
    }
}
