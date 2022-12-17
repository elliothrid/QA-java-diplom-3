import site.nomoreparties.stellarburgers.page_object.ConstructorPage;
import site.nomoreparties.stellarburgers.page_object.LoginPage;
import site.nomoreparties.stellarburgers.page_object.RegisterPage;
import site.nomoreparties.stellarburgers.page_object.RestorePasswordPage;
import site.nomoreparties.stellarburgers.api.AuthResponse;
import org.junit.*;
import site.nomoreparties.stellarburgers.test_method.TestMethod;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.WebDriverRunner.url;
import static site.nomoreparties.stellarburgers.test_method.TestMethod.randomEmail;
import static site.nomoreparties.stellarburgers.test_method.TestMethod.randomAlfaNum;

public class LoginTest {
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
            "на страницу конструктора уже залогиненного пользователя.")
    @DisplayName("Вход в аккаунт.")
    public void checkEnterFromConstructorByAccountButton() {
        ConstructorPage constructor = open(ConstructorPage.URL, ConstructorPage.class);
        LoginPage loginPage = constructor.clickLoginButton();
        Assert.assertEquals(ConstructorPage.URL + LoginPage.PATH, url());
        loginPage.enterEmail(this.email);
        loginPage.enterPassword(this.passwd);
        ConstructorPage constructorLoggedIn = loginPage.clickLoginButton();
        constructorLoggedIn.checkConstructorLoggedIn();
    }

    @Test
    @Description("Создать пользователя через API. Зайти на главную страницу https://stellarburgers.nomoreparties.site/. " +
            "Нажать на ссылку Личный Кабинет в заголовке странице. Ввести учётные данные пользователяи осуществить вход " +
            "на страницу конструктора уже залогиненного пользователя.")
    @DisplayName("Вход через Личный Кабинет.")
    public void checkEnterFromConstructorByProfile() {
        ConstructorPage constructor = open(ConstructorPage.URL, ConstructorPage.class);
        LoginPage loginPage = constructor.stellarHeaderPage.clickProfile();
        Assert.assertEquals(ConstructorPage.URL + LoginPage.PATH, url());
        loginPage.enterEmail(this.email);
        loginPage.enterPassword(this.passwd);
        ConstructorPage constructorLoggedIn = loginPage.clickLoginButton();
        constructorLoggedIn.checkConstructorLoggedIn();
    }


    @Test
    @Description("Создать пользователя через API. Зайти на страницу регистрации https://stellarburgers.nomoreparties.site/register. " +
            "Нажать на ссылку \"Войти\" под формой ввода двнных для регистрации. Перейти на страницу авторизаци /login. " +
            "Ввести учётные данные пользователя, нажать кнопку \"Войти\". Перейти на страницу конструктора уже залогиненного пользователя")
    @DisplayName("Вход через форму регистрации.")
    public void checkEnterFromRegister() {
        RegisterPage register = open(ConstructorPage.URL + RegisterPage.PATH, RegisterPage.class);
        LoginPage loginPage = register.clickLoginLink();
        Assert.assertEquals(ConstructorPage.URL + LoginPage.PATH, url());
        loginPage.enterEmail(this.email);
        loginPage.enterPassword(this.passwd);
        ConstructorPage constructorLoggedIn = loginPage.clickLoginButton();
        constructorLoggedIn.checkConstructorLoggedIn();
    }


    @Test
    @Description("Создать пользователя через API. Зайти на главную страницу восстановления пароля https://stellarburgers.nomoreparties.site/. " +
            "Нажать на кнопку Войти в аккаунт. Ввести учётные данные пользователя. Зайти на страницу профиля пользователя")
    @DisplayName("Вход через форму восстановления пароля.")
    public void checkEnterFromRestorePage() {
        RestorePasswordPage restore = open(ConstructorPage.URL + RestorePasswordPage.PATH, RestorePasswordPage.class);
        LoginPage loginPage = restore.clickLoginLink();
        Assert.assertEquals(ConstructorPage.URL + LoginPage.PATH, url());
        loginPage.enterEmail(this.email);
        loginPage.enterPassword(this.passwd);
        ConstructorPage constructorLoggedIn = loginPage.clickLoginButton();
        constructorLoggedIn.checkConstructorLoggedIn();
    }

}
