package org.example.model;

import com.ctc.wstx.shaded.msv_core.datatype.xsd.UnicodeUtil;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "cards")
public class Card {
//    @JacksonXmlElementWrapper(localName = "cards")
    @JacksonXmlProperty(isAttribute = true)
    private UUID id;
    private UUID userId;
    private String name;
    private String cardNumber;
    private String expireDate;

}
