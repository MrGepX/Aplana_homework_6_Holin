package pages;

import baseClasses.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends BasePage {

@FindBy (xpath = "//label [@class = 'radio__label' and contains(text(), '2 года')]")
private WebElement warrantyRadio;

    public void checkWarranty() {
        Assert.assertTrue(warrantyRadio.getText().contains("2 года"));
    }

    public boolean checkExistOfObjectByName (String name) {
        try {
            driver.findElement(By.xpath("//div [@class = 'cart-list__products']//* [contains(text(), '" + name + "')]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void deleteObjectByName (String name) {
        WebElement element = driver.findElement(By.xpath("//a [@class = 'cart-list__product-name-link' and " +
                "contains (text(), '" + name + "')]//ancestor::*[3]//button [@class = 'remove-button']"));
        element.click();

        removeProductByName(name);
    }

    public void buyMoreProducts (String name) {
        WebElement element = driver.findElement(By.xpath("//a [@class = 'cart-list__product-name-link' and contains (text(), '" + name + "')]//ancestor::*[3]//button [@class = 'count-buttons__button count-buttons__button_plus']"));
        waitForElement(element);
        element.click();
    }
}
