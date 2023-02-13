package org.example;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RubExmplGoToRegMailDomainOnYandexService {

    public static AuthPageInsideBoxMailRu authPageInsideBoxMailRu;
    public static LoginAuthPageYandexRu loginAuthPageYandexRu;
    public static SignInPageMainMailRu signInPageMainMailRu;
    public static MainPageYandexRu mainPageYandexRu;
    public static PopUpAuthPageMailRu popUpAuthPageMailRu;
    public static WebDriver driver;
    public static String mailTab;
    public static String yandexTab;

    @BeforeClass
    public static void setUp() {

        /**
         * осуществление первоначальrной настройки
         */

        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        driver = new ChromeDriver();
        authPageInsideBoxMailRu = new AuthPageInsideBoxMailRu(driver);
        loginAuthPageYandexRu = new LoginAuthPageYandexRu(driver);
        signInPageMainMailRu = new SignInPageMainMailRu(driver);
        mainPageYandexRu = new MainPageYandexRu(driver);
        popUpAuthPageMailRu = new PopUpAuthPageMailRu(driver);

        driver.manage().window().maximize();
        driver.navigate().to(ConfProperties.getProperty("loginPage"));
        yandexTab = driver.getWindowHandle();

        JavascriptExecutor jscript = (JavascriptExecutor) driver;
        jscript.executeScript("window.open('https://mail.ru');", driver.getWindowHandles());

        mailTab = null;

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();

        while (true) {
            if (!iterator.hasNext()) break;
            mailTab = iterator.next();
        }
    }

    @Test
    public void authYaRu() {

        mainPageYandexRu.buttonToGoEnterLoginField();
        mainPageYandexRu.clickForChooseAuthMeth();

        loginAuthPageYandexRu.inputLogin();
        loginAuthPageYandexRu.signInButton();

        signInPageMainMailRu.goToMailAuthFieldsButton();

        popUpAuthPageMailRu.swithToIFrame();
        popUpAuthPageMailRu.inputLoginFields();
        popUpAuthPageMailRu.clickToGoPasswordInputFields();
        popUpAuthPageMailRu.inputMailPassword();
        popUpAuthPageMailRu.clickSignInMailRu();

        authPageInsideBoxMailRu.goToMessageWithSecretCode();
        authPageInsideBoxMailRu.getSecretCodeFromMessage();

        loginAuthPageYandexRu.inputSecretMailCodeInYandexFinishTest();
    }

    @AfterClass
    public static void tearsDown() {
        driver.quit();
        System.out.println("🖐");
    }
}