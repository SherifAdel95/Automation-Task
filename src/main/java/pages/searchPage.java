package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class searchPage extends pageBase {

	public searchPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(css = "input.textField_XaJoz")	
	WebElement searchTxt;

	@FindBy(css="svg.blue_2GwtG.searchIcon_18ax0.icon_q2ZYd")
	WebElement searchBtn;
	
	@FindBy(css="a.autocompleteLink_3QJrx")
	List<WebElement>productList;

	public void searchUsingAutoSuggest(String productName)
	{

		setElementTxt(searchTxt, productName);
		try {
			Thread.sleep(5000);
			productList.get(1).click();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
}
