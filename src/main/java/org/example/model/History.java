package org.example.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class History {
    @JacksonXmlProperty(isAttribute = true)
    private UUID id;

    @JacksonXmlProperty(localName = "tocardnumber")
    private String toCardNumber;

    @JacksonXmlProperty(localName = "fromcardnumber")
    private String fromCardNumber;

    @JacksonXmlProperty(isAttribute = true)
    private double amount;

    @JacksonXmlProperty(localName = "date")
    private String date;


}
