package com.blinets.sdjpamultidb.repositories;

import com.blinets.sdjpamultidb.domain.pan.CreditCardPan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanRepository extends JpaRepository<CreditCardPan, Long> {
}
