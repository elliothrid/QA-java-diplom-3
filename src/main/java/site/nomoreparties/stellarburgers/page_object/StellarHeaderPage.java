package site.nomoreparties.stellarburgers.page_object;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Selenide.page;


public class StellarHeaderPage {
    @FindBy(how = How.XPATH, using = ".//p[text()='Конструктор']")
    private SelenideElement constructorButton;

    @FindBy(how = How.XPATH, using = ".//p[text()='Лента Заказов']")
    private SelenideElement ordersFeed;

    @FindBy(how = How.XPATH, using = ".//p[text()='Личный Кабинет']")
    private SelenideElement profile;

    @FindBy(how = How.XPATH, using = ".//div[@class='AppHeader_header__logo__2D0X2']")
    private SelenideElement stellarLogo;

    @Step("Перейти на главную страницу по ссылке \"Конструктор\"")
    public ConstructorPage clickConstructor() {
        this.constructorButton.click();
        return page(ConstructorPage.class);
    }

    @Step("Перейти на страницу заказов по ссылке \"Лента Заказов\"")
    public FeedPage clickOrderFeed() {
        this.ordersFeed.click();
        return  page(FeedPage.class);
    }

    @Step("Авторизоваться через \"Личный Кабинет\"")
    public LoginPage clickProfile() {
        this.profile.click();
        return page(LoginPage.class);
    }

    @Step("Перейти в Личный Кабинет авторизованного пользователя")
    public ProfilePage clickProfileLoggedIn() {
        this.profile.click();
        return page(ProfilePage.class);
    }

    @Step("Перейти на главную страницу \"Конструктор\" по клику на логотип в центре заголовка")
    public ConstructorPage clickConstructorLogo() {
        this.stellarLogo.click();
        ConstructorPage constructor = page(ConstructorPage.class);
        constructor.checkConstructorLoaded();
        return constructor;
    }
}
