package pages;

import baseClasses.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultPage extends BasePage {

    private WebElement product;

    public void searchAndClick(String name) {
        product = driver.findElement(By.xpath("//a [contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'" + name + "')] "));
        product.click();
    }
}
