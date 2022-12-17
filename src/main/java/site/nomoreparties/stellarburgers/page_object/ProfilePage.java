package site.nomoreparties.stellarburgers.page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.page;

import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;
import java.time.temporal.ChronoUnit;


public class ProfilePage {

    public static final String PATH = "/account/profile";

    public StellarHeaderPage stellarHeaderPage = Selenide.page(StellarHeaderPage.class);

    @FindBy(how= How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement exitLink;

    @FindBy(how= How.XPATH, using = ".//a[text()='Профиль']")
    private SelenideElement profileLink;

    @FindBy(how= How.XPATH, using = ".//a[text()='История заказов']")
    private SelenideElement historyLink;

    @FindBy(how = How.XPATH, using = ".//input[@name='Name' and @type='text']")
    private SelenideElement nameInput;

    @FindBy(how = How.XPATH, using = ".//input[@name='name' and @type='text']")
    private SelenideElement loginInput;

    @FindBy(how = How.XPATH, using = ".//input[@name='name' and @type='password']")
    private SelenideElement passwordInput;

    @FindBy(how = How.XPATH, using = ".//button[text()='Сохранить']")
    private SelenideElement saveButton;

    @FindBy(how = How.XPATH, using = ".//button[text()='Отмена']")
    private SelenideElement cancelButton;

    @Step("Проверка загрузки Профиля")
    public void checkProfile() {
        this.profileLink.shouldBe(Condition.appear);
    }

    public void clickEditName() {
        nameInput.find(byXpath(".//div[@class='input__icon input__icon-action']")).click();
        nameInput.shouldBe(Condition.enabled, Duration.of(1, ChronoUnit.SECONDS));
    }

    public void clickEditLogin() {
        loginInput.find(byXpath(".//div[@class='input__icon input__icon-action']")).click();
        loginInput.shouldBe(Condition.enabled, Duration.of(1, ChronoUnit.SECONDS));
    }

    public void clickEditPassword() {
        passwordInput.find(byXpath(".//div[@class='input__icon input__icon-action']")).click();
        passwordInput.shouldBe(Condition.enabled, Duration.of(1, ChronoUnit.SECONDS));
    }

    public OrderHistoryPage clickOrderHistoryLink() {
        historyLink.click();
        return page(OrderHistoryPage.class);
    }

    public LoginPage clickExitLink() {
        exitLink.click();
        return page(LoginPage.class);
    }
}
