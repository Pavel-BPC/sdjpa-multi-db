package com.blinets.sdjpamultidb.services;

import com.blinets.sdjpamultidb.domain.creditcard.CreditCard;

public interface CreditCardService {
    CreditCard saveCreditCard(CreditCard cc);

    CreditCard getCreditCardById(Long id);
}
