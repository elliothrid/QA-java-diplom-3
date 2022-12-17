package site.nomoreparties.stellarburgers.page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.$;

public class ConstructorPage {
    public final static String URL = "https://stellarburgers.nomoreparties.site";

    public StellarHeaderPage stellarHeaderPage = Selenide.page(StellarHeaderPage.class);

    @FindBy(how = How.XPATH, using = ".//h1[text()='Соберите бургер']")
    private SelenideElement constructorTitle;

    @FindBy(how = How.XPATH, using = ".//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']")
    private SelenideElement menuIngredientsContainer;

    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement bunTitle;

    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement sauceTitle;

    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    private SelenideElement toppingTitle;

    @FindBy(how = How.XPATH, using = ".//a[@class='BurgerIngredient_ingredient__1TVf6 ml-4 mr-4 mb-8']")
    private List<SelenideElement> ingredientsMenuList;

    @FindBy(how = How.XPATH, using = ".//li[@class='BurgerConstructor_basket__listItem__aWMu1 mr-4']")
    private List<SelenideElement> burgerConstructorBasketList;

    @FindBy(how = How.XPATH, using = ".//div[@class='BurgerConstructor_basket__totalContainer__2Z-ho mr-10']")
    private SelenideElement basketTotal;

    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement makeOrderButton;

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;

    @FindBy(how = How.XPATH, using = ".//div[@class='Modal_modal__container__Wo2l_']")
    private SelenideElement orderConfirmationModule;

    @Step("Нажать на кнопку \"Войти в аккаунт\"")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }

    @Step("Перейти в меню на раздел Булки")
    public void clickBunsItem() {
        bunTitle.shouldBe(Condition.visible, Condition.enabled);
        bunTitle.click();
        $(By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']" +
                "/span[text()='Булки']")).shouldBe(Condition.visible);
    }

    @Step("Перейти в меню на раздел Соусы")
    public void clickSaucesItem() {
        sauceTitle.click();
        $(By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']" +
                "/span[text()='Соусы']")).shouldBe(Condition.visible);
    }

    @Step("Перейти в меню на раздел Начинки")
    public void clickToppingsItem() {
        toppingTitle.click();
        $(By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']" +
                "/span[text()='Начинки']")).shouldBe(Condition.visible);
    }

    public void checkConstructorLoaded() {
        constructorTitle.shouldBe(Condition.appear);
    }

    public void checkConstructorLoggedIn() {
        makeOrderButton.shouldBe(Condition.appear);
    }

    @Step("Нажать на кнопку \"Оформить заказ\"")
    public void clickMakeOrder() {
        makeOrderButton.click();
        orderConfirmationModule.shouldBe(Condition.visible);
    }

    @Step("Закрыть форму подтверждения заказа")
    public void closeConfirmationModule() {
        orderConfirmationModule.find(By.className("Modal_modal__close_modified__3V5XS Modal_modal__close__TnseK")).click();
        orderConfirmationModule.shouldBe(Condition.disappear, Duration.of(1, ChronoUnit.SECONDS));
    }

}
