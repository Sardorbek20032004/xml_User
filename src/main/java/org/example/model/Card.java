package org.example.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private UUID id;
    private UUID userId;
    private String name;
    private String cardNumber;
    private String expireDate;
    private double amount;

}
