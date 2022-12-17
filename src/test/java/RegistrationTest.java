import site.nomoreparties.stellarburgers.page_object.LoginPage;
import site.nomoreparties.stellarburgers.api.AuthResponse;
import org.junit.After;
import site.nomoreparties.stellarburgers.test_method.TestMethod;
import org.junit.Test;
import site.nomoreparties.stellarburgers.page_object.ConstructorPage;
import site.nomoreparties.stellarburgers.page_object.RegisterPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWindow;
import static com.codeborne.selenide.WebDriverRunner.url;
import static site.nomoreparties.stellarburgers.test_method.TestMethod.randomEmail;
import static site.nomoreparties.stellarburgers.test_method.TestMethod.randomAlfaNum;

public class RegistrationTest {

    @After
    public void clearTest() {
        closeWindow();
    }

    @Test
    @DisplayName("Регистрация пользователя через форму /register")
    @Description("Зарегистрировать пользователя через UI на странице /register, " +
            "проверить что открылась страницы /login, " +
            "проверить, что можно залогиниться")
    public void checkUserRegister() {
        RegisterPage registerPage = open(ConstructorPage.URL + RegisterPage.PATH, RegisterPage.class);
        String name = randomAlfaNum();
        String passwd = randomAlfaNum();
        String email = randomEmail();
        registerPage.enterEmail(email);
        registerPage.enterLogin(name);
        registerPage.enterPassword(passwd);
        registerPage.changePasswordVisibility();
        registerPage.checkPasswordInputOpen();
        LoginPage loginPage = registerPage.clickRegisterButton();
        loginPage.entranceTitle.shouldBe(Condition.visible);
        Assert.assertEquals(ConstructorPage.URL + LoginPage.PATH, url());
        TestMethod testMethod = new TestMethod();
        AuthResponse authResponse = testMethod.authUser(email, passwd);
        AuthResponse userInfo = testMethod.userInfo(authResponse.getAccessToken());
        Assert.assertTrue(userInfo.getSuccess());
        Assert.assertEquals(name, userInfo.getUser().getName());
        Assert.assertEquals(email.toLowerCase(), userInfo.getUser().getEmail());
        testMethod.logoutUser(authResponse.getRefreshToken());
        testMethod.deleteUser(authResponse.getAccessToken());
    }

    @Test
    @DisplayName("Ввод пароля меньше 6ти символов")
    @Description("При регистрации через UI на странице /register ввести пароль длинной меньше 6 символов. " +
    "Получаем ошибку \"Некорректный пароль\"")
    public void checkIncorrectPasswordRegister() {
        RegisterPage registerPage = open(ConstructorPage.URL + RegisterPage.PATH, RegisterPage.class);
        String name = randomAlfaNum();
        String passwd = randomAlfaNum(1, 5);
        String email = randomEmail();
        registerPage.enterEmail(email);
        registerPage.enterLogin(name);
        registerPage.enterPassword(passwd);
        registerPage.changePasswordVisibility();
        registerPage.checkPasswordInputOpen();
        registerPage.clickRegisterButtonIncorrect();
        registerPage.checkIncorrectPasswordMessage();
    }
}

