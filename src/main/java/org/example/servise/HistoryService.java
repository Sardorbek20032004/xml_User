package org.example.servise;

import org.example.model.Card;
import org.example.model.History;
import org.example.model.wrapper.Histories;
import org.example.utils.XmlSerializableDeSerializableUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoryService {
    private static final String PATH = "/Users/sardor/Desktop/xml_User/src/main/java/org/example/file/history.xml";
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY:MM:dd");

    Histories histories = new Histories();

    public void add(History history) {
        histories.setHistories(XmlSerializableDeSerializableUtil.read(PATH, History.class));
        histories.getHistories().add(history);
        XmlSerializableDeSerializableUtil.write(PATH, histories);
    }

    public ArrayList<History> list(Card card) {
        histories.setHistories(XmlSerializableDeSerializableUtil.read(PATH, History.class));
        ArrayList<History> myHistory = new ArrayList<>();
        for (History history : histories.getHistories()) {
            if (history.getToCardNumber().equals(card.getCardNumber())) {
                myHistory.add(history);
            }
        }
        return myHistory.size() != 0 ? myHistory : new ArrayList<>();
    }

    public ArrayList<History> list(Date start, Date finish, Card card) {
        histories.setHistories(XmlSerializableDeSerializableUtil.read(PATH, History.class));
        ArrayList<History> myHistory = new ArrayList<>();
        Date date;
        for (History history : histories.getHistories()) {
            try {
                date = simpleDateFormat.parse(history.getDate());
                System.out.println("history date " + simpleDateFormat.format(date));
            } catch (Exception e) {
                return myHistory;
            }
            if (history.getToCardNumber().equals(card.getCardNumber()) && date.after(start) && finish.before(date)) {
                System.out.println("added" + history);
                myHistory.add(history);
            }
        }
        System.out.println("return");
        return myHistory.size() != 0 ? myHistory : new ArrayList<>();
    }
}
