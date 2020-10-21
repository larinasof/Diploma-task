package ru.netology.web.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.CardData;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement cardMonth = $("[placeholder='08']");
    private SelenideElement cardYear = $("[placeholder='22']");
    private SelenideElement cardOwner = $(withText("Владелец")).parent().$(".input__box>input.input__control");
    private SelenideElement cardCVC = $("[placeholder='999']");
    private SelenideElement proceedButton = $(withText("Продолжить"));

    private ElementsCollection textInputFields = $$(".input_type_text");
    private SelenideElement cardNumberError = textInputFields.get(0).find(".input__sub");
    private SelenideElement cardMonthError = textInputFields.get(1).find(".input__sub");
    private SelenideElement cardYearError = textInputFields.get(2).find(".input__sub");
    private SelenideElement cardOwnerError = textInputFields.get(3).find(".input__sub");
    private SelenideElement cardCVCError = textInputFields.get(4).find(".input__sub");

    private SelenideElement successNotification= $(withText("Операция одобрена Банком."));
    private SelenideElement errorNotification = $(withText("Ошибка! Банк отказал в проведении операции."));

    public void payment (CardData card) {
        cardNumber.setValue(card.getCardNumber());
        cardMonth.setValue(card.getCardMonth());
        cardYear.setValue(card.getCardYear());
        cardOwner.setValue(card.getCardOwner());
        cardCVC.setValue(card.getCardCVC());
        proceedButton.click();
    }

    public void paymentVerify() {
        successNotification.shouldBe(Condition.visible);
    }

    public void paymentError() {
        errorNotification.shouldBe(Condition.visible);
    }

    public void cardNumberError() {
        cardNumberError.shouldHave(Condition.exactText("Неверный формат"));
    }

    public void cardMonthError() {
        cardMonthError.shouldHave(Condition.exactText("Неверно указан срок действия карты"));
    }

    public String cardYearError() {
        cardYearError.shouldBe(Condition.visible);
        String error = cardYearError.getText();
        return error;
    }

    public void cardOwnerError() {
        cardOwnerError.shouldHave(Condition.exactText("Неверный формат"));
    }

    public void cardCVCError() {
        cardCVCError.shouldHave(Condition.exactText("Неверный формат"));
    }
}
