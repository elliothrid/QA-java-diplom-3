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
import java.util.List;

public class RegisterPage {

    public static final String PATH = "/register";

    public StellarHeaderPage stellarHeaderPage = Selenide.page(StellarHeaderPage.class);

    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private List<SelenideElement> nameEmailInputs;

    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement passwordInput;

    @FindBy(how = How.XPATH, using = ".//input[@type='text' and @name='Пароль']")
    private SelenideElement passwordInputOpen;

    @FindBy(how = How.XPATH, using = ".//div[@class='input__icon input__icon-action']")
    private SelenideElement iconEye;

    @FindBy(how = How.XPATH, using = ".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']")
    private SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = ".//p[@class='input__error text_type_main-default']")
    private SelenideElement passwordIncorrectMessage;

    @FindBy(how = How.XPATH, using = ".//a[@class='Auth_link__1fOlj']")
    private SelenideElement loginLink;

    public void enterLogin(String login) {
        nameEmailInputs.get(0).sendKeys(login);
    }

    public void enterEmail(String email) {
        nameEmailInputs.get(1).sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    @Step("Нажать кнопку \"Зарегистрироваться\"")
    public LoginPage clickRegisterButton() {
        registerButton.click();
        return page(LoginPage.class);
    }

    @Step("Нажать кнопку \"Зарегистрироваться\"")
    public RegisterPage clickRegisterButtonIncorrect() {
        registerButton.click();
        return page(RegisterPage.class);
    }

    @Step("Перейти по ссылке на станицу автоизации /login")
    public LoginPage clickLoginLink() {
        loginLink.click();
        return page(LoginPage.class);
    }

    @Step("Нажать на кнопку скрыть пароль")
    public void changePasswordVisibility() {
        iconEye.click();
    }

    @Step("Видимость введённого пароля изменилась")
    public SelenideElement checkPasswordInputOpen() {
        return this.passwordInputOpen.shouldBe(Condition.appear, Duration.of(1, ChronoUnit.SECONDS));
    }

    @Step("Появилось сообщенение \"Некорректный пароль\"")
    public SelenideElement checkIncorrectPasswordMessage() {
        return this.passwordIncorrectMessage.shouldBe(Condition.appear, Duration.of(1, ChronoUnit.SECONDS));
    }
}
