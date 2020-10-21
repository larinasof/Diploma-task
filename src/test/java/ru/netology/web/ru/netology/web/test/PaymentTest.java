package ru.netology.web.ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.CardData;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.DataSQL;
import ru.netology.web.pages.MainPage;
import ru.netology.web.pages.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentTest {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();
    private static String approvedCard = "APPROVED";
    private static String declinedCard = "DECLINED";

//    @BeforeAll
//    static void setUpAll() {
//        SelenideLogger.addListener("allure", new AllureSelenide());
//    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @AfterAll
    static void AllureReport() {
//        SelenideLogger.removeListener("allure");
        DataSQL.cleanTables();
    }

    @Test
    void shouldPayWithApprovedCard() {
        mainPage.openCardPaymentPage();
        CardData card = DataHelper.getApprovedCard();
        paymentPage.payment(card);
        paymentPage.paymentVerify();

        val actual = DataSQL.getPaymentStatus();
        assertEquals(approvedCard, actual);

        val paymentId = DataSQL.getPaymentId();
        val orderPaymentId = DataSQL.getOrderPaymentId();
        assertEquals(paymentId, orderPaymentId);
    }

    @Test
    void shouldPayWithDeclinedCard() {
        mainPage.openCardPaymentPage();
        CardData card = DataHelper.getDeclinedCard();
        paymentPage.payment(card);
        paymentPage.paymentError();

        val actual = DataSQL.getPaymentStatus();
        assertEquals(declinedCard, actual);

        val paymentId = DataSQL.getPaymentId();
        val orderPaymentId = DataSQL.getOrderPaymentId();
        assertEquals(paymentId, orderPaymentId);
    }

    @Test
    void shouldPayWithInvalidNumberCard() {
        mainPage.openCardPaymentPage();
        CardData card = DataHelper.getInvalidNumberCard();
        paymentPage.payment(card);
        paymentPage.paymentError();

        val orderPaymentId = DataSQL.getOrderPaymentId();
        assertNull(orderPaymentId);
    }

    @Test
    void shouldPayWithInvalidFormatCard() {
        mainPage.openCardPaymentPage();
        CardData card = DataHelper.getInvalidFormatNumberCard();
        paymentPage.payment(card);
        paymentPage.cardNumberError();

        val orderPaymentId = DataSQL.getOrderPaymentId();
        assertNull(orderPaymentId);
    }

    @Test
    void shouldPayWithExpiredCard() {
        mainPage.openCardPaymentPage();
        CardData card = DataHelper.getExpiredCard();
        paymentPage.payment(card);
        String actual = paymentPage.cardYearError();
        assertEquals("Истёк срок действия карты", actual);

        val orderPaymentId = DataSQL.getOrderPaymentId();
        assertNull(orderPaymentId);
    }

    @Test
    void shouldPayWithInvalidYearCard() {
        mainPage.openCardPaymentPage();
        CardData card = DataHelper.getInvalidYearCard();
        paymentPage.payment(card);
        String actual = paymentPage.cardYearError();
        assertEquals("Неверно указан срок действия карты", actual);

        val orderPaymentId = DataSQL.getOrderPaymentId();
        assertNull(orderPaymentId);
    }

    @Test
    void shouldPayWithInvalidMonthCard() {
        mainPage.openCardPaymentPage();
        CardData card = DataHelper.getInvalidMonthCard();
        paymentPage.payment(card);
        paymentPage.cardMonthError();

        val orderPaymentId = DataSQL.getOrderPaymentId();
        assertNull(orderPaymentId);
    }

    @Test
    void shouldPayWithInvalidOwnerCard() {
        mainPage.openCardPaymentPage();
        CardData card = DataHelper.getInvalidOwnerCard();
        paymentPage.payment(card);
        paymentPage.cardOwnerError();

        val orderPaymentId = DataSQL.getOrderPaymentId();
        assertNull(orderPaymentId);
    }

    @Test
    void shouldPayWithInvalidCVCCard() {
        mainPage.openCardPaymentPage();
        CardData card = DataHelper.getInvalidCVCCard();
        paymentPage.payment(card);
        paymentPage.cardCVCError();

        val orderPaymentId = DataSQL.getOrderPaymentId();
        assertNull(orderPaymentId);
    }
}
