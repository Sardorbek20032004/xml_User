package org.example.servise;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.model.Card;
import org.example.utils.GsonReadWriteUtil;

import java.util.ArrayList;
import java.util.UUID;

public class CardService {
    private static final String PATH = "/Users/sardor/Desktop/xml_User/src/main/java/org/example/file/cards.txt";

    public Card add(Card card) {
        ArrayList<Card> cards = GsonReadWriteUtil.readFromFile(PATH, new TypeReference<>() {});
        if (hasCard(card, cards)) {
            cards.add(card);
            GsonReadWriteUtil.writeToFile(cards, PATH);
            return card;
        }
        return null;
    }

     boolean hasCard(Card myCard, ArrayList<Card> cards) {
        for (Card card : cards) {
            if (card.getCardNumber().equals(myCard.getCardNumber())) {
                return false;
            }
        }
        return true;
    }
    public  Card login(String cardNumber) {
        ArrayList<Card> cards = GsonReadWriteUtil.readFromFile(PATH, new TypeReference<>() {});
        for (Card card : cards) {
            if (card.getCardNumber().equals(cardNumber)) {
                return card;
            }
        }
        return null;
    }

    public ArrayList<Card> list(UUID id) {
        ArrayList<Card> cards = GsonReadWriteUtil.readFromFile(PATH, new TypeReference<>() {
        });
        ArrayList<Card> myCard = new ArrayList<>();
        for (Card card : cards) {
            if (card.getUserId().equals(id)) {
                myCard.add(card);
            }
        }
        return myCard;
    }
    public void p2p(String toCardNumber,String fromCardNumber,double amount){
        ArrayList<Card> cards = GsonReadWriteUtil.readFromFile(PATH, new TypeReference<>() {});
        for (Card card: cards){
            if (card.getCardNumber().equals(toCardNumber)){
                card.setAmount(card.getAmount() - amount);
            }
            if (card.getCardNumber().equals(fromCardNumber)){
                card.setAmount(card.getAmount() + amount);
            }
        }
        GsonReadWriteUtil.writeToFile(cards,PATH);
    }
}
