package com.blinets.sdjpamultidb.repositories.pan;

import com.blinets.sdjpamultidb.domain.pan.CreditCardPan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PanRepository extends JpaRepository<CreditCardPan, Long> {
    Optional<CreditCardPan> findCreditCardPanByCreditCardId(Long id);
}

