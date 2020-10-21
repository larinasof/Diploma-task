package ru.netology.web.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private static SelenideElement cardButton = $$("button").find(exactText("Купить"));
    private static SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));
    private static SelenideElement payByCard = $$("h3[class]").find(exactText("Оплата по карте"));
    private static SelenideElement payInCredit = $$("h3[class]").find(exactText("Кредит по данным карты"));

    public void openCardPaymentPage() {
        cardButton.click();
        payByCard.shouldBe(visible);
    }

    public void openCreditPage() {
        creditButton.click();
        payInCredit.shouldBe(visible);
    }
}
