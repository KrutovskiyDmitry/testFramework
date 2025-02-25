package example.cucumber.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;

@Component
public class VivicaRegistrationPage {

    //=======Локаторы для текущей страницы========//
    /**
     * Заголовки полей на странице регистрации
     */
    private static final List<SelenideElement> registrationFieldLabels = $$x("//span[text()='Register']//following::label");
    private static ElementsCollection registrationFieldTitles = $$x("//span[text()='Register']//following::label");
    By registrationFieldInput = By.xpath("./following::div/input"); //путь от названия до поля ввода

    /**
     * Ошибки для полей ввода на странице регистрации пользователя
     */
    private static final List<SelenideElement> registrationPageErrors = $$x("//div[@color = '#EA3D5C']");


    /**
     * Все кнопки на странице регистрации
     */
    private static final List<SelenideElement> registrationPageButtons = $$x("//button");


    /**
     * Заголовок страницы завершения авторизации
     */
    private static final SelenideElement congratsHeader = $x("//span[text()='Congratulations!']");

    /**
     * Сообщение на странице завершения авторизации
     */
    private static final SelenideElement congratsMessage = $x("//span[contains(text(),'Congratulations')]/following::span");
    public final SelenideElement userTermsWindow = $x("//h2[text()='User Terms of Use']");
    public final SelenideElement userTermsCheckbox = $x("//h2[text()='User Terms of Use']//following::input[@type='checkbox']");
    public final SelenideElement userTermsNext = $x("//h2[text()='User Terms of Use']//following::button");
    //=============================================//

    /**
     * Метод заполняет поля данными на странице регистрации
     *
     * @param field название поля
     * @param data  данные для ввода
     */
    public void inputDataForField(String field, String data) {
        SelenideElement currentField = registrationFieldLabels.stream()
                .filter(e -> e.getText()
                        .equals(field))
                .findFirst()
                .orElse(null);
        Assert.notNull(currentField, "Не найдено поле " + field);
        currentField.find(registrationFieldInput)
                .setValue(data + Keys.TAB);
    }

    /**
     * Метод возвращает список ошибок на странице регистрации
     *
     * @return
     */
    public List<String> getRegistrationErrors() {
        return registrationPageErrors.stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
    }

    /**
     * Метод находит и нажимает кнопку по ее тексту
     *
     * @param button текст кнопки
     */
    public void pressButtonByText(String button) {
        SelenideElement currentButton = registrationPageButtons.stream()
                .filter(b -> b.getText()
                        .equals(button))
                .findFirst()
                .orElse(null);
        Assert.notNull(currentButton, "Не найдена кнопка " + button);
        currentButton.click();
    }

    /**
     * Метод возвращает отображение заголовка страницы
     * завершения авторизации
     *
     * @return
     */
    public boolean congratulationsPageIsVisible() {
        congratsHeader.shouldBe(Condition.visible);
        try {
            return congratsHeader.is(Condition.visible);
        } catch (ElementNotFound e) {
            return false;
        }
    }

    /**
     * Метод возвращает сообщение со страницы завершения регистрации
     *
     * @return
     */
    public String getCongratsMessage() {
        return congratsMessage.getText();
    }

    public boolean buttonIsVisible(String button) {
        SelenideElement currentButton = registrationPageButtons.stream()
                .filter(b -> b.getText()
                        .equals(button))
                .findFirst()
                .orElse(null);
        Assert.notNull(currentButton, "Не найдена кнопка " + button);
        return currentButton.is(Condition.visible);
    }

    /**
     * Универсальный метод для проверки видимости элемента надписи и соответствия её текста ожидаемому
     * @param element - SelenideElement на основе локатора
     * @param text - ожидаемый текст
     */
    private void checkElementText(SelenideElement element, String text) {
        assertThat(element.is(visible)).withFailMessage("Элемент " + text + " не виден").isTrue();
        assertThat(element.getText().equals(text)).withFailMessage("Неверный текст заголовка!\nОжидаемый: " + text + "\nФактический: " + element.getText()).isTrue();
    }

    /**
     * Метод возвращает видимость заголовка поля ввода по его тексту
     * @param title
     * @return
     */
    public boolean registerTitleIsVisible(String title) {
        boolean visibility = registrationFieldTitles.findBy(exactOwnText(title)).is(visible);
        registrationFieldTitles = registrationFieldTitles.excludeWith(exactOwnText(title));
        return visibility;
    }
}
