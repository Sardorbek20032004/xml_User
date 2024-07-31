package org.example.servise;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.model.Card;
import org.example.model.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.Callable;

public class CardService {
    private static final String PATH = "card.xml";
    Cards cards = new Cards();
    IOFileReaderAndWriter ioFileReaderAndWriter = new IOFileReaderAndWriter();

    public Card add(Card card) {
        cards.setCards(ioFileReaderAndWriter.read(PATH, Card.class));
        if (hasCard(card)) {
            cards.getCards().add(card);
            ioFileReaderAndWriter.write(PATH, cards);
            return card;
        }
        return null;
    }

    private boolean hasCard(Card myCard) {
        for (Card card : cards.getCards()) {
            if (card.getCardNumber().equals(myCard.getCardNumber())) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Card> list(UUID id) {
        cards.setCards(ioFileReaderAndWriter.read(PATH, Card.class));
        ArrayList<Card> myCard = new ArrayList<>();
        for (Card card : cards.getCards()) {
            if (card.getUserId().equals(id)) {
                myCard.add(card);
            }
        }
        return myCard;
    }
}
