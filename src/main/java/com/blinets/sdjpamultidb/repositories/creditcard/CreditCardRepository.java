package com.blinets.sdjpamultidb.repositories.creditcard;

import com.blinets.sdjpamultidb.domain.creditcard.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
