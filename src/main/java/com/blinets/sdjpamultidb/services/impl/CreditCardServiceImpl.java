package com.blinets.sdjpamultidb.services.impl;

import com.blinets.sdjpamultidb.domain.cardholder.CreditCardHolder;
import com.blinets.sdjpamultidb.domain.creditcard.CreditCard;
import com.blinets.sdjpamultidb.domain.pan.CreditCardPan;
import com.blinets.sdjpamultidb.repositories.cardholder.CardHolderRepository;
import com.blinets.sdjpamultidb.repositories.creditcard.CreditCardRepository;
import com.blinets.sdjpamultidb.repositories.pan.PanRepository;
import com.blinets.sdjpamultidb.services.CreditCardService;
import org.springframework.stereotype.Service;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final CardHolderRepository cardHolderRepository;
    private final PanRepository panRepository;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository, CardHolderRepository cardHolderRepository, PanRepository panRepository) {
        this.creditCardRepository = creditCardRepository;
        this.cardHolderRepository = cardHolderRepository;
        this.panRepository = panRepository;
    }

    @Override
    public CreditCard saveCreditCard(CreditCard cc) {
        CreditCard creditCardSave = creditCardRepository.save(cc);
        creditCardSave.setFirstName(cc.getFirstName());
        creditCardSave.setLastName(creditCardSave.getLastName());
        creditCardSave.setZipCode(creditCardSave.getZipCode());
        creditCardSave.setCreditCardNumber(creditCardSave.getCreditCardNumber());

        cardHolderRepository.save(CreditCardHolder.builder()
                .creditCardId(creditCardSave.getId())
                .firstName(creditCardSave.getFirstName())
                .lastName(creditCardSave.getLastName())
                .zipCode(creditCardSave.getZipCode())
                .build());
        panRepository.save(CreditCardPan.builder()
                .creditCardId(creditCardSave.getId())
                .creditCardNumber(creditCardSave.getCreditCardNumber())
                .build());

        return creditCardSave;
    }
}
