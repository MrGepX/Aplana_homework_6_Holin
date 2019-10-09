package pages;

import baseClasses.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BasketPage extends BasePage {

@FindBy (xpath = "//div[@class='total-amount']//div[@class='item-price']//span")
private WebElement basketPrice;

@FindBy (xpath = "//div[@class='cart-list__product']")
private List<WebElement> listOfProducts;

@FindBy (xpath = "//span [@class = 'pseudo-link' and contains(text(), 'Вернуть удалённый товар')]")
private WebElement returnProduct;

    String currentPrice;

    public void checkWarranty() {
        for (int i = 1; i <= basket.size(); i++) {
            if (!basket.get(i-1).getWarranty().equals("No Warranty")) {
                element = driver.findElement(By.xpath("//div[@class = 'cart-list__products']/div[" + i + "]//div [@class = 'radio radio_checked']"));
                Assert.assertTrue(element.getText().contains(basket.get(i-1).getWarranty()));
            }
        }
    }

    public void checkPrices() {
        for (int i = 1; i <= basket.size(); i++) {
                element = driver.findElement(By.xpath("//div[@class = 'cart-list__products']/div[" + i + "]//div [@class = 'item-price']/span"));
                Assert.assertEquals(
                        Integer.parseInt(element.getText().replaceAll("\\s+","")),
                        (basket.get(i-1).getPrice()));
        }
    }

    public boolean checkExistOfObjectByName (String name) {
        try {
            driver.findElement(By.xpath("//div [@class = 'cart-list__products']//* [contains(text(), '" + name + "')]"));
            System.out.println();

            return true;
        } catch (NoSuchElementException e) {
            System.out.println();
            return false;
        }
    }

    public void deleteObjectByName (String name) {
        WebElement element = driver.findElement(By.xpath("//a [@class = 'cart-list__product-name-link' and " +
                "contains (text(), '" + name + "')]//ancestor::*[3]//button [@class = 'remove-button']"));
        element.click();
        currentPrice = String.valueOf(getBasketPrice());
        waitForChanges(basketPrice, currentPrice);
        removeProductByName(name);
    }

    public void buyMoreProducts (String name) {
        WebElement element = driver.findElement(By.xpath("//a [@class = 'cart-list__product-name-link' and contains (text(), '" + name + "')]//ancestor::*[3]//button [@class = 'count-buttons__button count-buttons__button_plus']"));
        currentPrice = String.valueOf(getBasketPrice());

        element.click();
        waitForChanges(basketPrice, currentPrice);
        basket.add(getObjectByName(name));
    }

    public ProductObject getObjectByName (String name) {
        for (ProductObject object : basket) {
            if (object.getName().contains(name)) {
                return object;
            }
        }
        return null;
    }

    public void returnProduct() {
        returnProduct.click();
        waitForChanges(basketPrice, currentPrice);
        getObjectsFromTrash();
    }

    public void getObjectsFromTrash () {
        for (ProductObject object : trash) {
            basket.add(object);
        }
    }
}
