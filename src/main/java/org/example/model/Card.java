package org.example.model;

import com.ctc.wstx.shaded.msv_core.datatype.xsd.UnicodeUtil;
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

}
