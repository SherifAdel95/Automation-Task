package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultsPage extends pageBase {

	public ResultsPage(WebDriver driver) {
		super(driver);
		jse=(JavascriptExecutor)driver;
		// TODO Auto-generated constructor stub
	}

	@FindBy(tagName =  "h1")
	public WebElement productsTitle;
	
	@FindBy(id="facetFilter-Online Only")
	WebElement onlineFilter;

	@FindBy(css="div.pillList_31Ene")
	public WebElement onlineFilterApplied;
	
	@FindBy(partialLinkText = "Apple iPad Air 10.9\" 64GB with Wi-Fi (4th Generation) - Sky Blue")
	WebElement ipadProduct;
	
	public void applyOnlineFilter()
	{

		clickButton(onlineFilter);
	}
	public void openProductDetailedPage()
	{
		scrollToBottom();
		clickButton(ipadProduct);
	}

}
