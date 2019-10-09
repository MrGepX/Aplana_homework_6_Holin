package baseClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import pages.ProductObject;

import java.util.ArrayList;
import java.util.function.Function;


public class BasePage {

    public static WebDriver driver;
    public static Wait<WebDriver> wait;
    public static ArrayList<ProductObject> basket = new ArrayList<>();
    public static ArrayList<ProductObject> trash = new ArrayList<>();
    public WebElement element;
    public static int currentPrice;

    public BasePage () {
        driver = InitializationClass.getDriver();
        wait = InitializationClass.getWait();
        PageFactory.initElements(driver,this);
    }

    public WebElement waitForElement(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForChanges (WebElement element, String waitingString) {
        wait.until(new Function<WebDriver, Object>() {
            @Override
            public Boolean apply (WebDriver webDriver) {
                return !element.getText().replaceAll("\\s+","").equals(waitingString);
            }
        });
    }

    public void setCurrentPrice() {
        currentPrice = countAllProducts();
    }
    public int countAllProducts() {
        int counter = 0;
        for (ProductObject productObject: basket) {
            if (productObject.getWarranty().equals("No Warranty")) {
                counter += productObject.getPrice();
            } else {
                counter += productObject.getPriceWithWarranty();
            }
        }
        return counter;
    }

    public int countAllTrash() {
        int counter = 0;
        for (ProductObject productObject: trash) {
            if (productObject.getWarranty().equals("No Warranty")) {
                counter += productObject.getPrice();
            } else {
                counter += productObject.getPriceWithWarranty();
            }
        }
        return counter;
    }


    public int getBasketPrice() {
        return Integer.parseInt(driver.findElement(By.xpath("//nav[@id='header-search']//span[@data-of='totalPrice']")) // Вот так почему то не сработало //span [@data-of= 'totalPrice']")).getText()
                .getText().replaceAll("\\s+","")); //TODO
    }

    public void removeProductByName (String name) {
        for (ProductObject product: basket) {
            if (product.getName().contains(name)) {
                trash.add(product);
            }
        }
        basket.removeIf(productObject -> productObject.getName().contains(name));
    }
}
