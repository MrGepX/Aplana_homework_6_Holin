package pages;

import baseClasses.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(xpath = "(//input [@class = 'ui-input-search__input main-search-form__input ui-autocomplete-input'])[1]")
    private WebElement searchInput;

    @FindBy(xpath = "(//span[@class = 'ui-input-search__icon ui-input-search__icon_search'])[2]")
    private WebElement searchBtn;

    public void searchItems (String name) {
        searchInput.clear();
        searchInput.sendKeys(name);
        searchBtn.click();
    }
}
