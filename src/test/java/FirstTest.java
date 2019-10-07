import baseClasses.BasePage;
import baseClasses.InitializationClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.BasketPage;
import pages.MainPage;
import pages.ProductCardPage;
import pages.ResultPage;

public class FirstTest {
    @Test
    public void firstTest() {
        InitializationClass.init();

        BasePage basePage = new BasePage();
        MainPage main = new MainPage();
        main.searchItems("playstation");
        ResultPage result = new ResultPage();
        result.searchAndClick("playstation 4 slim black");

        ProductCardPage productCardPage = new ProductCardPage();
        productCardPage.fillData();
        if (productCardPage.checkWarranty()) {
            productCardPage.setWarranty(2);
        }
        productCardPage.addToBasket();

        main.searchItems("Detroit");
        productCardPage.fillData();
        productCardPage.addToBasket();

        Assert.assertEquals(basePage.getBasketPrice(), basePage.countAllProducts());

        InitializationClass.getDriver().get("https://www.dns-shop.ru/order/begin/");

        BasketPage basketPage = new BasketPage();
        basketPage.checkWarranty();
        basketPage.deleteObjectByName("Detroit");
        System.out.println(basketPage.checkExistOfObjectByName("Detroit"));
        Assert.assertFalse(basketPage.checkExistOfObjectByName("Detroit"));

        Assert.assertEquals(basePage.getBasketPrice(), basePage.countAllProducts());

        basketPage.buyMoreProducts("PlayStation");
    }
}
