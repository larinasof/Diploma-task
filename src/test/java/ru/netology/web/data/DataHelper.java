package ru.netology.web.data;

import com.github.javafaker.Faker;
import java.time.Year;

public class DataHelper {
    private static final Faker faker = new Faker();

    private static final String approvedCardNumber = "4444 4444 4444 4441";
    private static final String declinedCardNumber = "4444 4444 4444 4442";
    private static final String invalidCardNumber = "4444 4444 4444 4443";
    private static final String invalidFormatCardNumber = "4444 4444 444";


    public static CardData CardData(String cardNumber) {
        String month = String.format("%02d", faker.number().numberBetween(1, 12));
        int currentYear = Year.now().getValue() % 100;
        String year = String.valueOf(faker.number().numberBetween(currentYear + 2, currentYear + 5));
        String owner = "Ivan Ivanov";
        String cvc = String.format("%03d", faker.number().numberBetween(1, 999));
        return new CardData(cardNumber, month, year, owner, cvc);
    }

    public static CardData getApprovedCard() { return CardData(approvedCardNumber); }

    public static CardData getDeclinedCard() {
        return CardData(declinedCardNumber);
    }

    public static CardData getInvalidNumberCard() {
        return CardData(invalidCardNumber);
    }

    public static CardData getInvalidFormatNumberCard() {
        return CardData(invalidFormatCardNumber);
    }

    public static CardData getExpiredCard() {
        CardData cardData = CardData(approvedCardNumber);
        cardData.setCardYear("19");
        return cardData;
    }

    public static CardData getInvalidYearCard() {
        CardData cardData = CardData(approvedCardNumber);
        int currentYear = Year.now().getValue() % 100;
        cardData.setCardYear(String.valueOf(currentYear + 6));
        return cardData;
    }

    public static CardData getInvalidMonthCard() {
        CardData cardData = CardData(approvedCardNumber);
        cardData.setCardMonth("13");
        return cardData;
    }

    public static CardData getInvalidOwnerCard() {
        CardData cardData= CardData(approvedCardNumber);
        cardData.setCardOwner("Иван Иванов");
        return cardData;
    }


    public static CardData getInvalidCVCCard() {
        CardData cardData = CardData(approvedCardNumber);
        cardData.setCardCVC(String.valueOf(faker.number().numberBetween(1, 99)));
        return cardData;
    }
}
