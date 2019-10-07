package baseClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import pages.ProductObject;

import java.util.ArrayList;


public class BasePage {

    public static WebDriver driver;
    static Wait<WebDriver> wait;
    public static ArrayList<ProductObject> basket = new ArrayList<>();

    public BasePage () {
        driver = InitializationClass.getDriver();
        wait = InitializationClass.getWait();
        PageFactory.initElements(driver,this);
    }

    public WebElement waitForElement(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public int countAllProducts() {
        int counter = 0;
        for (ProductObject productObject: basket) {
            counter += productObject.getPrice();
        }
        return counter;
    }

    public int getBasketPrice() {
        return Integer.parseInt(driver.findElement(By.xpath("//nav[@id='header-search']//span[@data-of='totalPrice']"))
                .getText().replaceAll("\\s+","")); //TODO
    }

    public void removeProductByName (String name) {
        basket.removeIf(productObject -> productObject.getName().contains(name));
        System.out.println(basket.size());
    }
}
