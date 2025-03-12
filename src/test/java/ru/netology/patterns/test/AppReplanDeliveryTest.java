package ru.netology.patterns.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.patterns.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AppReplanDeliveryTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldSendAndReplanDateTest() {

        $("[data-test-id='city'] input.input__control").setValue(DataGenerator.generateCity());
        $("[data-test-id='date'] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input.input__control").setValue(DataGenerator.generateDate(4));
        $("[data-test-id='name'] input.input__control").setValue(DataGenerator.generateName("ru"));
        $("[data-test-id='phone'] input.input__control").setValue(DataGenerator.generatePhone("ru"));
        $("[data-test-id='agreement'] .checkbox__box").click();
        $("button.button").click();

        $("[data-test-id='success-notification']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text(DataGenerator.generateDate(4)));

        $("[data-test-id='date'] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input.input__control").setValue(DataGenerator.generateDate(5));
        $("button.button").click();
        $("[data-test-id='replan-notification']")
                .shouldBe(Condition.visible,Duration.ofSeconds(3));

        $("[data-test-id='replan-notification'] button.button").click();
        $("[data-test-id='success-notification']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text(DataGenerator.generateDate(5)));
    }

}

