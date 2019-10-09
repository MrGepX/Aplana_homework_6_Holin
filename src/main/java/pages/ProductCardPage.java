package pages;

import baseClasses.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductCardPage extends BasePage {

    private ProductObject object = null;

    @FindBy (xpath = "//h1 [@class = 'page-title price-item-title']")
    private WebElement name;

    @FindBy (xpath = "//span [@class = 'current-price-value']")
    private WebElement price;

    private WebElement warrantySelector = null;
    public boolean checkWarranty () {
        try {
            warrantySelector = driver.findElement(By.xpath("//select[@class = 'form-control select']"));
            return true;
        } catch (NoSuchElementException e) {
            e.getStackTrace();
            return false;
        }
    }

    @FindBy (xpath = "//div [@class = 'price-item-description']")
    private WebElement description;

    @FindBy (xpath = "//button[@class = 'btn btn-cart btn-lg']")
    private WebElement addToBasket;

    @FindBy (xpath = "//div [@class = 'price-changed-alert']")
    private WebElement changePriceConfirm;

    @FindBy (xpath = "//button[@class = 'btn btn-cart btn-lg btn-active']")
    private WebElement addToBasketConfirm;

    public void fillData () {
        String warranty = "No Warranty";
        if (checkWarranty()) {
            warranty = warrantySelector.getText();
        }
        object = new ProductObject(
                name.getText(),
                Integer.parseInt(price.getText().replaceAll("\\s+","")),
                warranty,
                description.getText());
    }

    public void setWarranty(int years) {
        Select select = new Select(warrantySelector);
        select.selectByIndex(years);
        object.setWarranty(select.getFirstSelectedOption().getText().trim());
        waitForElement(changePriceConfirm);
        object.setPriceWithWarranty(Integer.parseInt(price.getText().replaceAll("\\s+","")));
    }

    public void addToBasket(){
        addToBasket.click();
        waitForElement(addToBasketConfirm);
        basket.add(object);

    }
}
