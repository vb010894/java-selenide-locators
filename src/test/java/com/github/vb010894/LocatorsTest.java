package com.github.vb010894;


import com.codeborne.selenide.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;

/**
 * Примеры локторов
 */
public class LocatorsTest {

    /**
     * Инициализация
     */
    @BeforeAll
    @DisplayName("Открытие калькулятора")
    public static void init() {
        WebDriverManager.chromedriver().setup();
        // Да так можно
        Configuration.timeout = 20_000;
        Selenide.open("https://web2.0calc.ru/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    /**
     * Атрибут id
     */
    @Test
    @DisplayName("Тест локатора по 'id'")
    public void testIdLocators() {
        SelenideElement btn1 = Selenide.$(By.id("Btn1"));
        btn1.click();
        Selenide.sleep(10000);
    }

    /**
     * Атрибут class
     */
    @Test
    @DisplayName("Тест локатора по 'className'")
    public void testClassLocators() {
        SelenideElement calcContainer = Selenide.$(By.className("center")).shouldBe(Condition.visible);
        Rectangle rect = calcContainer.getRect();
        System.out.printf("""
                Положение:
                X: %s
                Y: %s
                Ширина: %s
                Высота: %s
                %n""", rect.x, rect.y, rect.width, rect.height);
        Selenide.sleep(10000);
    }

    /**
     * CSS селектор
     */
    @Test
    @DisplayName("Тест css селектора")
    public void testCssLocators() {
        SelenideElement button = Selenide
                .$(".btns button[title='Синус, sin(x)']")
                .shouldBe(Condition.visible);
        button.click();
        Selenide.sleep(10000);
    }

    /**
     * XPATH
     */
    @Test
    @DisplayName("Тест XPATH")
    public void testXpathLocators() {
        SelenideElement button = Selenide
                .$x(".//div[@class='btns']//button[@title='Синус, sin(x)']")
                .shouldBe(Condition.visible);
        button.click();
        Selenide.sleep(10000);
    }
}
