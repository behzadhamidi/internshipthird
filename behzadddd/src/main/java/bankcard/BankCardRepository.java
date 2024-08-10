package bankcard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BankCardRepository extends JpaRepository<BankCard, Long> {
    List<BankCard> findByUserId(Long userId);
}
