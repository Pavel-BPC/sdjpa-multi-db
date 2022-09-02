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
    private String svv;

    @Convert(converter = CreditCardConverter.class)
    private String expirationData;

}
