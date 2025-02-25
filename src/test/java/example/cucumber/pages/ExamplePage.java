package example.cucumber.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Component
public class ExamplePage {

   public final SelenideElement input = $x("//input[@type='number']").as("Поле ввода");

}
