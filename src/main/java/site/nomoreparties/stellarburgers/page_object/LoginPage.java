package site.nomoreparties.stellarburgers.page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.page;

import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class LoginPage {

    public static final String PATH = "/login";

    public StellarHeaderPage stellarHeaderPage = Selenide.page(StellarHeaderPage.class);

    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement emailInput;

    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement passwordInput;

    @FindBy(how = How.XPATH, using = ".//div[@class='input__icon input__icon-action']")
    private SelenideElement iconEye;

    @FindBy(how = How.XPATH, using = ".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']")
    private SelenideElement loginButton;

    @FindBy(how = How.XPATH, using = ".//a[text()='Зарегистрироваться']")
    private SelenideElement registerLink;

    @FindBy(how = How.XPATH, using = ".//a[text()='Восстановить пароль']")
    private SelenideElement restorePasswordLink;

    @FindBy(how = How.XPATH, using = ".//h2[text()='Вход']")
    public SelenideElement entranceTitle;

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    @Step("Авторизоваться и перейти в Конструктор")
    public ConstructorPage clickLoginButton() {
        loginButton.click();
        return Selenide.page(ConstructorPage.class);
    }

    @Step("Проверка загрузки страницы")
    public void checkLoginAppearance() {
        entranceTitle.shouldBe(Condition.appear, Duration.of(3, ChronoUnit.SECONDS));
    }

    public void changePasswordVisibility() {
        iconEye.click();
    }
}
