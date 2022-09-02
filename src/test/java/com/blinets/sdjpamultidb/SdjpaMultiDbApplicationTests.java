package com.blinets.sdjpamultidb;

import com.blinets.sdjpamultidb.domain.creditcard.CreditCard;
import com.blinets.sdjpamultidb.services.CreditCardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SdjpaMultiDbApplicationTests {
    @Autowired
    CreditCardService creditCardService;

    @Test
    void testSaveAndGetCreditCard() {
        CreditCard cc = CreditCard.builder()
                .firstName("Pavel")
                .lastName("Blinets")
                .zipCode("12345")
                .creditCardNumber("1234556")
                .cvv("123")
                .expirationDate("12/26")
                .build();

        CreditCard savedCC = creditCardService.saveCreditCard(cc);

        assertThat(savedCC).isNotNull();
        assertThat(savedCC.getId()).isNotNull();
        assertThat(savedCC.getCreditCardNumber()).isNotNull();

        CreditCard ccByID = creditCardService.getCreditCardById(savedCC.getId());

        assertThat(ccByID).isNotNull();
        assertThat(ccByID.getId()).isNotNull();
        assertThat(ccByID.getCreditCardNumber()).isNotNull();
        assertThat(ccByID.getFirstName()).isNotNull();
    }

    @Test
    void contextLoads() {
    }

}
