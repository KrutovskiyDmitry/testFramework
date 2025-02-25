package example.cucumber.steps;

import example.cucumber.pages.ExamplePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;


public class ExampleSteps {

    @Autowired
    ExamplePage examplePage;


    @When("^Открываю страницу (.*)$")
    public void openPage(String url) {
        open(url);
    }

    @When("^Проверяю что отображается поле ввода$")
    public void check() {
        examplePage.input.shouldBe(visible.because("Не отобразилось поле ввода"));
    }

    @When("^Ввожу в поле ввода (.*)$")
    public void sendText(String text) {
        examplePage.input.sendKeys(text);
    }

    @Then("^Проверяю что в поле ввода число больше (\\d+)$")
    public void checkResultCont(int count) {
        assertThat(Integer.parseInt(examplePage.input.getValue()))
                .as("Неверное значение")
                .isGreaterThan(count);
    }
}
