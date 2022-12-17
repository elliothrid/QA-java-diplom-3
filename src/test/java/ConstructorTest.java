import site.nomoreparties.stellarburgers.page_object.ConstructorPage;
import site.nomoreparties.stellarburgers.page_object.LoginPage;
import site.nomoreparties.stellarburgers.api.AuthResponse;
import org.junit.*;
import site.nomoreparties.stellarburgers.test_method.TestMethod;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static site.nomoreparties.stellarburgers.test_method.TestMethod.randomEmail;
import static site.nomoreparties.stellarburgers.test_method.TestMethod.randomAlfaNum;

public class ConstructorTest {

    private TestMethod testMethod = new TestMethod();

    private String name;
    private String email;
    private String passwd;
    private String token;
    private String refreshToken;

    @Before
    public void prepareTest() {
        this.name = randomAlfaNum();
        this.passwd = randomAlfaNum();
        this.email = randomEmail();
        AuthResponse registerResponse = this.testMethod.registerUser(this.email, this.passwd, this.name);
        this.token = registerResponse.getAccessToken();
        this.refreshToken = registerResponse.getRefreshToken();
    }

    @After
    public void clearTest() {
        closeWindow();
        testMethod.logoutUser(this.refreshToken);
        testMethod.deleteUser(this.token);
    }

    @Test
    @Description("Создать пользователя через API. Зайти на главную страницу https://stellarburgers.nomoreparties.site/. " +
            "Нажать на кнопку Войти в аккаунт. Ввести учётные данные пользователяи осуществить вход " +
            "на страницу конструктора уже залогиненного пользователя. " +
            "Кликнуть на пункт меню Булки.")
    @DisplayName("Переход в раздел Булки.")
    public void checkConstructorBuns() {
        ConstructorPage constructor = open(ConstructorPage.URL, ConstructorPage.class);
        LoginPage loginPage = constructor.clickLoginButton();
        Assert.assertEquals(ConstructorPage.URL + LoginPage.PATH, url());
        loginPage.enterEmail(this.email);
        loginPage.enterPassword(this.passwd);
        ConstructorPage constructorLoggedIn = loginPage.clickLoginButton();
        constructorLoggedIn.checkConstructorLoggedIn();
        constructor.clickSaucesItem();
        constructor.clickBunsItem();
    }

    @Test
    @Description("Создать пользователя через API. Зайти на главную страницу https://stellarburgers.nomoreparties.site/. " +
            "Нажать на кнопку Войти в аккаунт. Ввести учётные данные пользователя и осуществить вход " +
            "на страницу конструктора уже залогиненного пользователя. " +
            "Кликнуть на пункт меню Соусы.")
    @DisplayName("Переход в раздел Соусы.")
    public void checkConstructorSauces() {
        ConstructorPage constructor = open(ConstructorPage.URL, ConstructorPage.class);
        LoginPage loginPage = constructor.clickLoginButton();
        Assert.assertEquals(ConstructorPage.URL + LoginPage.PATH, url());
        loginPage.enterEmail(this.email);
        loginPage.enterPassword(this.passwd);
        ConstructorPage constructorLoggedIn = loginPage.clickLoginButton();
        constructorLoggedIn.checkConstructorLoggedIn();
        constructor.clickSaucesItem();
    }

    @Test
    @Description("Создать пользователя через API. Зайти на главную страницу https://stellarburgers.nomoreparties.site/. " +
            "Нажать на кнопку Войти в аккаунт. Ввести учётные данные пользователяи b осуществить вход " +
            "на страницу конструктора уже залогиненного пользователя. " +
            "Кликнуть на пункт меню Начинки.")
    @DisplayName("Переход в раздел Начинки.")
    public void checkConstructorToppings() {
        ConstructorPage constructor = open(ConstructorPage.URL, ConstructorPage.class);
        LoginPage loginPage = constructor.clickLoginButton();
        Assert.assertEquals(ConstructorPage.URL + LoginPage.PATH, url());
        loginPage.enterEmail(this.email);
        loginPage.enterPassword(this.passwd);
        ConstructorPage constructorLoggedIn = loginPage.clickLoginButton();
        constructorLoggedIn.checkConstructorLoggedIn();
        constructor.clickToppingsItem();
    }

}
