package site.nomoreparties.stellarburgers.page_object;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class FeedPage {
    public static final String PATH = "/feed";

    public StellarHeaderPage stellarHeaderPage = Selenide.page(StellarHeaderPage.class);

    @FindBy(how = How.XPATH, using = ".//h1[text()='Лента заказов']")
    private SelenideElement FeedOrdersHeader;

    @FindBy(how = How.XPATH, using = ".//a[@class='OrderHistory_link__1iNby']")
    private List<SelenideElement> OrderHistoryList;

    @FindBy(how = How.XPATH, using = ".//div[@class='OrderFeed_orderStatusBox__1d4q2 mb-15']")
    private SelenideElement orderStatusBox;

    @FindBy(how = How.XPATH, using = ".//p[text()='Выполнено за все время:']")
    private SelenideElement totalFinishedOrdersTitle;

    @FindBy(how = How.XPATH, using = ".//p[text()='Выполнено за сегодня:']")
    private SelenideElement todayFinishedOrdersTitle;

    @FindBy(how = How.XPATH, using = ".//p[@class='OrderFeed_number__2MbrQ text text_type_digits-large']")
    private List<SelenideElement> OrdersCountersList;
}
