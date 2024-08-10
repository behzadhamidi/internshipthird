package bankcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import user.User;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class BankCardService {

    @Autowired

    private final BankCardRepository bankCardRepository;
    private final UserService userService;

    public BankCardService(BankCardRepository bankCardRepository, UserService userService) {
        this.bankCardRepository = bankCardRepository;
        this.userService = userService;
    }
    public BankCard saveBankCard(BankCard bankCard) {
        return bankCardRepository.save(bankCard);
    }

    public BankCard addBankCard(BankCard bankCard) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userService.findByUsername(currentUserName);

        bankCard.setUser(user);
        return bankCardRepository.save(bankCard);
    }
    public List<BankCard> findAllBankCardsByUserId(Long userId) {
        return bankCardRepository.findByUserId(userId);
    }

    public BankCard findBankCardById(Long id) {
        Optional<BankCard> bankCard = bankCardRepository.findById(id);
        return bankCard.orElse(null);
    }

    public BankCard updateBankCard(Long id, BankCard bankCardDetails) {
        Optional<BankCard> bankCardOptional = bankCardRepository.findById(id);
        if (bankCardOptional.isPresent()) {
            BankCard bankCard = bankCardOptional.get();
            bankCard.setCardNumber(bankCardDetails.getCardNumber());
            bankCard.setCardHolderName(bankCardDetails.getCardHolderName());
            bankCard.setExpiryDate(bankCardDetails.getExpiryDate());
            bankCard.setCvv(bankCardDetails.getCvv());
            return bankCardRepository.save(bankCard);
        }
        return null;
    }

    public void deleteBankCard(Long id) {
        bankCardRepository.deleteById(id);
    }
}
