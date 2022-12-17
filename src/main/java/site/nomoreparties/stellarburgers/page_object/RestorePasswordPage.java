package site.nomoreparties.stellarburgers.page_object;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.page;

public class RestorePasswordPage {

    public static final String PATH = "/forgot-password";

    public StellarHeaderPage stellarHeaderPage = Selenide.page(StellarHeaderPage.class);

    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement emailInput;

    @FindBy(how = How.XPATH, using = ".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']")
    private SelenideElement restoreButton;

    @FindBy(how = How.XPATH, using = ".//a[@class='Auth_link__1fOlj']")
    private SelenideElement loginLink;

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    @Step("Нажать на кнопку восстановления пароля")
    public void clickRestoreButton() {
        restoreButton.click();
    }

    @Step("Перейти по ссылке на станицу автоизации /login")
    public LoginPage clickLoginLink() {
        loginLink.click();
        return page(LoginPage.class);
    }

}
