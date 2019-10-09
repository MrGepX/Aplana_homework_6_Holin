import baseClasses.BasePage;
import baseClasses.InitializationClass;
import controllers.InitPropertyController;
import org.junit.Assert;
import org.junit.Test;
import pages.*;

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

        InitializationClass.getDriver().get(InitPropertyController.getInitProperty("basketUrl"));
        BasketPage basketPage = new BasketPage();
        basketPage.checkWarranty();
        basketPage.checkPrices();

        Assert.assertEquals(basePage.getBasketPrice(), basePage.countAllProducts());

        basketPage.setCurrentPrice();
        basketPage.deleteObjectByName("Detroit");

        Assert.assertFalse(basketPage.checkExistOfObjectByName("Detroit"));

        Assert.assertEquals(BasePage.currentPrice - basePage.countAllTrash(), basePage.countAllProducts());

        basketPage.buyMoreProducts("PlayStation");

        basketPage.buyMoreProducts("PlayStation");

        Assert.assertEquals(basePage.getBasketPrice(), basePage.countAllProducts());
        basketPage.setCurrentPrice();
        basketPage.returnProduct();

        Assert.assertTrue(basketPage.checkExistOfObjectByName("Detroit"));
        Assert.assertEquals(BasePage.currentPrice + basketPage.getObjectByName("Detroit").getPrice(), basePage.countAllProducts());
    }
}
