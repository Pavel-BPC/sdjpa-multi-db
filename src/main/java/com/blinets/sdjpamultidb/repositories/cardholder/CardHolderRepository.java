package com.blinets.sdjpamultidb.repositories.cardholder;

import com.blinets.sdjpamultidb.domain.cardholder.CreditCardHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardHolderRepository extends JpaRepository<CreditCardHolder, Long> {
}
