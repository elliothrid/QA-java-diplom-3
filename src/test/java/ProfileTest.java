import site.nomoreparties.stellarburgers.page_object.ConstructorPage;
import site.nomoreparties.stellarburgers.page_object.LoginPage;
import site.nomoreparties.stellarburgers.page_object.ProfilePage;
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

public class ProfileTest {
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
            "на страницу конструктора уже залогиненного пользователя." +
            "Нажать на Личный Кабинет в заголовке и перейти в аккаунт")
    @DisplayName("Переход по клику в Личный кабинет.")
    public void checkLinkToProfile() {
        ConstructorPage constructor = open(ConstructorPage.URL, ConstructorPage.class);
        LoginPage loginPage = constructor.clickLoginButton();
        Assert.assertEquals(ConstructorPage.URL + LoginPage.PATH, url());
        loginPage.enterEmail(this.email);
        loginPage.enterPassword(this.passwd);
        ConstructorPage constructorLoggedIn = loginPage.clickLoginButton();
        constructorLoggedIn.checkConstructorLoggedIn();
        ProfilePage profilePage = constructor.stellarHeaderPage.clickProfileLoggedIn();
        profilePage.checkProfile();
        Assert.assertEquals(ConstructorPage.URL + ProfilePage.PATH, url());
    }

    @Test
    @Description("Создать пользователя через API. Зайти на главную страницу https://stellarburgers.nomoreparties.site/. " +
            "Нажать на кнопку Войти в аккаунт. Ввести учётные данные пользователяи осуществить вход " +
            "на страницу конструктора уже залогиненного пользователя. " +
            "Нажать на Личный Кабинет в заголовке и перейти в аккаунт /account/profile. " +
            "Перейти по клику на Конструктор и на логотип приложения на главную.")
    @DisplayName("Переход из Личного кабинета на главную.")
    public void checkLinkToConstructor() {
        ConstructorPage constructor = open(ConstructorPage.URL, ConstructorPage.class);
        LoginPage loginPage = constructor.clickLoginButton();
        loginPage.enterEmail(this.email);
        loginPage.enterPassword(this.passwd);
        ConstructorPage constructorLoggedIn = loginPage.clickLoginButton();
        constructorLoggedIn.checkConstructorLoggedIn();
        ProfilePage profilePage = constructor.stellarHeaderPage.clickProfileLoggedIn();
        constructor = profilePage.stellarHeaderPage.clickConstructor();
        Assert.assertEquals(ConstructorPage.URL + "/", url());
        constructor.checkConstructorLoggedIn();
        profilePage = constructor.stellarHeaderPage.clickProfileLoggedIn();
        constructor = profilePage.stellarHeaderPage.clickConstructorLogo();
        Assert.assertEquals(ConstructorPage.URL + "/", url());
        constructor.checkConstructorLoggedIn();
    }

    @Test
    @Description("Создать пользователя через API. Зайти на главную страницу https://stellarburgers.nomoreparties.site/. " +
            "Нажать на кнопку Войти в аккаунт. Ввести учётные данные пользователяи осуществить вход " +
            "на страницу конструктора уже залогиненного пользователя." +
            "Нажать на Личный Кабинет в заголовке и перейти в аккаунт. " +
            "Нажать на кнопку Выход и перейти на страницу логина.")
    @DisplayName("Переход по клику в Личный кабинет.")
    public void checkProfileLogout() {
        ConstructorPage constructor = open(ConstructorPage.URL, ConstructorPage.class);
        LoginPage loginPage = constructor.clickLoginButton();
        loginPage.enterEmail(this.email);
        loginPage.enterPassword(this.passwd);
        ConstructorPage constructorLoggedIn = loginPage.clickLoginButton();
        constructorLoggedIn.checkConstructorLoggedIn();
        ProfilePage profilePage = constructor.stellarHeaderPage.clickProfileLoggedIn();
        loginPage = profilePage.clickExitLink();
        loginPage.checkLoginAppearance();
        Assert.assertEquals(ConstructorPage.URL + LoginPage.PATH, url());
    }
}
