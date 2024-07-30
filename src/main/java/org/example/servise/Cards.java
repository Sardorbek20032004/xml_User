package org.example.servise;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Card;
import org.example.model.User;

import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "cards")
public class Cards {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "card")
    private ArrayList<Card> cards = new ArrayList<>();
}
