package com.blinets.sdjpamultidb.domain.creditcard;

import com.blinets.sdjpamultidb.domain.CreditCardConverter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CreditCardConverter.class)
    private String cvv;

    @Convert(converter = CreditCardConverter.class)
    private String expirationDate;

    @Transient
    private String creditCardNumber;
    @Transient
    private String firstName;
    @Transient
    private String lastName;
    @Transient
    private String zipCode;

}
