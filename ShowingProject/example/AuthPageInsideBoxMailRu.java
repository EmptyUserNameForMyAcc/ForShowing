package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthPageInsideBoxMailRu {

    public WebDriver driver;

    public AuthPageInsideBoxMailRu(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[contains(@class, 'sp__normal')]")
    private WebElement neededMassageWithCodeFromMailBox;

    public void goToMessageWithSecretCode() throws InterruptedException {
        neededMassageWithCodeFromMailBox.click();
    }

    @FindBy(xpath = "//tr/td[contains(@style, 'padding')]/descendant::td/p[3]/b")
    private WebElement getCodeFromMailRuMassage;

    public String getSecretCodeFromMessage() {
        return getCodeFromMailRuMassage.getText();
    }
}